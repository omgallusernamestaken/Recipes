package com.example.recipes.controllers;

import com.example.recipes.entities.Ingredient;
import com.example.recipes.entities.Recipe;
import com.example.recipes.entities.RecipeIngredient;
import com.example.recipes.entities.RecipeTag;
import com.example.recipes.exceptions.RecipeNotFoundException;
import com.example.recipes.services.IngredientService;
import com.example.recipes.services.OpinionService;
import com.example.recipes.services.RecipeService;
import com.example.recipes.services.RecipeTagService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @Autowired
    private OpinionService opinionService;

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

    @GetMapping(value = "/search", params = "tagName")
    public String getRecipesWithTag(@RequestParam String tagName, Model model) {
        List<Recipe> recipeList = recipeService.getRecipesWithTag(tagName);
        model.addAttribute("recipes", recipeList);
        return "recipe/recipes_list";
    }

    @GetMapping("/{id}")
    public String getRecipeById(@PathVariable Long id, Model model) throws RecipeNotFoundException {
        Recipe recipe = recipeService.getRecipeById(id);
        model.addAttribute("recipe", recipe);
        model.addAttribute("opinions", opinionService.getThreeLatestOpinionsForRecipe(id));
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
    public String addRecipe(@ModelAttribute @Valid Recipe recipe,
                            BindingResult bindingResult,
                            @RequestParam List<Long> recipeTags,
                            @RequestParam String ingredientMap,
                            Model model) throws IOException {

        buildMapperForRecipeIngredients(ingredientMap, recipe);

        if (bindingResult.hasErrors()) {
            prepareFormModel(model, ingredientMap);
            return "recipe/recipe_add";
        }

        List<RecipeTag> selectedTags = recipeTagService.getAllByIdsList(recipeTags);
        recipe.setRecipeTags(selectedTags);

        recipeService.addRecipe(recipe);

        return "redirect:/recipes";
    }

    @GetMapping("/update/{id}")
    public String showAddRecipeFormForUpdate(@PathVariable("id") long id, Model model) throws RecipeNotFoundException {
        model.addAttribute("recipe", recipeService.getRecipeById(id));
        model.addAttribute("allTags", recipeTagService.getAllTags());
        model.addAttribute("isEdit", true);

        List<Ingredient> allIngredients = ingredientService.getAllIngredients();
        model.addAttribute("allIngredients", allIngredients);

        return "recipe/recipe_add";
    }

    @PostMapping("/update")
    public String updateRecipe(@ModelAttribute @Valid Recipe recipe,
                               BindingResult bindingResult,
                               @RequestParam List<Long> recipeTags,
                               @RequestParam String ingredientMap,
                               Model model) throws IOException {

        buildMapperForRecipeIngredients(ingredientMap, recipe);

        if (bindingResult.hasErrors()) {
            prepareFormModel(model, ingredientMap);
            model.addAttribute("isEdit", true);
            return "recipe/recipe_add";
        }

        recipeService.removeRecipeIngredients(recipe.getId());

        List<RecipeTag> selectedTags = recipeTagService.getAllByIdsList(recipeTags);
        recipe.setRecipeTags(selectedTags);

        recipeService.addRecipe(recipe);

        return "redirect:/recipes";
    }

    @GetMapping("/delete/{id}")
    public String deleteRecipe(@PathVariable long id) {
        recipeService.deleteRecipeById(id);
        return "redirect:/recipes";
    }

    private void prepareFormModel(Model model,String ingredientMap) {
        model.addAttribute("allTags", recipeTagService.getAllTags());
        model.addAttribute("allIngredients", ingredientService.getAllIngredients());
        model.addAttribute("ingredientMap", ingredientMap);
    }

    private void buildMapperForRecipeIngredients(String ingredientMap, Recipe recipe) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<Long, Integer> ingredientsMap = objectMapper.readValue(ingredientMap, new TypeReference<Map<Long, Integer>>() {});

        List<RecipeIngredient> recipeIngredients = createRecipeIngredients(recipe, ingredientsMap);
        recipe.setRecipeIngredients(recipeIngredients);
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
}