package com.solar.guru.spring5recipeproject.services;

import com.solar.guru.spring5recipeproject.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);

    void deleteById(Long id);
}
