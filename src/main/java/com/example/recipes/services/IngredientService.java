package com.example.recipes.services;

import com.example.recipes.entities.Ingredient;
import com.example.recipes.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public Optional<Ingredient> getByName(String ingredientName) {
        return ingredientRepository.findByIngredientName(ingredientName);
    }

    public List<Ingredient> getAllIngredients() {
        List<Ingredient> ingredients = ingredientRepository.findAll(Sort.by(Sort.Order.asc("id")));
        return ingredients.isEmpty() ? new ArrayList<>() : ingredients;
    }

    public List<Ingredient> getAllIngredientsById(List<Long> ingredientsList) {
        return ingredientRepository.findAllById(ingredientsList);
    }

    public void save(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    public Optional<Ingredient> getById(Long id) {
        return ingredientRepository.findById(id);
    }

    public void update(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    public void deleteById(Long id) {
        ingredientRepository.deleteById(id);
    }
}
