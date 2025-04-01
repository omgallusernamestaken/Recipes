package com.example.recipes.controllers;

import com.example.recipes.entities.Ingredient;
import com.example.recipes.entities.Recipe;
import com.example.recipes.entities.RecipeIngredient;
import com.example.recipes.entities.RecipeTag;
import com.example.recipes.services.IngredientService;
import com.example.recipes.services.RecipeService;
import com.example.recipes.services.RecipeTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/recipes")
@Controller
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private RecipeTagService recipeTagService;

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

    @GetMapping("/add")
    public String showAddRecipeForm(Model model) {
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("allTags", recipeTagService.findAllTags());

        List<Ingredient> allIngredients = ingredientService.getAllIngredients();
        model.addAttribute("allIngredients", allIngredients);
        return "recipe_add";
    }

    @PostMapping("/add")
    public String addRecipe(@ModelAttribute Recipe recipe,
                            @RequestParam List<Long> recipeTags,
                            @RequestParam List<Long> ingredientIds,
                            @RequestParam List<Integer> quantities) {

        List<RecipeTag> selectedTags = recipeTagService.findAllById(recipeTags);
        recipe.setRecipeTags(selectedTags);

        List<RecipeIngredient> recipeIngredients = createRecipeIngredients(recipe, ingredientIds, quantities);
        recipe.setRecipeIngredients(recipeIngredients);

        recipeService.addRecipe(recipe);

        return "redirect:/recipes/all";
    }

    private List<RecipeIngredient> createRecipeIngredients(Recipe recipe, List<Long> ingredientIds, List<Integer> quantities) {
        List<RecipeIngredient> recipeIngredients = new ArrayList<>();

        for (int i = 0; i < ingredientIds.size(); i++) {
            Long ingredientId = ingredientIds.get(i);
            Integer quantity = quantities.get(i);

            Ingredient ingredient = ingredientService.getById(ingredientId).orElseThrow();
            RecipeIngredient recipeIngredient = new RecipeIngredient();
            recipeIngredient.setRecipe(recipe);
            recipeIngredient.setIngredient(ingredient);
            recipeIngredient.setQuantity(quantity);

            recipeIngredients.add(recipeIngredient);
        }
        return recipeIngredients;
    }
}
