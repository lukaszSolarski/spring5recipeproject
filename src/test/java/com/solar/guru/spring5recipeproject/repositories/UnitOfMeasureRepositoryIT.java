package com.solar.guru.spring5recipeproject.repositories;

import com.solar.guru.spring5recipeproject.model.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
// It brings up embedded db and prepares it
@DataJpaTest
public class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    /*use the above to make sure the context will be reloaded before next test method in this test class
    It will take more time.
     */
    @DirtiesContext
    public void findByDescription() {
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("teaspoon");
        assertEquals("teaspoon", unitOfMeasureOptional.get().getDescription());
    }
    @Test
    public void findByDescriptionCup() {
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("cup");
        assertEquals("cup", unitOfMeasureOptional.get().getDescription());
    }
}