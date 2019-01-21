package com.solar.guru.spring5recipeproject.services;

import com.solar.guru.spring5recipeproject.commands.RecipeCommand;
import com.solar.guru.spring5recipeproject.converters.RecipeCommandToRecipe;
import com.solar.guru.spring5recipeproject.converters.RecipeToRecipeCommand;
import com.solar.guru.spring5recipeproject.model.Recipe;
import com.solar.guru.spring5recipeproject.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository,
                             RecipeCommandToRecipe recipeCommandToRecipe,
                             RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
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

    @Override
    public Recipe findById(Long id) {
        return recipeRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);
        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved recipe: " + savedRecipe.getId() + ", " + savedRecipe.getName());
        return recipeToRecipeCommand.convert(savedRecipe);
    }
}
