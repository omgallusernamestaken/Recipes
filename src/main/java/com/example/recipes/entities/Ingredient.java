package com.example.recipes.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
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

    public Ingredient() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public double getKcalIn100g() {
        return kcalIn100g;
    }

    public void setKcalIn100g(double caloriesIn100g) {
        this.kcalIn100g = caloriesIn100g;
    }

    public IngredientCategory getCategory() {
        return category;
    }

    public void setCategory(IngredientCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", ingredientName='" + ingredientName + '\'' +
                ", caloriesIn100g=" + kcalIn100g +
                ", category=" + category +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return id == that.id && Double.compare(that.kcalIn100g, kcalIn100g) == 0 && Objects.equals(ingredientName, that.ingredientName) && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ingredientName, kcalIn100g, category);
    }
}
