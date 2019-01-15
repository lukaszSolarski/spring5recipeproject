package com.solar.guru.spring5recipeproject.controllers;

import com.solar.guru.spring5recipeproject.model.Category;
import com.solar.guru.spring5recipeproject.model.UnitOfMeasure;
import com.solar.guru.spring5recipeproject.repositories.CategoryRepository;
import com.solar.guru.spring5recipeproject.repositories.UnitOfMeasureRepository;
import com.solar.guru.spring5recipeproject.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@Slf4j
public class IndexController {
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeService recipeService;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeService recipeService) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/","/index","/index.html"})
    public String getIndex(Model model) {
        log.info("Performing getIndex method in " + this.getClass().getName());
        Optional<Category> categoryOptional = categoryRepository.findByName("Polish");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("teaspoon");
        Optional<Category> notExistingCategoryOptional = categoryRepository.findByName("Polish2");
        System.out.println("Category: " + categoryOptional.get().getId() + ":" + categoryOptional.get().getName());
        System.out.println("Unit: " + unitOfMeasureOptional.get().getId() + ":" + unitOfMeasureOptional.get().getDescription());
        /*the above line will throw 'java.util.NoSuchElementException: No value present' and the index page will return
        HTTP 500 error
         */
        //notExistingCategoryOptional.get();
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }
}
