package com.x_o_client;

import com.x_o_client.data.RequestData;
import com.x_o_client.data.ResponseData;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.ReplayingDecoder;
import java.nio.charset.Charset;
import java.util.List;

public class Client {
    private Channel channel;
    private final Form form;
    
    public Client(Form form) {
        this.form = form;
    }
    
    public void sendMessage(String str) {
        if (channel.isOpen()) {
            RequestData msg = new RequestData();
            msg.setIntValue(0);
            msg.setStringValue(
              str);
            channel.writeAndFlush(msg);
        }
    }
    public void sendMessage(String str, int num) {
        if (channel.isOpen()) {
            RequestData msg = new RequestData();
            msg.setIntValue(num);
            msg.setStringValue(
              str);
            channel.writeAndFlush(msg);
        }
    }
    
    public void CloseConnection() {
        if (channel != null && channel.isOpen()) {
            System.out.println("Closing connection...");
            channel.close();
            channel = null;
        }
    }
    
    public class RequestDataEncoder extends MessageToByteEncoder<RequestData> {

        private final Charset charset = Charset.forName("UTF-8");

        @Override
        protected void encode(ChannelHandlerContext ctx, 
            RequestData msg, ByteBuf out) throws Exception {

            out.writeInt(msg.getIntValue());
            out.writeInt(msg.getStringValue().length());
            out.writeCharSequence(msg.getStringValue(), charset);
        }
    }
    
    public class ResponseDataDecoder extends ReplayingDecoder<ResponseData> {
        private final Charset charset = Charset.forName("UTF-8");
        
        @Override
        protected void decode(ChannelHandlerContext ctx, 
            ByteBuf in, List<Object> out) throws Exception {

            ResponseData data = new ResponseData();
            data.setIntValue(in.readInt());
            int strLen = in.readInt();
            data.setStringValue(in.readCharSequence(strLen, charset).toString());
            strLen = in.readInt();
            data.setOtherStringValue(in.readCharSequence(strLen, charset).toString());
            data.setBoolValue(in.readBoolean());
            out.add(data);
        }
    }

    public void run() {
        String host = "localhost";
        int port = 8080;
        Thread clientThread = new Thread(() -> {
            EventLoopGroup workerGroup = new NioEventLoopGroup();
            try {
                Bootstrap b = new Bootstrap()
                 .group(workerGroup)
                 .channel(NioSocketChannel.class)
                 .option(ChannelOption.SO_KEEPALIVE, true)
                 .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) 
                      throws Exception {
                        ch.pipeline().addLast(new RequestDataEncoder(), 
                          new ResponseDataDecoder(), 
                          new ServerHandler(form));
                    }
                });

                ChannelFuture f = b.connect(host, port).sync();
                if (!f.channel().isActive()) {
                    CloseConnection();
                    throw new java.net.ConnectException("Connection refused");
                }
                f.sync();
                channel = f.channel();
                channel.closeFuture().addListener((ChannelFutureListener) (ChannelFuture future) -> {
                    System.out.println("Потеряно соединение с сервером");
                    CloseConnection();
                });
                form.SetConnection(true);
                channel.closeFuture().sync();
            } catch (java.net.ConnectException | InterruptedException e) {
                System.err.println(e.getMessage());
            } finally {
                form.SetConnection(false);
                workerGroup.shutdownGracefully();
            }
        });
        clientThread.setName("client-thread");
        clientThread.setDaemon(true);
        clientThread.start();
    }
}