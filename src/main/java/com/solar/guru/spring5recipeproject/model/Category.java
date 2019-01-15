package com.solar.guru.spring5recipeproject.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/*
Exclude this properties from EqualsAndHashCode, which are bidirectional. We have to exclude recipes from: Category,
Ingredient and Note models.
 */
@Entity
@Data
@EqualsAndHashCode(exclude = "recipes")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;
}
