package com.solar.guru.spring5recipeproject.repositories;

import com.solar.guru.spring5recipeproject.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/*
This repository, as well as the others, will be Autowired even though they don't have neither @Repository nor @Component
annotation. It's a spring boot feature. Normally it needs to use @EnableJpaRepositories at an @Configuration class,
but in SpringBoot it comes with JpaRepositoriesAutoConfiguration.
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {
    /*
    This is Spring Data JPA Query method. Under the cover, Spring Data JPA is going to create query based on the
    method's name and parameters.
     */
    Optional<Category> findByName(String name);
}
