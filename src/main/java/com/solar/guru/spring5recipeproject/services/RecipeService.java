package com.solar.guru.spring5recipeproject.services;

import com.solar.guru.spring5recipeproject.commands.RecipeCommand;
import com.solar.guru.spring5recipeproject.model.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();

    Recipe findById(Long id);

    RecipeCommand findCommandById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand command);
}
