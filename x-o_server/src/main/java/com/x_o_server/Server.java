package com.x_o_server;

import com.x_o_server.data.ResponseData;
import com.x_o_server.data.RequestData;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.ReplayingDecoder;
import java.nio.charset.Charset;
import java.util.*;

public class Server {
    private final int PORT;
    private final ServerProcess process;
    private static Channel serverChannel;
    
    public Server() {
        PORT = 8080;
        process = new ServerProcess();
    }
    
    private static void workLoop() {
        Scanner console = new Scanner(System.in);
        while (true) {
            String str = console.nextLine();
            if ("stop".equals(str) || "стоп".equals(str)) {
                StopServer();
                break;
            }
        }
    }
    
    private static void StopServer() {
        if (serverChannel != null) {
            System.out.println("Closing server...");
            serverChannel.close();
            serverChannel = null;
        }
    }
    
    private class RequestDecoder extends ReplayingDecoder<RequestData> {
        private final Charset charset = Charset.forName("UTF-8");

        @Override
        protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
            RequestData data = new RequestData();
            data.setIntValue(in.readInt());
            int strLen = in.readInt();
            data.setStringValue(in.readCharSequence(strLen, charset).toString());
            out.add(data);
        }
    }
    
    private class ResponseDataEncoder extends MessageToByteEncoder<ResponseData> {
        private final Charset charset = Charset.forName("UTF-8");

        @Override
        protected void encode(ChannelHandlerContext ctx, ResponseData msg, ByteBuf out) throws Exception {
            out.writeInt(msg.getIntValue());
            out.writeInt(msg.getStringValue().length());
            out.writeCharSequence(msg.getStringValue(), charset);
            out.writeInt(msg.getOtherStringValue().length());
            out.writeCharSequence(msg.getOtherStringValue(), charset);
            out.writeBoolean(msg.getBoolValue());
        }
    }
    
    public void run() {
        Thread serverThread = new Thread(() -> {
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
                                new ClientHandler(process));
                    }
                }).option(ChannelOption.SO_BACKLOG, 128)
                  .childOption(ChannelOption.SO_KEEPALIVE, true);

                ChannelFuture channelFuture = b.bind(PORT).sync();
                serverChannel = channelFuture.channel();
                System.out.println("Server started on " + PORT);
                
                serverChannel.closeFuture().sync();
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            } finally {
                workerGroup.shutdownGracefully();
                bossGroup.shutdownGracefully();
            }
        });
        serverThread.setName("server-thread");
        serverThread.setDaemon(true);
        serverThread.start();
    }
    
    public static void main(String[] args) {
        new Server().run();
        workLoop();
    }
}
