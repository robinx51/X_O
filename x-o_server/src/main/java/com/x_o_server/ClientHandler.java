package com.x_o_server;

import com.x_o_server.data.RequestData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;

public class ClientHandler extends ChannelInboundHandlerAdapter {
    private ByteBuf tmp;
    private String id;
    private final ServerProcess process;
    
    public ClientHandler(ServerProcess process) {
        this.process = process;
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        id = ctx.channel().remoteAddress().toString().substring(11);
        System.out.println("New client, id: " + id);
        process.AddClient(ctx.channel());
        if (process.GetListSize() == 2) {
            process.NewGame();
        } else if (process.GetListSize() > 2) {
            ctx.close();
        }
        tmp = ctx.alloc().buffer(4);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        System.out.println("Client " + id + " exit");
        process.DeleteClient(ctx.channel());
        tmp.release();
        tmp = null;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        process.HandleMessage(ctx, (RequestData) msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println(cause.getMessage());
    }
}
