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

    public Optional<Ingredient> getIngredientById(Long id) {
        return ingredientRepository.findById(id);
    }

    public Optional<Ingredient> getIngredientByName(String ingredientName) {
        return ingredientRepository.findByIngredientName(ingredientName);
    }

    public List<Ingredient> getAllIngredients() {
        List<Ingredient> ingredients = ingredientRepository.findAll(Sort.by(Sort.Order.asc("id")));
        return ingredients.isEmpty() ? new ArrayList<>() : ingredients;
    }

    public List<Ingredient> getAllIngredientsByIdsList(List<Long> ingredientsList) {
        return ingredientRepository.findAllById(ingredientsList);
    }

    public void saveIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    public void updateIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    public void deleteIngredientById(Long id) {
        ingredientRepository.deleteById(id);
    }
}
