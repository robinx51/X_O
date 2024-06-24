package com.x.o_client;

import io.netty.channel.*;
import java.util.Scanner;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        Scanner console = new Scanner(System.in);
//        while (true) {
//            String str = console.nextLine();
//            if ("stop".equals(str) || "стоп".equals(str)) {
//                break;
//            } else {
//                RequestData msg = new RequestData();
//                msg.setIntValue(Integer.parseInt(str));
//                msg.setStringValue(
//                  "all work and no play makes jack a dull boy");
//                ChannelFuture future = ctx.writeAndFlush(msg);
//            }
//        }

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
}
