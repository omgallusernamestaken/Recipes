package com.example.recipes.enums;

public enum TagCategory {

    MEAL("MEAL"),
    KITCHEN("KITCHEN"),
    PREFERENCES("PREFERENCES");

    private String tagCategoryName;

    TagCategory(String tagCategoryName) {
        this.tagCategoryName = tagCategoryName;
    }

    public String getTagCategoryName() {
        return tagCategoryName;
    }
}
