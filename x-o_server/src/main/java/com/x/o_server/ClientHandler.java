package com.x.o_server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;

public class ClientHandler extends ChannelInboundHandlerAdapter {
    private ByteBuf tmp;
    private String id;
    
    private void HandleMessage(ChannelHandlerContext ctx, RequestData requestData) {
        ResponseData responseData = new ResponseData();
        responseData.setIntValue(requestData.getIntValue() * 2);
        ctx.writeAndFlush(responseData);
        System.out.println(id + ": " + requestData.getIntValue() + " | " + requestData.getStringValue());
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        id = ctx.channel().remoteAddress().toString().substring(11);
        System.out.println("New client, id: " + id);
        tmp = ctx.alloc().buffer(4);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        System.out.println("Client " + id + " exit");
        tmp.release();
        tmp = null;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        HandleMessage(ctx, (RequestData) msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println(cause.getMessage());
    }
}
