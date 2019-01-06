package com.solar.guru.spring5recipeproject.repositories;

import com.solar.guru.spring5recipeproject.model.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
    /*
    This is Spring Data JPA Query method. Under the cover, Spring Data JPA is going to create query based on the
    method's name and parameters.
     */
    Optional<UnitOfMeasure> findByDescription(String description);
}
