package com.example.recipes.entities;

import com.example.recipes.enums.Difficulty;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@Table(name ="recipes")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private long id;

    @Column(name = "recipe_name")
    private String recipeName;

    @Column(name = "recipe_description")
    private String recipeDescription;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<RecipeIngredient> recipeIngredients;

    @ManyToMany
    @JoinTable(
            name = "recipe_tags",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<RecipeTag> recipeTags;

    @Column(name = "portions_amount")
    private int amountOfPortions;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Opinion> opinions;

    @Column(name = "preparation_time")
    private int preparationTime;

    @Column(name = "difficulty")
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @Column(name = "avg_rating")
    private double avgRating;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return id == recipe.id && amountOfPortions == recipe.amountOfPortions && preparationTime == recipe.preparationTime && Objects.equals(recipeName, recipe.recipeName) && Objects.equals(recipeDescription, recipe.recipeDescription) && Objects.equals(recipeIngredients, recipe.recipeIngredients) && Objects.equals(recipeTags, recipe.recipeTags) && difficulty == recipe.difficulty;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, recipeName, recipeDescription, recipeIngredients, recipeTags, amountOfPortions, preparationTime, difficulty);
    }

    public double calculateAvgRating(List<Opinion> opinions) {
        if (opinions.isEmpty()) {
            return 0.0;
        }
        double sum = opinions.stream()
                .mapToInt(opinion -> opinion.getRating().getValue())
                .sum();

        return sum/ opinions.size();
    }
}
