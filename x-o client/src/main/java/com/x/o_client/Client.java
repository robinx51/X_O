package com.x.o_client;

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
    public static class RequestDataEncoder extends MessageToByteEncoder<RequestData> {

        private final Charset charset = Charset.forName("UTF-8");

        @Override
        protected void encode(ChannelHandlerContext ctx, 
            RequestData msg, ByteBuf out) throws Exception {

            out.writeInt(msg.getIntValue());
            out.writeInt(msg.getStringValue().length());
            out.writeCharSequence(msg.getStringValue(), charset);
        }
    }
    
    public static class ResponseDataDecoder extends ReplayingDecoder<ResponseData> {

        @Override
        protected void decode(ChannelHandlerContext ctx, 
            ByteBuf in, List<Object> out) throws Exception {

            ResponseData data = new ResponseData();
            data.setIntValue(in.readInt());
            out.add(data);
        }
    }

    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 8080;
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
 
                @Override
                public void initChannel(SocketChannel ch) 
                  throws Exception {
                    ch.pipeline().addLast(new RequestDataEncoder(), 
                      new ResponseDataDecoder(), new ServerHandler());
                }
            });

            ChannelFuture f = b.connect(host, port).sync();
            
            {
                Scanner console = new Scanner(System.in);
                while (true) {
                    String str = console.nextLine();
                    if ("stop".equals(str) || "стоп".equals(str)) {
                        f.channel().close();
                        break;
                    } else {
                        RequestData msg = new RequestData();
                        msg.setIntValue(Integer.parseInt(str));
                        msg.setStringValue(
                          "all work and no play makes jack a dull boy");
                        f.channel().writeAndFlush(msg);
                    }
                }
            }
            
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
