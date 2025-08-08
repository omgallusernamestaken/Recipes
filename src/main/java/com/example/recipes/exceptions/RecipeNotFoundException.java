package com.example.recipes.exceptions;

public class RecipeNotFoundException extends ItemNotFoundException {

    public RecipeNotFoundException(Long id) {
        super("Nie znaleziono przepisu o ID: " + id);
    }
}
