package ru.vsu.fcs.oop;

public interface Boardable<T> {
    int getWidth();
    int getHeight();

    T get(int row, int col);
    void set(int row, int col, T value);
}