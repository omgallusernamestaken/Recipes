package com.example.recipes.controllers;

import com.example.recipes.entities.Ingredient;
import com.example.recipes.entities.Recipe;
import com.example.recipes.services.IngredientService;
import com.example.recipes.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequestMapping("/recipes")
@Controller
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private IngredientService ingredientService;

    @GetMapping("/all")
    public String getAllRecipes(Model model) {
        List<Recipe> recipeList = recipeService.getAllRecipes();
        model.addAttribute("recipes", recipeList);
        return "recipes_list";
    }

    @GetMapping("/recipebyname/{namePart}")
    public String getRecipesThatNameContains(@PathVariable String namePart, Model model) {
        List<Recipe> recipeList = recipeService.getRecipesThatNameContains(namePart);
        model.addAttribute("recipes", recipeList);
        return "recipes_list";
    }

    //TODO fix case sensitivity
    @GetMapping("/ingredients/{ingredientName}")
    public String getRecipesWithIngredient(@PathVariable String ingredientName, Model model) {
        Ingredient ingredient = ingredientService.getByName(ingredientName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found"));

        List<Recipe> recipeList = recipeService.getRecipesWithIngredient(ingredient);
        model.addAttribute("recipes", recipeList);
        return "recipes_list";
    }

    @GetMapping("/recipe/{id}")
    public String getRecipeById(@PathVariable Long id, Model model) {
        Recipe recipe = recipeService.getRecipeById(id);
        model.addAttribute("recipe", recipe);
        return "recipe_template";
    }


}
