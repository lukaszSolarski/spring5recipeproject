package com.solar.guru.spring5recipeproject.services;

import com.solar.guru.spring5recipeproject.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> findAll();
}
