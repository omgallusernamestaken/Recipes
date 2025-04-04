package com.example.recipes.services;

import com.example.recipes.entities.Ingredient;
import com.example.recipes.entities.Recipe;
import com.example.recipes.entities.RecipeIngredient;
import com.example.recipes.repositories.RecipeIngredientRepository;
import com.example.recipes.repositories.RecipesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    @Autowired
    private RecipesRepository recipesRepository;

    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;

    @Autowired
    private OpinionService opinionService;

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

    public void updateRecipe(Recipe recipe) {
        recipesRepository.save(recipe);
    }

    @Transactional
    public void removeRecipeIngredients(Long recipeId) {
        recipeIngredientRepository.deleteByRecipeId(recipeId);
    }

    public void deleteRecipeById(Long id) {
        Recipe recipeToDelete = getRecipeById(id);
        recipesRepository.delete(recipeToDelete);
    }

    public void updateRatingForRecipe(long recipeId) {
        Recipe recipe = getRecipeById(recipeId);
        double newRating = recipe.calculateAvgRating(recipe.getOpinions());
        recipe.setAvgRating(newRating);
        recipesRepository.save(recipe);
    }
}
