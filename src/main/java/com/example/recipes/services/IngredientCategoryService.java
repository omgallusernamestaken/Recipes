package com.example.recipes.services;

import com.example.recipes.entities.IngredientCategory;
import com.example.recipes.repositories.IngredientCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientCategoryService {

    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;

    public List<IngredientCategory> findAll() {
        return ingredientCategoryRepository.findAll();
    }

}
