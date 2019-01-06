package com.solar.guru.spring5recipeproject.controllers;

import com.solar.guru.spring5recipeproject.model.Category;
import com.solar.guru.spring5recipeproject.model.UnitOfMeasure;
import com.solar.guru.spring5recipeproject.repositories.CategoryRepository;
import com.solar.guru.spring5recipeproject.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {
    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"","/","/index","/index.html"})
    public String getIndex() {

        Optional<Category> categoryOptional = categoryRepository.findByName("Polish");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        Optional<Category> notExistingCategoryOptional = categoryRepository.findByName("Polish2");
        System.out.println("Category: " + categoryOptional.get().getId() + ":" + categoryOptional.get().getName());
        System.out.println("Unit: " + unitOfMeasureOptional.get().getId() + ":" + unitOfMeasureOptional.get().getDescription());
        /*the above line will throw 'java.util.NoSuchElementException: No value present' and the index page will return
        HTTP 500 error
         */
        //notExistingCategoryOptional.get();

        return "index";
    }
}
