package com.x_o_server;

import com.x_o_server.data.RequestData;
import com.x_o_server.data.ResponseData;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import java.util.LinkedList;

public class ServerProcess {
        private static LinkedList<Channel> list;
        private Game game;
        
        public ServerProcess() {
            list = new LinkedList<>();
        }
        
        public void HandleMessage(ChannelHandlerContext ctx, RequestData requestData) {
            Channel ch = ctx.channel();
            Channel chOpp;
            System.out.println(GetPort(ch) + ": " + requestData.getIntValue() + " | " + requestData.getStringValue());
            
            if(ch.equals(list.get(0)))
                chOpp = list.get(1);
            else 
                chOpp = list.get(0);
            switch (requestData.getStringValue()){
                case "step": {
                    if (ch.equals(game.getTurn())){
                        int field = requestData.getIntValue();
                        switch (game.setScore(field, ctx.channel())) {
                            case -1: {
                                SendMessage(ctx.channel(), "busy",requestData.getIntValue(), true);
                                break;
                            } case 0: {
                                SendMessage(ch, "setField",  game.getFigure(ch), field, false);
                                
                                SendMessage(chOpp, "setField", game.getFigure(ch),field, true);
                                break;
                            } case 1: {
                                SendMessage(ch, "win", game.getWinType(), field, false);

                                SendMessage(chOpp, "lose", game.getWinType(), field, false);
                                
                                ctx.writeAndFlush("disconnecting")
                                    .addListener(ChannelFutureListener.CLOSE);
                                chOpp.writeAndFlush("disconnecting")
                                    .addListener(ChannelFutureListener.CLOSE);
                                break;
                            } case 2: {
                                SendMessage(ch, "draw", game.getFigure(ch), field, false);
                                
                                SendMessage(chOpp, "draw",game.getFigure(ch), field, false);
                                
                                SendMessage(ch, "disconnecting");
                                SendMessage(chOpp, "disconnecting");
                                
//                                ctx.writeAndFlush("disconnecting")
//                                    .addListener(ChannelFutureListener.CLOSE);
//                                chOpp.writeAndFlush("disconnecting")
//                                    .addListener(ChannelFutureListener.CLOSE);
                                break;
                            }
                        }
                    }
                    break;
                }
            }
            
        }
        
        public void NewGame() {
            Channel player1 = list.get(0);
            Channel player2 = list.get(1);
            game = new Game(player1, player2);
            System.out.println("New game: " +  GetPort(player1) + " vs " + GetPort(player2));
            SendMessage(player1, "newGame", "x", true); // игра начинается с него
            SendMessage(player2, "newGame", "o", false);
        }
        
        public void AddClient(Channel channel) {
            list.add(channel);
        }
        public void DeleteClient(Channel channel) {
            list.remove(channel);
            if (list.size() == 1) {
                SendMessage(list.getFirst(), "leave");
                list.clear();
            }
        }
        public int GetListSize() { return list.size(); }
        
        private String GetPort(Channel channel) {
            return channel.remoteAddress().toString().substring(11);
        }
        
        public void SendMessage(Channel channel, String msg) {
            ResponseData responseData = new ResponseData();
            responseData.setStringValue(msg);
            channel.writeAndFlush(responseData).addListener(ChannelFutureListener.CLOSE);
        }
        private void SendMessage(Channel channel, String msg, int num, boolean step) {
            ResponseData responseData = new ResponseData();
            responseData.setStringValue(msg);
            responseData.setIntValue(num);
            responseData.setBoolValue(step);
            channel.writeAndFlush(responseData);
        }
        private void SendMessage(Channel channel, String msg, String otherMsg, boolean step) {
            ResponseData responseData = new ResponseData();
            responseData.setStringValue(msg);
            responseData.setOtherStringValue(otherMsg);
            responseData.setBoolValue(step);
            channel.writeAndFlush(responseData);
        }
        private void SendMessage(Channel channel, String msg, String otherMsg, int num, boolean step) {
            ResponseData responseData = new ResponseData();
            responseData.setStringValue(msg);
            responseData.setOtherStringValue(otherMsg);
            responseData.setIntValue(num);
            responseData.setBoolValue(step);
            channel.writeAndFlush(responseData);
        }
    }
