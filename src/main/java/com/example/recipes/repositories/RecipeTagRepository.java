package com.example.recipes.repositories;

import com.example.recipes.entities.RecipeTag;
import com.example.recipes.enums.TagCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeTagRepository extends JpaRepository<RecipeTag, Long> {

    List<RecipeTag> findAllByTagCategory(TagCategory category);

    RecipeTag findByTagName(String recipeTagName);
}
