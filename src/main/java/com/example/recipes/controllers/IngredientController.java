package com.example.recipes.controllers;

import com.example.recipes.entities.Ingredient;
import com.example.recipes.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    IngredientRepository ingredientRepository;

    @GetMapping("/all")
    public List<Ingredient> getAllIngredients() {
        List<Ingredient> list = ingredientRepository.findAll();
        System.out.println(list);
        return list;
    }
}
