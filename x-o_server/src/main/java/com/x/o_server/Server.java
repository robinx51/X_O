package com.x.o_server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.ReplayingDecoder;
import java.nio.charset.Charset;
import java.util.List;

public class Server {
    private final static int PORT = 8080;
    
    private class RequestDecoder extends ReplayingDecoder<RequestData> {

        private final Charset charset = Charset.forName("UTF-8");

        @Override
        protected void decode(ChannelHandlerContext ctx, 
          ByteBuf in, List<Object> out) throws Exception {

            RequestData data = new RequestData();
            data.setIntValue(in.readInt());
            int strLen = in.readInt();
            data.setStringValue(
              in.readCharSequence(strLen, charset).toString());
            out.add(data);
        }
    }
    
    private class ResponseDataEncoder extends MessageToByteEncoder<ResponseData> {
        @Override
        protected void encode(ChannelHandlerContext ctx, 
            ResponseData msg, ByteBuf out) throws Exception {
            out.writeInt(msg.getIntValue());
        }
    }

    public static void main(String[] args) throws Exception {
        new Server().run();
    }
    
    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
              .channel(NioServerSocketChannel.class)
              .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new RequestDecoder(), 
                      new ResponseDataEncoder(), 
                      new ClientHandler());
                }
            }).option(ChannelOption.SO_BACKLOG, 128)
              .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture f = b.bind(PORT).sync();
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
