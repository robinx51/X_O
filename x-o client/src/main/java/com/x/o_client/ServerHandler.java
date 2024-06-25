package com.x.o_client;

import com.x.o_client.data.RequestData;
import com.x.o_client.data.ResponseData;
import io.netty.channel.*;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        RequestData msg = new RequestData();
        msg.setIntValue(123);
        msg.setStringValue(
          "all work and no play makes jack a dull boy");
        ChannelFuture future = ctx.writeAndFlush(msg);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ResponseData value = (ResponseData) msg;
        System.out.println(value.getIntValue());
        //ctx.close();
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println(cause.getMessage());
    }
}
