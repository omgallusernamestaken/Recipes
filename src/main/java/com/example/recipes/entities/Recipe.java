package com.example.recipes.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name ="recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private long id;

    @Column(name = "recipe_name")
    private String recipeName;

    @Column(name = "recipe_description")
    private String recipeDescription;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<RecipeIngredient> recipeIngredients;

    @Column(name = "recipe_category")
    private String recipeCategory;

    //TODO later
    // remember to update setters, getters, toString, hashcode, equals, thymeleaf
//    private List<String> tagList;

    @Column(name = "portions_amount")
    private int amountOfPortions;

    //TODO later
    // remember to update setters, getters, toString, hashcode, equals, thymeleaf
    //TODO: change into Opinion Object
    //private List<String> opinions;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    public List<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public String getRecipeCategory() {
        return recipeCategory;
    }

    public void setRecipeCategory(String recipeCategory) {
        this.recipeCategory = recipeCategory;
    }

    public int getAmountOfPortions() {
        return amountOfPortions;
    }

    public void setAmountOfPortions(int amountOfPortions) {
        this.amountOfPortions = amountOfPortions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return id == recipe.id && amountOfPortions == recipe.amountOfPortions && Objects.equals(recipeName, recipe.recipeName) && Objects.equals(recipeDescription, recipe.recipeDescription) && Objects.equals(recipeIngredients, recipe.recipeIngredients) && Objects.equals(recipeCategory, recipe.recipeCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, recipeName, recipeDescription, recipeIngredients, recipeCategory, amountOfPortions);
    }
}
