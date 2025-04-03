package com.example.recipes.enums;

public enum Rating {
    ONE_STAR(1, "⭐"),
    TWO_STARS(2, "⭐⭐"),
    THREE_STARS(3, "⭐⭐⭐"),
    FOUR_STARS(4, "⭐⭐⭐⭐"),
    FIVE_STARS(5, "⭐⭐⭐⭐⭐");

    private final int value;
    private final String stars;

    Rating(int value, String stars){
        this.value = value;
        this.stars = stars;
    }

    public int getValue() {
        return value;
    }

    public String getStars() {
        return stars;
    }

    public static Rating fromValue(int value) {
        for (Rating rating : values()) {
            if (rating.value == value) {
                return rating;
            }
        }
        throw new IllegalArgumentException("Invalid rating value: " + value);
    }
}
