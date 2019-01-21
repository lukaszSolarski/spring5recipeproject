package com.solar.guru.spring5recipeproject.converters;

import com.solar.guru.spring5recipeproject.commands.CategoryCommand;
import com.solar.guru.spring5recipeproject.model.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {

    public static final Long ID_VALUE = new Long(1L);
    public static final String NAME = "name";
    CategoryCommandToCategory converter;

    @Before
    public void setUp() throws Exception {
        converter = new CategoryCommandToCategory();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    public void convert() {
        //GIVEN
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(ID_VALUE);
        categoryCommand.setName(NAME);

        //WHEN
        Category category = converter.convert(categoryCommand);

        //THEN
        assertEquals(ID_VALUE, category.getId());
        assertEquals(NAME, category.getName());
    }
}