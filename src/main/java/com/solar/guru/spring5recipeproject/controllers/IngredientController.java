package com.solar.guru.spring5recipeproject.controllers;

import com.solar.guru.spring5recipeproject.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IngredientController {

    private final RecipeService recipeService;

    public IngredientController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @RequestMapping("/recipe/{recipeId}/ingredient/list")
    public String showListOfIngredients(@PathVariable String recipeId, Model model) {
        model.addAttribute("recipe", recipeService.findById(new Long(recipeId)));
        return "recipe/ingredient/list";
    }
}
