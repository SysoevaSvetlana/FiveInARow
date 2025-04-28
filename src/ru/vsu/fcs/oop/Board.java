package ru.vsu.fcs.oop;

public class Board implements Boardable<Sign> {
    private Sign[][] b;

    public Board(int width, int height) {
        b = new Sign[height][width];
    }

    @Override
    public int getWidth() {
        return b[0].length;
    }

    @Override
    public int getHeight() {
        return b.length;
    }

    @Override
    public Sign get(int row, int col) {
        return b[row][col];
    }

    @Override
    public void set(int row, int col, Sign value) {
        b[row][col] = value;
    }
}