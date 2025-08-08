package com.example.recipes.controllers;

import com.example.recipes.entities.Ingredient;
import com.example.recipes.enums.Unit;
import com.example.recipes.services.IngredientCategoryService;
import com.example.recipes.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private IngredientCategoryService ingredientCategoryService;

    @GetMapping()
    public String getAllIngredients(Model model) {
        List<Ingredient> listOfIngredients = ingredientService.getAllIngredients();
        model.addAttribute("ingredients", listOfIngredients);
        return "ingredient/ingredients_list";
    }

    @GetMapping("/add")
    public String showAddIngredientForm(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        model.addAttribute("categories", ingredientCategoryService.getAllIngredientsCategories());
        model.addAttribute("units", Unit.values());
        return "ingredient/ingredient_add";
    }

    @PostMapping("add")
    public String addIngredient(@ModelAttribute Ingredient ingredient) {
        ingredientService.saveIngredient(ingredient);
        return "redirect:/ingredients";
    }

    @GetMapping("/update/{id}")
    public String updateIngredient(@PathVariable Long id, Model model) {
        Ingredient ingredient = ingredientService.getIngredientById(id).get();
        model.addAttribute("ingredient", ingredient);
        model.addAttribute("categories", ingredientCategoryService.getAllIngredientsCategories());
        model.addAttribute("isEdit", true);
        model.addAttribute("id", ingredient.getId());
        return "ingredient/ingredient_add";
    }

    @PostMapping("/update")
    public String updateIngredient(@ModelAttribute Ingredient ingredient) {
        ingredientService.updateIngredient(ingredient);
        return "redirect:/ingredients";
    }

    @GetMapping("/delete/{id}")
    public String deleteIngredient(@PathVariable Long id) {
        ingredientService.deleteIngredientById(id);
        return "redirect:/ingredients";
    }
}
