package com.example.recipes.controllers;

import com.example.recipes.entities.Ingredient;
import com.example.recipes.entities.Recipe;
import com.example.recipes.entities.RecipeIngredient;
import com.example.recipes.entities.RecipeTag;
import com.example.recipes.services.IngredientService;
import com.example.recipes.services.RecipeService;
import com.example.recipes.services.RecipeTagService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("/recipes")
@Controller
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private RecipeTagService recipeTagService;

    @GetMapping()
    public String getAllRecipes(Model model) {
        List<Recipe> recipeList = recipeService.getAllRecipes();
        model.addAttribute("recipes", recipeList);
        return "recipe/recipes_list";
    }

    @GetMapping(value = "/search", params = "name")
    public String getRecipesThatNameContains(@RequestParam String name, Model model) {
        List<Recipe> recipeList = recipeService.getRecipesThatNameContains(name);
        model.addAttribute("recipes", recipeList);
        return "recipe/recipes_list";
    }

    @GetMapping(value = "/search", params = "ingredientName")
    public String getRecipesWithIngredient(@RequestParam String ingredientName, Model model) {
        List<Recipe> recipeList = recipeService.getRecipesWithIngredientName(ingredientName);
        model.addAttribute("recipes", recipeList);
        return "recipe/recipes_list";
    }

    @GetMapping(value = "/search", params = "categoryName")
    public String getRecipesWithCategory(@RequestParam String categoryName, Model model) {
        List<Recipe> recipeList = recipeService.getRecipesWithTagCategory(categoryName);
        model.addAttribute("recipes", recipeList);
        return "recipe/recipes_list";
    }

    @GetMapping("/{id}")
    public String getRecipeById(@PathVariable Long id, Model model) {
        Recipe recipe = recipeService.getRecipeById(id);
        model.addAttribute("recipe", recipe);
        return "recipe/recipe_template";
    }

    @GetMapping("/add")
    public String showAddRecipeForm(Model model) {
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("allTags", recipeTagService.getAllTags());

        List<Ingredient> allIngredients = ingredientService.getAllIngredients();
        model.addAttribute("allIngredients", allIngredients);
        return "recipe/recipe_add";
    }

    @PostMapping("/add")
    public String addRecipe(@ModelAttribute Recipe recipe,
                            @RequestParam List<Long> recipeTags,
                            @RequestParam String ingredientMap) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        Map<Long, Integer> ingredientsMap = objectMapper.readValue(ingredientMap, new TypeReference<Map<Long, Integer>>() {});

        List<RecipeTag> selectedTags = recipeTagService.getAllByIdsList(recipeTags);
        recipe.setRecipeTags(selectedTags);

        List<RecipeIngredient> recipeIngredients = createRecipeIngredients(recipe, ingredientsMap);
        recipe.setRecipeIngredients(recipeIngredients);

        recipeService.addRecipe(recipe);

        return "redirect:/recipes";
    }

    private List<RecipeIngredient> createRecipeIngredients(Recipe recipe, Map<Long, Integer> ingredientsMap) {
        List<RecipeIngredient> recipeIngredients = new ArrayList<>();

        for (Map.Entry<Long, Integer> entry : ingredientsMap.entrySet()) {
            Long ingredientId = entry.getKey();
            Integer quantity = entry.getValue();

            Ingredient ingredient = ingredientService.getIngredientById(ingredientId).orElseThrow();
            RecipeIngredient recipeIngredient = new RecipeIngredient();
            recipeIngredient.setRecipe(recipe);
            recipeIngredient.setIngredient(ingredient);
            recipeIngredient.setQuantity(quantity);

            recipeIngredients.add(recipeIngredient);
        }
        return recipeIngredients;
    }

    @GetMapping("/update/{id}")
    public String showAddRecipeFormForUpdate(@PathVariable("id") long id, Model model) {
        model.addAttribute("recipe", recipeService.getRecipeById(id));
        model.addAttribute("allTags", recipeTagService.getAllTags());
        model.addAttribute("isEdit", true);

        List<Ingredient> allIngredients = ingredientService.getAllIngredients();
        model.addAttribute("allIngredients", allIngredients);

        return "recipe/recipe_add";
    }

    @PostMapping("/update")
    public String updateRecipe(@ModelAttribute Recipe recipe,
                            @RequestParam List<Long> recipeTags,
                            @RequestParam String ingredientMap) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        Map<Long, Integer> ingredientsMap = objectMapper.readValue(ingredientMap, new TypeReference<Map<Long, Integer>>() {});

        recipeService.removeRecipeIngredients(recipe.getId());

        List<RecipeTag> selectedTags = recipeTagService.getAllByIdsList(recipeTags);
        recipe.setRecipeTags(selectedTags);

        List<RecipeIngredient> recipeIngredients = createRecipeIngredients(recipe, ingredientsMap);
        recipe.setRecipeIngredients(recipeIngredients);

        recipeService.addRecipe(recipe);

        return "redirect:/recipes";
    }

    @GetMapping("/delete/{id}")
    public String deleteRecipe(@PathVariable long id) {
        recipeService.deleteRecipeById(id);
        return "redirect:/recipes";
    }
}