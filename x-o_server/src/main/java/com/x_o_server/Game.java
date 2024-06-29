package com.x_o_server;

import io.netty.channel.Channel;
import java.util.SortedMap;
import java.util.TreeMap;

public class Game {
    private final SortedMap<Channel, Integer> playersList;
    private final int[][] fields;
    private int steps;
    private String winType;
    
    public Game(Channel firstPlayer, Channel secondPlayer){
        playersList = new TreeMap<>();
        playersList.put(firstPlayer, 1);
        playersList.put(secondPlayer, 2);
        fields = new int[][] {{0,0,0}, {0,0,0}, {0,0,0}};
        steps = 0;
    }
    
    public Channel getTurn() {
        if (steps % 2 == 0)
            return playersList.firstKey();
        else return playersList.lastKey();
    }
    public String getFigure(Channel channel) {
        if (playersList.get(channel) == 1) 
            return "x";
        else 
            return "o";
    }
    public String getWinType() { return winType; }
    public String getFields() {
        String fieldsStr = "";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                fieldsStr += fields[i][j];
            }
        }
        return fieldsStr;
    }
    
    public int setScore(int field, Channel player) { // field = 0..8
        int row = (field / 3);
        int column = (field % 3);
        
        if (field < 0 || field > 8) 
            return -2; // out of range
        
        int score = playersList.get(player);
        
        if (fields[row][column] == 0) {
            fields[row][column] = score;
            if (checkColumn(column, score) || checkRow(row, score) || checkDiagonal(score))
                return 1; // победа
            else {
                if (++steps == 9) 
                    return 2; // ничья
                else return 0; // ход сделан
            }
        }
        else return -1; // позиция занята
    }
    
    private boolean checkRow(int row, int score) {
        boolean value = fields[row][0] == score && fields[row][1] == score && fields[row][2] == score;
        if (value)
            winType = "h" + (row + 1);
        return value;
    }
    
    private boolean checkColumn(int column, int score) {
        boolean value = fields[0][column] == score && fields[1][column] == score && fields[2][column] == score;
        if (value)
            winType = "v" + (column + 1);
        return value;
    }
    
    private boolean checkDiagonal(int score) {
        boolean diagonal[] = {fields[0][0] == score && fields[1][1] == score && fields[2][2] == score,
                               fields[0][2] == score && fields[1][1] == score && fields[2][0] == score};
        
        for (int i = 0; i < 2; i++) {
            if (diagonal[i])
                winType = "c" + (i + 1);
        }
        return diagonal[0] || diagonal[1];
    }
}
