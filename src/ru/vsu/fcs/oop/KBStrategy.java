package ru.vsu.fcs.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class KBStrategy implements PlayerStrategy {
    public static final Factory FACTORY = new Factory() {
        @Override
        public PlayerStrategy create() {
            return new KBStrategy();
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

        Scanner scan = new Scanner(System.in);
        int r, c;
        do {
            System.out.println("Enter row and column (e.g., 0 1):");
            r = scan.nextInt();
            c = scan.nextInt();
        } while (r < 0 || r >= pb.getHeight() || c < 0 || c >= pb.getWidth() || !pb.isEmpty(new Point(c, r)));

        mm.makeMove(new Point(c, r));
    }
}