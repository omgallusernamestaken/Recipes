package com.example.recipes.controllers;

import com.example.recipes.entities.Ingredient;
import com.example.recipes.enums.Unit;
import com.example.recipes.services.IngredientCategoryService;
import com.example.recipes.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    IngredientCategoryService ingredientCategoryService;

    @GetMapping("/all")
    public String getAllIngredients(Model model) {
        List<Ingredient> listOfIngredients = ingredientService.getAllIngredients();
        System.out.println(listOfIngredients);
        model.addAttribute("ingredients", listOfIngredients);
        return "ingredients_list";
    }

    @GetMapping("/add")
    public String showAddIngredientForm(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        model.addAttribute("categories", ingredientCategoryService.findAll());
        model.addAttribute("units", Unit.values());
        return "ingredient_add";
    }

    @PostMapping("add")
    public String addIngredient(@ModelAttribute Ingredient ingredient) {
        ingredientService.save(ingredient);
        return "redirect:/ingredients/all";
    }
}
