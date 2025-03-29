package com.example.recipes.controllers;

import com.example.recipes.entities.Ingredient;
import com.example.recipes.entities.Recipe;
import com.example.recipes.services.IngredientService;
import com.example.recipes.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequestMapping("/recipes")
@RestController
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private IngredientService ingredientService;

    @GetMapping("/all")
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/recipe/{namePart}")
    public List<Recipe> getRecipesThatNameContains(@PathVariable String namePart) {
        return recipeService.getRecipesThatNameContains(namePart);
    }

    @GetMapping("/ingredients/{ingredientName}")
    public List<Recipe> getRecipesWithIngredient(@PathVariable String ingredientName) {
        Ingredient ingredient = ingredientService.getByName(ingredientName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found"));

        return recipeService.getRecipesWithIngredient(ingredient);
    }
}
