package com.example.recipes.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private long id;

    @Column(name = "ingredient_name")
    private String ingredientName;

    @Column(name = "kcal_in_100g")
    private double kcalIn100g;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    private IngredientCategory category;

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<RecipeIngredient> recipeIngredients;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return id == that.id && Double.compare(that.kcalIn100g, kcalIn100g) == 0 && Objects.equals(ingredientName, that.ingredientName) && Objects.equals(category, that.category) && Objects.equals(recipeIngredients, that.recipeIngredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ingredientName, kcalIn100g, category, recipeIngredients);
    }
}
