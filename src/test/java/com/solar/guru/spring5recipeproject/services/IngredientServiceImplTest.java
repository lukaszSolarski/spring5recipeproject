package com.solar.guru.spring5recipeproject.services;

import com.solar.guru.spring5recipeproject.commands.IngredientCommand;
import com.solar.guru.spring5recipeproject.converters.IngredientCommandToIngredient;
import com.solar.guru.spring5recipeproject.converters.IngredientToIngredientCommand;
import com.solar.guru.spring5recipeproject.converters.UnitOfMeasureCommandToUnitOfMeasure;
import com.solar.guru.spring5recipeproject.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.solar.guru.spring5recipeproject.model.Ingredient;
import com.solar.guru.spring5recipeproject.model.Recipe;
import com.solar.guru.spring5recipeproject.model.UnitOfMeasure;
import com.solar.guru.spring5recipeproject.repositories.IngredientRepository;
import com.solar.guru.spring5recipeproject.repositories.RecipeRepository;
import com.solar.guru.spring5recipeproject.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IngredientServiceImplTest {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    IngredientServiceImpl ingredientService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    IngredientRepository ingredientRepository;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    public IngredientServiceImplTest() {
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        this.ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ingredientService = new IngredientServiceImpl(recipeRepository, ingredientRepository, unitOfMeasureRepository, ingredientToIngredientCommand, ingredientCommandToIngredient);
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

    @Test
    public void saveNewIngredientCommand() {
        Long recipeId = 1L;
        Long newIngredientId = 3L;

        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(1L);
        uom.setDescription("description");

        Ingredient newIngredient = new Ingredient();
        newIngredient.setId(newIngredientId);
        newIngredient.setUnitOfMeasure(uom);

        Recipe recipe = new Recipe();
        recipe.setId(recipeId);

        newIngredient.setRecipe(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));
        when(unitOfMeasureRepository.findById(anyLong())).thenReturn(Optional.of(uom));
        when(recipeRepository.save(any())).thenReturn(recipe);

        IngredientCommand newIngredientCommand = ingredientService.saveIngredientCommand(
                ingredientToIngredientCommand.convert(newIngredient)
        );

        assertEquals(newIngredientId, newIngredientCommand.getId());
    }

    @Test
    public void saveExistingIngredientCommand() {
        Long recipeId = 1L;
        Long existingIngredientId = 2L;

        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(1L);
        uom.setDescription("description");

        Ingredient existingIngredient = new Ingredient();
        existingIngredient.setId(existingIngredientId);
        existingIngredient.setUnitOfMeasure(uom);

        Recipe recipe = new Recipe();
        recipe.setId(recipeId);
        recipe.addIngredient(existingIngredient);

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));
        when(unitOfMeasureRepository.findById(anyLong())).thenReturn(Optional.of(uom));
        when(recipeRepository.save(any())).thenReturn(recipe);

        IngredientCommand existingIngredientCommand = ingredientService.saveIngredientCommand(
                ingredientToIngredientCommand.convert(existingIngredient)
        );

        assertEquals(existingIngredientId, existingIngredientCommand.getId());
    }
}