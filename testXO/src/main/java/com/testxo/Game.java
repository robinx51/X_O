package com.testxo;

import java.util.SortedMap;
import java.util.TreeMap;

public class Game {
    private final SortedMap<String, Integer> playersList;
    private final int[][] field;
    private int steps;
    
    public Game(String firstPlayer, String secondPlayer){
        playersList = new TreeMap<>();
        playersList.put(firstPlayer, 1);
        playersList.put(secondPlayer, 2);
        field = new int[][] {{0,0,0}, {0,0,0}, {0,0,0}};
        steps = 0;
    }
    
    public int setScore(int data, String player) {
        int row = (data / 3);
        int column = (data % 3);
        
        if (data < 0 || data > 8) 
            return -2; // out of range
        
        int score = playersList.get(player);
        
        if (field[row][column] == 0) {
            field[row][column] = score;
            if (checkColumn(column, score) || checkRow(row, score) || checkDiagonal(score))
                return score; // победа player(1 или 2)
            else {
                if (++steps == 9) 
                    return 3; // ничья
                else return 0; // ход сделан
            }
        }
        else return -1; // позиция занята
    }
    
    public void print() {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                System.out.print(field[row][column] + " ");
            }
            System.out.println("\n");
        }
    }
    
    private boolean checkColumn(int column, int score) {
        return (field[0][column] == score && field[1][column] == score && field[2][column] == score);
    }
    
    private boolean checkRow(int row, int score) {
        return (field[row][0] == score && field[row][1] == score && field[row][2] == score);
            
    }
    
    private boolean checkDiagonal(int score) {
        return (field[0][0] == score && field[1][1] == score && field[2][2] == score) 
                || (field[0][2] == score && field[1][1] == score && field[2][0] == score);
    }
}
