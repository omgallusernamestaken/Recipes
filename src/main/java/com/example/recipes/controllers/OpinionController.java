package com.example.recipes.controllers;

import com.example.recipes.services.OpinionService;
import com.example.recipes.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/opinions")
public class OpinionController {

    @Autowired
    private OpinionService opinionService;

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/recipe/{recipeId}")
    public String getAllOpinionsForRecipe(@PathVariable long recipeId, Model model) {
        model.addAttribute("opinionsList", opinionService.getAllOpinionByRecipeId(recipeId));
        model.addAttribute("recipeName", recipeService.getRecipeById(recipeId).getRecipeName());
        return "opinions_list";
    }

    @GetMapping("/opinion/{id}")
    public String showOpinion(@PathVariable long id, Model model) {
        System.out.println("tu");
        model.addAttribute("opinion", opinionService.getOpinionById(id));
        return "opinion_template";
    }
}
