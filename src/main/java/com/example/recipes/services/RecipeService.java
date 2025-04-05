package com.example.recipes.services;

import com.example.recipes.entities.Ingredient;
import com.example.recipes.entities.Recipe;
import com.example.recipes.entities.RecipeIngredient;
import com.example.recipes.entities.RecipeTag;
import com.example.recipes.repositories.RecipeIngredientRepository;
import com.example.recipes.repositories.RecipesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
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

    @Autowired
    private IngredientService ingredientService;

    public List<Recipe> getAllRecipes() {
        return recipesRepository.findAll();
    }

    public List<Recipe> getRecipesThatNameContains(String namePart) {
        return getAllRecipes().stream()
                .filter(recipe -> recipe.getRecipeName().toLowerCase().contains(namePart.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Recipe> getRecipesWithIngredientName(String ingredientName) {
        Ingredient ingredient = ingredientService.getIngredientByName(ingredientName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found"));;

        return getAllRecipes().stream()
                .filter(recipe -> recipe.getRecipeIngredients().stream()
                        .map(RecipeIngredient::getIngredient)
                        .anyMatch(i -> i.equals(ingredient)))
                .collect(Collectors.toList());
    }

    public List<Recipe> getRecipesWithTagList(List<RecipeTag> tagList) {
        return getAllRecipes().stream()
                .filter(recipe -> recipe.getRecipeTags().containsAll(tagList))
                .collect(Collectors.toList());
    }

    public List<Recipe> getRecipesWithExactTagList(List<RecipeTag> tagList) {
        return getAllRecipes().stream()
                .filter(recipe -> new HashSet<>(recipe.getRecipeTags()).equals(new HashSet<>(tagList)))
                .collect(Collectors.toList());
    }

    public Recipe getRecipeById(Long id) {
        return recipesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe not found with id: " + id));
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
