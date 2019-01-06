package com.solar.guru.spring5recipeproject.model;

import javax.persistence.*;

@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
    @Log annotation specifies that this value can be very large. It's important, because default capacity for String
    in Hibernate is much smaller that the capacity of Spring in Java (recipe note can be large).
    In this case, JPA will store CLOB (character large object) field in database (because it's String)
     */
    @Lob
    private String recipeNote;

    @OneToOne
    private Recipe recipe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReceipeNote() {
        return recipeNote;
    }

    public void setRecipeNote(String recipeNote) {
        this.recipeNote = recipeNote;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
