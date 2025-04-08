package com.example.recipes.exceptions;

public class OpinionNotFoundException extends ItemNotFoundException{
    public OpinionNotFoundException(long id) {
        super("Nie znaleziono opinii o ID: " + id);
    }
}
