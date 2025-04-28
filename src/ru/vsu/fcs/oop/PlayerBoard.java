package ru.vsu.fcs.oop;

public interface PlayerBoard {
    int getWidth();
    int getHeight();
    Cell getCell(Point p);

    default Cell getCell(int x, int y) {
        return getCell(new Point(x, y));
    }

    default boolean isEmpty(Point p) {
        return Cell.EMPTY.equals(getCell(p));
    }
}
