package com.solar.guru.spring5recipeproject.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class CategoryCommand {
    private Long id;
    private String name;
}
