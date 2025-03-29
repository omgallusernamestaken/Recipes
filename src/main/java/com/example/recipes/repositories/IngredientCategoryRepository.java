package com.example.recipes.repositories;

import com.example.recipes.entities.IngredientCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IngredientCategoryRepository extends JpaRepository<IngredientCategory, Long> {
}
