package com.solar.guru.spring5recipeproject.controllers;

import com.solar.guru.spring5recipeproject.commands.RecipeCommand;
import com.solar.guru.spring5recipeproject.model.Recipe;
import com.solar.guru.spring5recipeproject.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    final String expectedNameOfAttribute = "recipe";
    final String expectedShowViewName = "recipe/show";
    final String expectedFormViewName = "recipe/recipeform";
    final Long recipeId = 1L;
    MockMvc mockMvc;
    Recipe recipe;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeController = new RecipeController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
        recipe = new Recipe();
        recipe.setId(recipeId);
    }

    @Test
    public void showById() {
        when(recipeService.findById(anyLong())).thenReturn(recipe);
        assertEquals(expectedUrl, recipeController.showById(recipe.getId().toString(), model));
        verify(recipeService, times(1)).findById(anyLong());
        verify(model, times(1)).addAttribute(expectedNameOfAttribute, recipe);
    }

    @Test
    public void testMockMvcRecipeController() throws Exception {
        when(recipeService.findById(anyLong())).thenReturn(recipe);
        mockMvc.perform(get("/recipe/" + recipeId + "/show"))
                .andExpect(status().isOk())
                .andExpect(view().name(expectedShowViewName))
                .andExpect(model().attributeExists(expectedNameOfAttribute));
    }

    @Test
    public void newRecipe() throws Exception {
        mockMvc.perform(get("/recipe/new"))
                .andExpect(status().isOk())
                .andExpect(view().name(expectedFormViewName))
                .andExpect(model().attributeExists(expectedNameOfAttribute));
    }

    @Test
    public void saveOrUpdate() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        Long id = 2L;
        recipeCommand.setId(id);
        when(recipeService.saveRecipeCommand(any())).thenReturn(recipeCommand);

        mockMvc.perform(post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "some string")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/" + id + "/show"));

    }

    @Test
    public void updateRecipe() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        Long id = 2L;
        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

        mockMvc.perform(get("/recipe/" + id + "/update"))
                .andExpect(status().isOk())
                .andExpect(view().name(expectedFormViewName))
                .andExpect(model().attributeExists(expectedNameOfAttribute));
    }
}