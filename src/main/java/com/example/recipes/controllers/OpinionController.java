package com.example.recipes.controllers;

import com.example.recipes.entities.Opinion;
import com.example.recipes.entities.Recipe;
import com.example.recipes.services.OpinionService;
import com.example.recipes.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("recipeId", recipeService.getRecipeById(recipeId).getId());
        return "opinion/opinions_list";
    }

    @GetMapping("/opinion/{id}")
    public String showOpinion(@PathVariable long id, Model model) {
        model.addAttribute("opinion", opinionService.getOpinionById(id));
        return "opinion/opinion_template";
    }

    @GetMapping("/add/{recipeId}")
    public String showAddOpinionForm(@PathVariable long recipeId, Model model) {
        model.addAttribute("opinion", new Opinion());
        model.addAttribute("recipeId", recipeId);
        model.addAttribute("recipeName", recipeService.getRecipeById(recipeId).getRecipeName());
        return "opinion/opinion_add";
    }

    @PostMapping("/add")
    public String addOpinion(@ModelAttribute Opinion opinion, @RequestParam("recipeId") long recipeId) {
        Recipe recipe = recipeService.getRecipeById(recipeId);
        opinion.setRecipe(recipe);
        opinionService.addOpinion(opinion);
        recipeService.updateRatingForRecipe(recipeId);
        return "redirect:/opinions/recipe/" + recipeId;
    }

    @GetMapping("/edit/{id}")
    public String showEditOpinionForm(@PathVariable long id, Model model) {
        Opinion editedOpinion = opinionService.getOpinionById(id);
        long recipeId = editedOpinion.getRecipe().getId();
        model.addAttribute("opinion", editedOpinion);
        model.addAttribute("recipeId", recipeId);
        model.addAttribute("recipeName", recipeService.getRecipeById(recipeId).getRecipeName());
        return "opinion/opinion_add";
    }

    @GetMapping("/delete/{id}")
    public String deleteOpinion(@PathVariable long id, @RequestParam("recipeId") String recipeId) {
        opinionService.deleteOpinionById(id);
        return "redirect:/opinions/recipe/" +recipeId;
    }
}
