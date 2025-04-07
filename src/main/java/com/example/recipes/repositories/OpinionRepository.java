package com.example.recipes.repositories;

import com.example.recipes.entities.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpinionRepository extends JpaRepository<Opinion, Long> {

    List<Opinion> getAllOpinionsByRecipeIdOrderByIdDesc(long recipeId);
}
