package com.solar.guru.spring5recipeproject.converters;

import com.solar.guru.spring5recipeproject.commands.CategoryCommand;
import com.solar.guru.spring5recipeproject.commands.IngredientCommand;
import com.solar.guru.spring5recipeproject.commands.NoteCommand;
import com.solar.guru.spring5recipeproject.commands.RecipeCommand;
import com.solar.guru.spring5recipeproject.model.Difficulty;
import com.solar.guru.spring5recipeproject.model.Recipe;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {

    public static final Long RECIPE_ID = 1L;
    public static final Integer COOK_TIME = Integer.valueOf("5");
    public static final Integer PREP_TIME = Integer.valueOf("7");
    public static final String NAME = "My Recipe";
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Integer SERVINGS = Integer.valueOf("3");
    public static final String SOURCE = "Source";
    public static final String URL = "Some URL";
    public static final Long CAT_ID_1 = 1L;
    public static final Long CAT_ID_2 = 2L;
    public static final Long INGRED_ID_1 = 3L;
    public static final Long INGRED_ID_2 = 4L;
    public static final Long NOTE_ID = 5L;
    RecipeCommandToRecipe converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeCommandToRecipe(
                new CategoryCommandToCategory(),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new NoteCommandToNote());
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        //TODO it throws NPE
        //assertNotNull(converter.convert(new RecipeCommand()));
    }


    @Test
    public void convert() {
        //GIVEN
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(RECIPE_ID);
        recipeCommand.setName(NAME);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setDirections(DIRECTIONS);
        recipeCommand.setDifficulty(DIFFICULTY);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setUrl(URL);

        CategoryCommand categoryCommand1 = new CategoryCommand();
        categoryCommand1.setId(CAT_ID_1);
        CategoryCommand categoryCommand2 = new CategoryCommand();
        categoryCommand2.setId(CAT_ID_2);
        recipeCommand.getCategories().add(categoryCommand1);
        recipeCommand.getCategories().add(categoryCommand2);

        IngredientCommand ingredientCommand1 = new IngredientCommand();
        ingredientCommand1.setId(INGRED_ID_1);
        IngredientCommand ingredientCommand2 = new IngredientCommand();
        ingredientCommand2.setId(INGRED_ID_2);
        recipeCommand.getIngredients().add(ingredientCommand1);
        recipeCommand.getIngredients().add(ingredientCommand2);

        NoteCommand noteCommand = new NoteCommand();
        noteCommand.setId(NOTE_ID);
        recipeCommand.setNote(noteCommand);


        //WHEN
        Recipe recipe = converter.convert(recipeCommand);

        //THEN
        assertNotNull(recipe);
        assertNotNull(recipe.getCategories());
        assertNotNull(recipe.getIngredients());
        assertNotNull(recipe.getNote());
        assertEquals(RECIPE_ID, recipe.getId());
        assertEquals(COOK_TIME.intValue(), recipe.getCookTime());
        assertEquals(PREP_TIME.intValue(), recipe.getPrepTime());
        assertEquals(NAME, recipe.getName());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(Difficulty.EASY, recipe.getDifficulty());
        assertEquals(SERVINGS.intValue(), recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(recipeCommand.getCategories().size(), recipe.getCategories().size());
        assertEquals(recipeCommand.getCategories().size(), recipe.getCategories().size());
        assertEquals(NOTE_ID, recipe.getNote().getId());
    }
}