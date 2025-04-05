package com.example.recipes.entities;

import com.example.recipes.enums.TagCategory;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tags")
@EqualsAndHashCode
public class RecipeTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private long id;

    @Column(name = "tag_name")
    private String tagName;

    @Column(name = "tag_category")
    @Enumerated(EnumType.STRING)
    private TagCategory tagCategory;
}
