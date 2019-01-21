package com.solar.guru.spring5recipeproject.converters;

import com.solar.guru.spring5recipeproject.commands.CategoryCommand;
import com.solar.guru.spring5recipeproject.model.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandTest {
    public static final Long ID_VALUE = new Long(1L);
    public static final String NAME = "name";
    CategoryToCategoryCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    public void convert() {
        //GIVEN
        Category category = new Category();
        category.setId(ID_VALUE);
        category.setName(NAME);

        //WHEN
        CategoryCommand categoryCommand = converter.convert(category);

        //THEN
        assertEquals(ID_VALUE, categoryCommand.getId());
        assertEquals(NAME, categoryCommand.getName());
    }
}