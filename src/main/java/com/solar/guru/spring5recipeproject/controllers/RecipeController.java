package com.solar.guru.spring5recipeproject.controllers;

import com.solar.guru.spring5recipeproject.commands.RecipeCommand;
import com.solar.guru.spring5recipeproject.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/show/{id}")
    public String showById(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findById(new Long(id)));
        return "recipe/show";
    }

    /*
    It renders view with form where you can add new Recipe.
     */
    @RequestMapping("recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeform";
        //TODO create tests
    }

    /*
    It handles POST request, adds new recipe and redirects to recipe/show
     */
    @PostMapping
    @RequestMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
        RecipeCommand savedRecipe = recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/show/" + savedRecipe.getId();
        //TODO create tests
    }
}
