package com.example.recipes.controllers;

import com.example.recipes.entities.Recipe;
import com.example.recipes.repositories.RecipesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/recipes")
@RestController
public class RecipeController {

    @Autowired
    private RecipesRepository recipesRepository;

    @GetMapping("/all")
    public List<Recipe> getAllRecipes() {
        List<Recipe> list = recipesRepository.findAll();
        System.out.println(list);
        return list;
    }

}
