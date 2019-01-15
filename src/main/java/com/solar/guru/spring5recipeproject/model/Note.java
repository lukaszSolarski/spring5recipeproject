package com.solar.guru.spring5recipeproject.model;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = "recipe")
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

}
