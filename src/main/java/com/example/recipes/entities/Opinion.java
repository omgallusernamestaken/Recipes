package com.example.recipes.entities;

import com.example.recipes.enums.Rating;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "opinions")
public class Opinion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "opinion_id")
    private long id;

    @Column(name = "opinion_rate")
    @Enumerated(EnumType.STRING)
    private Rating rating;

    @Column(name = "opinion_author")
    private String author;

    @Column(name = "opinion_description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

}
