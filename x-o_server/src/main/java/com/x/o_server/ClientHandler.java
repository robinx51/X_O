package com.x.o_server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;

public class ClientHandler extends ChannelInboundHandlerAdapter {
    private ByteBuf tmp;

        @Override
        public void handlerAdded(ChannelHandlerContext ctx) {
            System.out.println("Handler added");
            tmp = ctx.alloc().buffer(4);
        }

        @Override
        public void handlerRemoved(ChannelHandlerContext ctx) {
            System.out.println("Handler removed");
            tmp.release();
            tmp = null;
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            RequestData requestData = (RequestData) msg;
            ResponseData responseData = new ResponseData();
            responseData.setIntValue(requestData.getIntValue() * 2);
            ChannelFuture future = ctx.writeAndFlush(responseData);
            //future.addListener(ChannelFutureListener.CLOSE);
            System.out.println(requestData.getIntValue());
            System.out.println(requestData.getStringValue());
        }
}
