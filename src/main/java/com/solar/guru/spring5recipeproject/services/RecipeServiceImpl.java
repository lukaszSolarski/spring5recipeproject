package com.solar.guru.spring5recipeproject.services;

import com.solar.guru.spring5recipeproject.model.Recipe;
import com.solar.guru.spring5recipeproject.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> set = new HashSet<>();
        Iterable<Recipe> iterable = recipeRepository.findAll();
        //TODO: read about differences between Iterable.forEach() and Iterator.forEachRemaining()
        //iterable.forEach(set::add);
        iterable.iterator().forEachRemaining(set::add);
        return set;
    }
}
