package com.testxo;

import java.util.Scanner;

public class TestXO {

    public static void main(String[] args) {
        String player1 = "Nikita", player2 = "Dima";
        Game testGame = new Game(player1, player2);
        
        int queue = 0;
        Scanner console = new Scanner(System.in);
        while(true) {
            testGame.print();
            System.out.print("Сделайте ход: ");
            int str = Integer.parseInt(console.nextLine());
            
            System.out.println("\n");
            
            int request;
            if (++queue % 2 == 1) request = testGame.setScore(str, player1);
            else request = testGame.setScore(str, player2);
            
            switch (request) {
                case 1  -> System.out.println("Победа игрока " + player1);
                case 2 -> System.out.println("Победа игрока " + player2);
                case 3 -> {
                    System.out.println("Ничья!"); 
                    testGame = new Game(player1, player2);
                }
                case -1 -> System.out.println("Ячейчка занята");
                case -2 -> System.out.println("Неправильные данные");
                default -> {
                }
            }
        }
    }
}
