package com.solar.guru.spring5recipeproject.controllers;

import com.solar.guru.spring5recipeproject.commands.IngredientCommand;
import com.solar.guru.spring5recipeproject.commands.RecipeCommand;
import com.solar.guru.spring5recipeproject.commands.UnitOfMeasureCommand;
import com.solar.guru.spring5recipeproject.services.IngredientService;
import com.solar.guru.spring5recipeproject.services.RecipeService;
import com.solar.guru.spring5recipeproject.services.UnitOfMeasureService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

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


public class IngredientControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    IngredientService ingredientService;

    @Mock
    UnitOfMeasureService unitOfMeasureService;

    IngredientController ingredientController;
    MockMvc mockMvc;
    RecipeCommand recipeCommand;
    final Long INGREDIENT_ID = 1L;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ingredientController = new IngredientController(recipeService, ingredientService, unitOfMeasureService);
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
        recipeCommand = new RecipeCommand();
        recipeCommand.setId(INGREDIENT_ID);
    }

    @Test
    public void showListOfIngredients() throws Exception {
        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

        mockMvc.perform(get("/recipe/" + INGREDIENT_ID + "/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/list"))
                .andExpect(model().attributeExists("recipe"));

        verify(recipeService, times(1)).findCommandById(anyLong());
    }

    @Test
    public void showIngredient() throws Exception{
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(INGREDIENT_ID);
        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);

        mockMvc.perform(get("/recipe/" + INGREDIENT_ID + "/ingredient/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/show"))
                .andExpect(model().attributeExists("ingredient"));
        verify(ingredientService, times(1)).findByRecipeIdAndIngredientId(anyLong(), anyLong());
    }

    @Test
    public void updateIngredient() throws Exception {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(INGREDIENT_ID);
        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);
        when(unitOfMeasureService.findAll()).thenReturn(new HashSet<>());

        mockMvc.perform(get("/recipe/1/ingredient/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/ingredientform"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomList"));
        verify(ingredientService, times(1)).findByRecipeIdAndIngredientId(anyLong(), anyLong());
    }

    @Test
    public void saveOrUpdateIngredient () throws Exception {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(INGREDIENT_ID);
        ingredientCommand.setRecipeId(2L);
        when(ingredientService.saveIngredientCommand(any())).thenReturn(ingredientCommand);

        mockMvc.perform(post("/ingredient")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "1")
                .param("description", "some description")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/" + ingredientCommand.getRecipeId()
                        + "/ingredients"));
    }

    @Test
    public void saveNewIngredientWithoutID () throws Exception {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setDescription("description");
        ingredientCommand.setAmount(new BigDecimal(1));
        UnitOfMeasureCommand uom = new UnitOfMeasureCommand();
        uom.setId(1L);
        ingredientCommand.setUnitOfMeasure(uom);
        ingredientCommand.setRecipeId(2L);

        IngredientCommand savedIngredient = new IngredientCommand();
        savedIngredient.setUnitOfMeasure(ingredientCommand.getUnitOfMeasure());
        savedIngredient.setAmount(ingredientCommand.getAmount());
        savedIngredient.setDescription(ingredientCommand.getDescription());
        savedIngredient.setRecipeId(ingredientCommand.getRecipeId());
        savedIngredient.setId(1L);
        when(ingredientService.saveIngredientCommand(any())).thenReturn(savedIngredient);

        mockMvc.perform(post("/ingredient")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", ingredientCommand.getDescription())
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/" + savedIngredient.getRecipeId()
                        + "/ingredients"));
    }

    @Test
    public void newIngredient() throws Exception {
        Set<UnitOfMeasureCommand> units = new HashSet<>();
        UnitOfMeasureCommand uom = new UnitOfMeasureCommand();
        uom.setId(1L);
        units.add(uom);
        when(unitOfMeasureService.findAll()).thenReturn(units);
        mockMvc.perform(get("/recipe/1/ingredient/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/ingredientform"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomList"));
    }

    @Test
    public void deleteIngredient() throws Exception {
        mockMvc.perform(get("/recipe/1/ingredient/1/delete"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/recipe/1/ingredients"));
        verify(ingredientService, times(1)).deleteById(anyLong());
    }
}