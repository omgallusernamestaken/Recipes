package com.example.recipes.services;

import com.example.recipes.entities.RecipeTag;
import com.example.recipes.repositories.RecipeTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeTagService {

    @Autowired
    private RecipeTagRepository recipeTagRepository;

    public List<RecipeTag> findAllTags() {
        return recipeTagRepository.findAll();
    }

    public List<RecipeTag> findAllByIdsList(List<Long> recipeTags) {
        return recipeTagRepository.findAllById(recipeTags);
    }
}
