package com.solar.guru.spring5recipeproject.converters;

import com.solar.guru.spring5recipeproject.commands.NoteCommand;
import com.solar.guru.spring5recipeproject.model.Note;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NoteToNoteCommandTest {

    public static final Long ID_VALUE = new Long(1L);
    public static final String RECIPE_NOTE = "recipeNote";
    NoteToNoteCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new NoteToNoteCommand();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Note()));
    }


    @Test
    public void convert() {
        //GIVEN
        Note note = new Note();
        note.setId(ID_VALUE);
        note.setRecipeNote(RECIPE_NOTE);

        //WHEN
        NoteCommand noteCommand = converter.convert(note);

        //THEN
        assertEquals(ID_VALUE, noteCommand.getId());
        assertEquals(RECIPE_NOTE, noteCommand.getRecipeNote());
    }
}