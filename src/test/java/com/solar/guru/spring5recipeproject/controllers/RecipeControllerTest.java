package com.solar.guru.spring5recipeproject.controllers;

import com.solar.guru.spring5recipeproject.model.Recipe;
import com.solar.guru.spring5recipeproject.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class RecipeControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    RecipeController recipeController;
    final String expectedUrl = "recipe/show";
    final String nameOfAttribute = "recipe";
    final String expectedViewName = "recipe/show";
    Recipe recipe;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeController = new RecipeController(recipeService);
        recipe = new Recipe();
        recipe.setId(1L);
    }

    @Test
    public void showById() {
        when(recipeService.findById(anyLong())).thenReturn(recipe);
        assertEquals(expectedUrl, recipeController.showById(recipe.getId().toString(), model));
        verify(recipeService, times(1)).findById(anyLong());
        verify(model, times(1)).addAttribute(nameOfAttribute, recipe);
    }

    @Test
    public void testMockMvcRecipeController() throws Exception {
        when(recipeService.findById(anyLong())).thenReturn(recipe);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
        mockMvc.perform(get("/recipe/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name(expectedViewName))
                .andExpect(model().attributeExists(nameOfAttribute));
    }
}