package com.example.recipes.enums;

public enum Unit {
    GRAM("g"),
    MILLILITER("ml"),
    PIECE("szt");

    private final String symbol;

    Unit(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}