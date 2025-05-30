package ru.vsu.fcs.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomStrategy implements PlayerStrategy {

    public static final Factory FACTORY = new Factory() {
        @Override
        public PlayerStrategy create() {
            return new RandomStrategy();
        }
    };
    private final static Random rnd = new Random();
    @Override
    public void makeMove(PlayerBoard pb, MoveMaker mm) {
        List<Point> available = new ArrayList<>();
        System.out.println("====");
        for (int i = 0; i < pb.getHeight(); i++) {
            for (int j = 0; j < pb.getWidth(); j++) {
                Cell c = pb.getCell(j, i);
                if (Cell.EMPTY.equals(c)) {
                    System.out.print(".");
                    available.add(new Point(j, i));
                } else if (Cell.MY.equals(c)) {
                    System.out.print("M");
                } else {
                    System.out.print("O");
                }
            }
            System.out.println();
        }

        if (available.size() == 0)
            return;
        mm.makeMove(available.get(rnd.nextInt(available.size())));
    }
}