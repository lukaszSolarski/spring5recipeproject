package com.solar.guru.spring5recipeproject.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {
    Category category;

    @Before
    public void setUp() {
        category = new Category();
    }
    @Test
    public void getId() {
        Long value = 4L;
        category.setId(value);
        assertEquals(value, category.getId());
    }

    @Test
    public void getName() {
    }

    @Test
    public void getRecipes() {
    }
}