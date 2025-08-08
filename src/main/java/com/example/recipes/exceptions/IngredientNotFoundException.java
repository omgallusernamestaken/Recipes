package com.example.recipes.exceptions;

public class IngredientNotFoundException extends ItemNotFoundException {
    public IngredientNotFoundException(long id) {
        super("Nie znaleziono składnika o ID: " + id);
    }
}
