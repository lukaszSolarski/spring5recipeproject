package com.solar.guru.spring5recipeproject.converters;

import com.solar.guru.spring5recipeproject.commands.NoteCommand;
import com.solar.guru.spring5recipeproject.model.Note;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NoteToNoteCommand implements Converter<Note, NoteCommand> {

    @Synchronized
    @Nullable
    @Override
    public NoteCommand convert(Note note) {
        if (note == null) return null;
        NoteCommand noteCommand = new NoteCommand();
        noteCommand.setId(note.getId());
        noteCommand.setRecipeNote(note.getRecipeNote());
        return noteCommand;
    }
}
