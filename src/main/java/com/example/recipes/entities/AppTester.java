package com.example.recipes.entities;

import com.example.recipes.repositories.IngredientCategoryRepository;
import com.example.recipes.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppTester {

    @Autowired
    IngredientCategoryRepository ingredientCategoryRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    public void testApp() {
        IngredientCategory ingredientCategory = new IngredientCategory();
        ingredientCategory.setCategoryName("warzywo");

        Ingredient banan = new Ingredient();
        banan.setCaloriesIn100g(100.00);
        banan.setIngredientName("banan");
        IngredientCategory owoc = new IngredientCategory();
        owoc.setCategoryName("owoc");
        banan.setCategory(owoc);
        ingredientCategoryRepository.save(ingredientCategory);
        ingredientRepository.save(banan);
    }
}
