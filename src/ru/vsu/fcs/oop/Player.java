package ru.vsu.fcs.oop;

public class Player {
    private final String name;
    private final PlayerStrategy strategy;
    private final Sign sign;

    public Player(String name, PlayerStrategy strategy, Sign sign) {
        this.name = name;
        this.strategy = strategy;
        this.sign = sign;
    }

    public String getName() {
        return name;
    }

    public PlayerStrategy getStrategy() {
        return strategy;
    }

    public Sign getSign() {
        return sign;
    }
}
