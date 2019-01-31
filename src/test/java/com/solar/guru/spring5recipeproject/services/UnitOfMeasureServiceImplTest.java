package com.solar.guru.spring5recipeproject.services;

import com.solar.guru.spring5recipeproject.commands.UnitOfMeasureCommand;
import com.solar.guru.spring5recipeproject.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.solar.guru.spring5recipeproject.model.UnitOfMeasure;
import com.solar.guru.spring5recipeproject.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UnitOfMeasureServiceImplTest {

    UnitOfMeasureServiceImpl unitOfMeasureService;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        unitOfMeasureService = new UnitOfMeasureServiceImpl(unitOfMeasureRepository, unitOfMeasureToUnitOfMeasureCommand);
    }

    @Test
    public void findAll() {
        Set<UnitOfMeasure> units = new HashSet<>();
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setId(1L);
        units.add(uom1);

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setId(2L);
        units.add(uom2);
        when(unitOfMeasureRepository.findAll()).thenReturn(units);

        Set<UnitOfMeasureCommand> commands = unitOfMeasureService.findAll();

        assertEquals(2, commands.size());
        verify(unitOfMeasureRepository, times(1)).findAll();
        verify(unitOfMeasureRepository, times(0)).findById(anyLong());
    }
}