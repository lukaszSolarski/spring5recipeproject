package com.solar.guru.spring5recipeproject.repositories;

import com.solar.guru.spring5recipeproject.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
