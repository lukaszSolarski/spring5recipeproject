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
    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/","/index","/index.html"})
    public String getIndex(Model model) {
        log.info("Performing getIndex method in " + this.getClass().getName());
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }
}
