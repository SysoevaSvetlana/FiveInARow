package ru.vsu.fcs.oop;

public interface PlayerStrategy {
    interface Factory {
        PlayerStrategy create();
    }

    void makeMove(PlayerBoard pb, MoveMaker mm);

    default void gameOver(boolean isWin) {}
}
