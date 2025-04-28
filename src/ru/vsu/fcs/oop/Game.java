package ru.vsu.fcs.oop;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Boardable<Sign> board;
    private List<Player> players = new ArrayList<>();
    private int currentPlayerIndex = 0;
    private int stepsCount = 0;
    private final int WIN_CONDITION = 5; // Количество знаков подряд для победы

    private boolean gameOver = false;

    private Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    private void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        stepsCount++;
    }

    public Game(Boardable<Sign> board, Player a, Player b) {
        this.board = board;
        players.add(a);
        players.add(b);
    }

    private static class PBoard implements PlayerBoard {

        private final Boardable<Sign> board;
        private final Sign sign;

        public PBoard(Boardable<Sign> board, Sign sign) {
            this.board = board;
            this.sign = sign;
        }

        @Override
        public int getWidth() {
            return board.getWidth();
        }

        @Override
        public int getHeight() {
            return board.getHeight();
        }

        @Override
        public Cell getCell(Point p) {
            Sign s = board.get(p.getY(), p.getX());
            if (s == null)
                return Cell.EMPTY;
            if (s == sign)
                return Cell.MY;
            return Cell.ENEMY;
        }
    }

    private class MakeMoveImpl implements MoveMaker {

        @Override
        public void makeMove(Point p) {
            Player pl = getCurrentPlayer();
            if (p != null && isValidMove(p)) {
                board.set(p.getY(), p.getX(), pl.getSign());
                if (checkWin(p, pl.getSign())) {
                    System.out.println("ru.vsu.fcs.oop.Player " + pl.getName() + " wins!");
                    gameOver = true;
                    return;
                }
                if (isGameOver()) {
                    System.out.println("The game is a draw!");
                    gameOver = true;
                    return;
                }
                nextPlayer();
            }
            if (isGameOver())
                return;

            pl = getCurrentPlayer();
            PBoard pb = new PBoard(board, pl.getSign());
            getCurrentPlayer().getStrategy().makeMove(pb, this);
        }
    }

    public void start() {
        //while (gameOver!=true){
        Player pl = getCurrentPlayer();
            PBoard pb = new PBoard(board, pl.getSign());
            getCurrentPlayer().getStrategy().makeMove(pb, new MakeMoveImpl());
       // }
    }

    public boolean isGameOver() {
        return stepsCount >= (board.getWidth() * board.getHeight());
    }

    private boolean isValidMove(Point p) {
        return p.getX() >= 0 && p.getX() < board.getWidth() &&
                p.getY() >= 0 && p.getY() < board.getHeight() &&
                board.get(p.getY(), p.getX()) == null;
    }

    private boolean checkWin(Point lastMove, Sign sign) {
        int x = lastMove.getX();
        int y = lastMove.getY();

        // Проверка всех направлений
        return countInDirection(x, y, 1, 0, sign) + countInDirection(x, y, -1, 0, sign) - 1 >= WIN_CONDITION || // Горизонталь
                countInDirection(x, y, 0, 1, sign) + countInDirection(x, y, 0, -1, sign) - 1 >= WIN_CONDITION || // Вертикаль
                countInDirection(x, y, 1, 1, sign) + countInDirection(x, y, -1, -1, sign) - 1 >= WIN_CONDITION || // Диагональ \
                countInDirection(x, y, 1, -1, sign) + countInDirection(x, y, -1, 1, sign) - 1 >= WIN_CONDITION;  // Диагональ /
    }

    private int countInDirection(int startX, int startY, int dx, int dy, Sign sign) {
        int count = 0;
        int x = startX;
        int y = startY;

        while (x >= 0 && x < board.getWidth() &&
                y >= 0 && y < board.getHeight() &&
                board.get(y, x) == sign) {
            count++;
            x += dx;
            y += dy;
        }
        return count;
    }
}
