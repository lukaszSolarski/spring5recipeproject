package com.solar.guru.spring5recipeproject.services;

import com.solar.guru.spring5recipeproject.commands.UnitOfMeasureCommand;
import com.solar.guru.spring5recipeproject.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.solar.guru.spring5recipeproject.model.UnitOfMeasure;
import com.solar.guru.spring5recipeproject.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand converter;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository,
                                    UnitOfMeasureToUnitOfMeasureCommand converter) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.converter = converter;
    }

    @Override
    public Set<UnitOfMeasureCommand> findAll() {
        Set<UnitOfMeasure> units = new HashSet<>();
        unitOfMeasureRepository.findAll().forEach(units::add);
        //TODO change the following code:
        Set<UnitOfMeasureCommand> commands = new HashSet<>();
        for (UnitOfMeasure uom : units) {
            commands.add(converter.convert(uom));
        }
        return commands;
    }
}
