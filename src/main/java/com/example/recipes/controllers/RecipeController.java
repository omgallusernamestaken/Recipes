package com.example.recipes.controllers;

import com.example.recipes.entities.Recipe;
import com.example.recipes.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/recipes")
@RestController
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/all")
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/recipe/{namePart}")
    public List<Recipe> getRecipesThatNameContains(@PathVariable String namePart) {
        return recipeService.getRecipesThatNameContains(namePart);
    }


    @GetMapping("/recipe/ingredients/{ingredientName}")
    public List<Recipe> getRecipesWithIngredient(@PathVariable String ingredientName) {
        return recipeService.getRecipesWithIngredient(ingredientName);
    }
}
