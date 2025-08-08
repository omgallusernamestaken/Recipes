package com.example.recipes.enums;

public enum Difficulty {
    EASY("easy"),
    INTERMEDIATE("intermediate"),
    HARD("hard");

    private final String symbol;

    Difficulty(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
