package com.solar.guru.spring5recipeproject.services;

import com.solar.guru.spring5recipeproject.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();

    Recipe findById(Long id);
}
