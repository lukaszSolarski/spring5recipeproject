package com.solar.guru.spring5recipeproject.converters;

import com.solar.guru.spring5recipeproject.commands.NoteCommand;
import com.solar.guru.spring5recipeproject.model.Note;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NoteCommandToNote implements Converter<NoteCommand, Note> {

    @Synchronized
    @Nullable
    @Override
    public Note convert(NoteCommand noteCommand) {
        if (noteCommand == null) return null;
        Note note = new Note();
        note.setId(noteCommand.getId());
        note.setRecipeNote(noteCommand.getRecipeNote());
        return note;
    }
}
