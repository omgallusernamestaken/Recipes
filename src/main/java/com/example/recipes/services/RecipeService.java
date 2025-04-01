package com.example.recipes.services;

import com.example.recipes.entities.Ingredient;
import com.example.recipes.entities.Recipe;
import com.example.recipes.entities.RecipeIngredient;
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

    public List<Recipe> getRecipesWithIngredient(Ingredient ingredient) {
        return getAllRecipes().stream()
                .filter(recipe -> recipe.getRecipeIngredients().stream()
                        .map(RecipeIngredient::getIngredient)
                        .anyMatch(i -> i.equals(ingredient)))
                .collect(Collectors.toList());
    }

    public Recipe getRecipeById(Long id) {
        return recipesRepository.findById(id).get();
    }

    public void addRecipe(Recipe recipe) {
        recipesRepository.save(recipe);
    }
}
