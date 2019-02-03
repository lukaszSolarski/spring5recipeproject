package com.solar.guru.spring5recipeproject.controllers;

import com.solar.guru.spring5recipeproject.commands.IngredientCommand;
import com.solar.guru.spring5recipeproject.commands.UnitOfMeasureCommand;
import com.solar.guru.spring5recipeproject.services.IngredientService;
import com.solar.guru.spring5recipeproject.services.RecipeService;
import com.solar.guru.spring5recipeproject.services.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService,
                                IngredientService ingredientService,
                                UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String showListOfIngredients(@PathVariable String recipeId, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(new Long(recipeId)));
        return "recipe/ingredient/list";
    }

    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(
                new Long(recipeId), new Long(ingredientId)));
        return "recipe/ingredient/show";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{id}/update")
    public String updateIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(
                new Long(recipeId), new Long(id)));
        model.addAttribute("uomList", unitOfMeasureService.findAll());
        return "recipe/ingredient/ingredientform";
    }

    @PostMapping
    @RequestMapping("ingredient")
    public String saveOrUpdateIngredient(@ModelAttribute IngredientCommand ingredientCommand) {
        IngredientCommand savedIngredientCommand = ingredientService.saveIngredientCommand(ingredientCommand);
        return "redirect:/recipe/" + savedIngredientCommand.getRecipeId() + "/ingredients";
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/new")
    public String newIngredient(@PathVariable String recipeId, Model model) {
        Set<UnitOfMeasureCommand> uomList = unitOfMeasureService.findAll();
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(new Long(recipeId));
        ingredientCommand.setUnitOfMeasure(uomList.stream().findFirst().get());
        model.addAttribute("ingredient", ingredientCommand);
        model.addAttribute("uomList", uomList);
        return "recipe/ingredient/ingredientform";
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/{id}/delete")
    public String delete(@PathVariable String recipeId, @PathVariable String id) {
        ingredientService.deleteById(new Long(id));
        return "redirect:/recipe/" + recipeId + "/ingredients";
    }
}
