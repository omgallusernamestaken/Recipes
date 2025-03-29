package com.example.recipes.services;

import com.example.recipes.entities.Recipe;
import com.example.recipes.repositories.RecipesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    @Autowired
    private RecipesRepository recipesRepository;

    public List<Recipe> getAllRecipes() {
        return recipesRepository.findAll();
    }

    public List<Recipe> getRecipesThatNameContains(String namePart) {
        return getAllRecipes().stream()
                .filter(recipe -> recipe.getRecipeName().toLowerCase().contains(namePart.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Recipe> getRecipesWithIngredient(String ingredientName) {
        return getAllRecipes().stream()
                .filter(recipe -> recipe.getIngredientsList().stream()
                        .anyMatch(ingredient -> ingredient.getIngredientName().toLowerCase().equalsIgnoreCase(ingredientName)))
                .collect(Collectors.toList());
    }
}
