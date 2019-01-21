package com.solar.guru.spring5recipeproject.converters;

import com.solar.guru.spring5recipeproject.commands.NoteCommand;
import com.solar.guru.spring5recipeproject.model.Note;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NoteCommandToNoteTest {

    public static final Long ID_VALUE = new Long(1L);
    public static final String RECIPE_NOTE = "recipeNote";
    NoteCommandToNote converter;

    @Before
    public void setUp() throws Exception {
        converter = new NoteCommandToNote();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new NoteCommand()));
    }


    @Test
    public void convert() {
        //GIVEN
        NoteCommand noteCommand = new NoteCommand();
        noteCommand.setId(ID_VALUE);
        noteCommand.setRecipeNote(RECIPE_NOTE);

        //WHEN
        Note note = converter.convert(noteCommand);

        //THEN
        assertEquals(ID_VALUE, note.getId());
        assertEquals(RECIPE_NOTE, note.getRecipeNote());
    }
}