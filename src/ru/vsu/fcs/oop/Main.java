package ru.vsu.fcs.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board board = new Board(6, 6);
        PlayerStrategy.Factory[] factories = new PlayerStrategy.Factory[] {
                KBStrategy.FACTORY,
                RandomStrategy.FACTORY,
                Window.FACTORY

        };

        Scanner scan = new Scanner(System.in);

        List<Player> playerList = new ArrayList<>();

        Sign[] signs = new Sign[] {Sign.X, Sign.O};
        for (int i = 0; i < 2; i++) {
            System.out.println("Enter name for " + (i == 0 ? "First" : "Second") + " player:");
            String playerName = scan.next();

            int strategyIndex;
            do {
                System.out.println("Choose strategy for " + playerName + " (0 - KBStrategy, 1 - RandomStrategy , 2 - WindowStrategy):");
                strategyIndex = scan.nextInt();
                scan.nextLine();
            } while (strategyIndex < 0 || strategyIndex >= factories.length);

            playerList.add(new Player(playerName, factories[strategyIndex].create(), signs[i]));
            System.out.println(playerName + " has been created with strategy " + strategyIndex);
        }

        Game game = new Game(board, playerList.get(0), playerList.get(1));
        game.start();
    }
}
