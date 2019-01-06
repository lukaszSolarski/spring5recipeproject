package com.solar.guru.spring5recipeproject.repositories;

import com.solar.guru.spring5recipeproject.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
