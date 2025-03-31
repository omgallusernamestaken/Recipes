package com.example.recipes.repositories;

import com.example.recipes.entities.RecipeTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<RecipeTag, Long> {
}
