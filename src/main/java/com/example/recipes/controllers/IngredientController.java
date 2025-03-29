package com.example.recipes.controllers;

import com.example.recipes.entities.Ingredient;
import com.example.recipes.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    IngredientRepository ingredientRepository;

    @GetMapping("/all")
    public String getAllIngredients(Model model) {
        List<Ingredient> listOfIngredients = ingredientRepository.findAll();
        System.out.println(listOfIngredients);
        model.addAttribute("ingredients", listOfIngredients);
        return "ingredients_list";
    }
}
