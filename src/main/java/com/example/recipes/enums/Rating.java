package com.example.recipes.enums;

public enum Rating {
    ONE_STAR("⭐"),
    TWO_STARS( "⭐⭐"),
    THREE_STARS( "⭐⭐⭐"),
    FOUR_STARS( "⭐⭐⭐⭐"),
    FIVE_STARS("⭐⭐⭐⭐⭐");

    private final String stars;

    Rating(String stars){
        this.stars = stars;
    }

    public String getStars() {
        return stars;
    }
}
