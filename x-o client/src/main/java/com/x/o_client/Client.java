package com.x.o_client;

import com.x.o_client.data.RequestData;
import com.x.o_client.data.ResponseData;
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
import java.util.Scanner;

public class Client {
    private Channel channel;
    private Thread workThread;
    private final Form form;
    
    public Client(Form form) {
        this.form = form;
    }
    
    public void CloseConnection() {
        if (channel != null) {
            System.out.println("Closing server...");
            channel.close();
            channel = null;
        }
    }
    
    private void workLoop() {
        workThread = new Thread(() -> {
            Scanner console = new Scanner(System.in);
            while (channel.isOpen()) {
                String str = console.nextLine();
                if ("stop".equals(str) || "стоп".equals(str)) {
                    CloseConnection();
                    break;
                } else {
                    RequestData msg = new RequestData();
                    msg.setIntValue(Integer.parseInt(str));
                    msg.setStringValue("all works");
                    channel.writeAndFlush(msg);
                }
            }
        });
        workThread.setName("work-thread");
        workThread.start();
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

        @Override
        protected void decode(ChannelHandlerContext ctx, 
            ByteBuf in, List<Object> out) throws Exception {

            ResponseData data = new ResponseData();
            data.setIntValue(in.readInt());
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
                          new ResponseDataDecoder(), new ServerHandler());
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
                channel.closeFuture().sync();
            } catch (java.net.ConnectException | InterruptedException e) {
                System.err.println(e.getMessage());
            } finally {
                workerGroup.shutdownGracefully();
            }
        });
        clientThread.setName("client-thread");
        clientThread.setDaemon(true);
        clientThread.start();
    }
    
//    public static void main(String[] args) {
//        new Client().run();
//        new Form().setVisible(true);
//        try {
//            Thread.sleep(2000);
//            workLoop();
//        } catch (InterruptedException ex) {
//            System.err.println(ex.getMessage());
//        }
//    }
}