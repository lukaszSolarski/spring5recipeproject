package com.solar.guru.spring5recipeproject.repositories;

import com.solar.guru.spring5recipeproject.model.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
