package com.example.recipes.services;

import com.example.recipes.entities.Ingredient;
import com.example.recipes.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public Optional<Ingredient> getByName(String ingredientName) {
        return ingredientRepository.findByIngredientName(ingredientName);
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }
}
