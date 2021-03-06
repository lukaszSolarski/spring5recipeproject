package com.solar.guru.spring5recipeproject.services;

import com.solar.guru.spring5recipeproject.commands.RecipeCommand;
import com.solar.guru.spring5recipeproject.converters.RecipeCommandToRecipe;
import com.solar.guru.spring5recipeproject.converters.RecipeToRecipeCommand;
import com.solar.guru.spring5recipeproject.model.Recipe;
import com.solar.guru.spring5recipeproject.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Before
    public void setUp() {
        /*
        This line initializes fields marked with @Mock annotation
         */
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void getRecipes() {
        Set<Recipe> recipesData = new HashSet<>();
        recipesData.add(new Recipe());

        /*
        The below commented line isn't good. The recipeRepository was mocked so this recipeRepository should has been
        used in this expression (not recipeService).
         */
        //when(recipeService.getRecipes()).thenReturn(recipesData);
        when(recipeRepository.findAll()).thenReturn(recipesData);

        Set<Recipe> recipes = recipeService.getRecipes();
        assertEquals(recipes.size(), recipesData.size());
        verify(recipeRepository, times(1)).findAll();
        verify(recipeRepository, never()).findById(anyLong());
    }

    @Test
    public void findById() {
        Long id = 1L;
        Recipe recipe = new Recipe();
        recipe.setId(id);
        Optional<Recipe> optionalRecipe = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);
        Recipe returnedRecipe = recipeService.findById(id);

        assertNotNull("Null recipe returned", returnedRecipe);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
        assertEquals(recipe, returnedRecipe);
    }

    @Test
    public void findCommandById() {
        RecipeCommand recipeCommand = new RecipeCommand();
        Long id = 1L;
        recipeCommand.setId(id);

        when(recipeToRecipeCommand.convert(any())).thenReturn(recipeCommand);
        RecipeCommand returnedRecipeCommand = recipeService.findCommandById(id);

        assertNotNull("Null recipe command returned", returnedRecipeCommand);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
        assertEquals(recipeCommand, returnedRecipeCommand);
    }

    @Test
    public void deleteById() {
            Long id = 2L;
            recipeService.deleteById(id);

            verify(recipeRepository, times(1)).deleteById(anyLong());
    }
}