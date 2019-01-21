package com.solar.guru.spring5recipeproject.converters;

import com.solar.guru.spring5recipeproject.commands.CategoryCommand;
import com.solar.guru.spring5recipeproject.model.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    @Synchronized
    @Nullable
    @Override
    public CategoryCommand convert(Category category) {
        if (category == null) return null;

        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(category.getId());
        categoryCommand.setName(category.getName());
        return categoryCommand;
    }
}
