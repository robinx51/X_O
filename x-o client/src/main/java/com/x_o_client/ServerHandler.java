package com.x_o_client;

import com.x_o_client.data.RequestData;
import com.x_o_client.data.ResponseData;
import io.netty.channel.*;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    private final Form form;
    
    public ServerHandler(Form form) {
        this.form = form;
    }
    
    private void HandleMessage(ResponseData msg) {
        form.SetIsYourStep(msg.getBoolValue());
        switch (msg.getStringValue()) {
            case "newGame": {
                form.SetFigure(msg.getOtherStringValue());
                form.SetIsYourStep(msg.getBoolValue());
                form.InitFields();
                break;
            } case "setField": {
                form.SetField(msg.getIntValue(), msg.getOtherStringValue());
                break;
            } case "win": {
                form.SetField(msg.getIntValue(), form.figure);
                form.SetVictory(msg.getOtherStringValue(), 1);
                break;
            } case "lose": {
                form.SetField(msg.getIntValue(), form.figureOpp);
                form.SetVictory(msg.getOtherStringValue(), 0);
                break;
            } case "draw": {
                form.SetField(msg.getIntValue(), msg.getOtherStringValue());
                form.SetVictory(msg.getOtherStringValue(), 2);
                break;
            } case "wait": {
                form.SetLabel("Подождите очереди", "black");
                break;
            }
        }
    }
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        RequestData msg = new RequestData();
        msg.setStringValue(
          "Hello, server!");
        ctx.writeAndFlush(msg);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ResponseData value = (ResponseData) msg;
        System.out.println(value.getStringValue() + " " + value.getOtherStringValue() 
                + " " + value.getIntValue() + " " + value.getBoolValue());
        HandleMessage(value);
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println(cause.getMessage());
    }
}
