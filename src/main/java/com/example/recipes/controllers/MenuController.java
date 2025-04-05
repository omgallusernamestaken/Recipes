package com.example.recipes.controllers;

import com.example.recipes.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MenuController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping()
    public String showMenu(Model model) {
        model.addAttribute("recipes", recipeService.getAllRecipes());
        return "menu";
    }
}
