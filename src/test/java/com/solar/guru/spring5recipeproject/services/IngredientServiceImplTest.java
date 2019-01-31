package com.solar.guru.spring5recipeproject.services;

import com.solar.guru.spring5recipeproject.commands.IngredientCommand;
import com.solar.guru.spring5recipeproject.converters.IngredientToIngredientCommand;
import com.solar.guru.spring5recipeproject.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.solar.guru.spring5recipeproject.model.Ingredient;
import com.solar.guru.spring5recipeproject.model.Recipe;
import com.solar.guru.spring5recipeproject.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IngredientServiceImplTest {

    private final IngredientToIngredientCommand converter;

    IngredientServiceImpl ingredientService;

    @Mock
    RecipeRepository recipeRepository;

    public IngredientServiceImplTest() {
        this.converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ingredientService = new IngredientServiceImpl(recipeRepository, converter);
    }

    @Test
    public void findByRecipeIdAndIngredientIdHappyPath() {
        Long recipeId = 1L;
        Long ingredientId = 2L;

        Recipe recipe = new Recipe();
        recipe.setId(recipeId);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(ingredientId);
        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(recipeId, ingredientId);

        assertNotNull(ingredientCommand);
        verify(recipeRepository, times(1)).findById(anyLong());
        assertEquals(ingredientId, ingredientCommand.getId());
        assertEquals(recipeId, ingredientCommand.getRecipeId());
    }
}