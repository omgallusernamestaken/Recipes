package com.example.recipes.services;

import com.example.recipes.entities.RecipeTag;
import com.example.recipes.enums.TagCategory;
import com.example.recipes.repositories.RecipeTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeTagService {

    @Autowired
    private RecipeTagRepository recipeTagRepository;

    public List<RecipeTag> getAllTags() {
        return recipeTagRepository.findAll();
    }

    public List<RecipeTag> getAllByIdsList(List<Long> recipeTags) {
        return recipeTagRepository.findAllById(recipeTags);
    }

    public List<RecipeTag> getTagsWithCategory(String categoryName) {
        TagCategory category = TagCategory.valueOf(categoryName.toUpperCase());
        List<RecipeTag> recipeTags = recipeTagRepository.findAllByTagCategory(category);
        return recipeTags;
    }

    public RecipeTag getTagByTagName(String tagName) {
        return recipeTagRepository.findByTagName(tagName);
    }
}
