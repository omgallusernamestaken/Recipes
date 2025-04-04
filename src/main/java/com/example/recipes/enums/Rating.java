package com.example.recipes.enums;

public enum Rating {
    ONE_STAR("⭐", 1),
    TWO_STARS( "⭐⭐", 2),
    THREE_STARS( "⭐⭐⭐", 3),
    FOUR_STARS( "⭐⭐⭐⭐", 4),
    FIVE_STARS("⭐⭐⭐⭐⭐",5 );

    private final String stars;
    private final int value;

    Rating(String stars, int value) {
        this.stars = stars;
        this.value = value;
    }

    public String getStars() {
        return stars;
    }

    public int getValue() {
        return value;
    }
}
