package com.x_o_client;

import com.x_o_client.data.RequestData;
import com.x_o_client.data.ResponseData;
import io.netty.channel.*;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        RequestData msg = new RequestData();
        msg.setIntValue(0);
        msg.setStringValue(
          "Hello, server!");
        ctx.writeAndFlush(msg);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ResponseData value = (ResponseData) msg;
        System.out.println(value.getStringValue());
        //ctx.close();
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println(cause.getMessage());
    }
}
