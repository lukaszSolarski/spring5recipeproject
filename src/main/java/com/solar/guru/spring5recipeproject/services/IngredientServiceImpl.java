package com.solar.guru.spring5recipeproject.services;

import com.solar.guru.spring5recipeproject.commands.IngredientCommand;
import com.solar.guru.spring5recipeproject.converters.IngredientCommandToIngredient;
import com.solar.guru.spring5recipeproject.converters.IngredientToIngredientCommand;
import com.solar.guru.spring5recipeproject.model.Ingredient;
import com.solar.guru.spring5recipeproject.model.Recipe;
import com.solar.guru.spring5recipeproject.repositories.RecipeRepository;
import com.solar.guru.spring5recipeproject.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    public IngredientServiceImpl(RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository,
                                 IngredientToIngredientCommand ingredientToIngredientCommand,
                                 IngredientCommandToIngredient ingredientCommandToIngredient) {
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() -> new RuntimeException("Couldn't find" +
                " recipe with id " + recipeId));

        Optional<IngredientCommand> ingredientCommand = recipe.getIngredients().stream()
            .filter(ingredient -> ingredient.getId().equals(ingredientId))
            .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

        return ingredientCommand.orElseThrow(() -> new RuntimeException("Couldn't find ingredient with id " +
                ingredientId));
    }

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand) {
        Recipe recipe = recipeRepository.findById(ingredientCommand.getRecipeId())
                .orElseThrow(() -> new RuntimeException("Couldn't find recipe!"));
        //ingredient is new if it doesn't have id
        if (ingredientCommand.getId() == null) {
            recipe.addIngredient(ingredientCommandToIngredient.convert(ingredientCommand));

        }
        else {
            Optional<Ingredient> ingredientOptional= recipe.getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId()))
                    .findFirst();
            if (ingredientOptional.isPresent()) {
                //update existing ingredient
                Ingredient foundIngredient = ingredientOptional.get();
                foundIngredient.setAmount(ingredientCommand.getAmount());
                foundIngredient.setDescription(ingredientCommand.getDescription());
                foundIngredient.setUnitOfMeasure(
                        unitOfMeasureRepository
                                .findById(ingredientCommand.getUnitOfMeasure().getId())
                                .orElseThrow(() -> new RuntimeException("Couldn't find uom!")));
            }
            else {
                //add new ingredient
                Ingredient ingredient = ingredientCommandToIngredient.convert(ingredientCommand);
                recipe.addIngredient(ingredient);
            }
        }
        //in both cases, save recipe
        Recipe savedRecipe = recipeRepository.save(recipe);
        Ingredient savedIngredient;
        Set<Ingredient> listOfIngredients = savedRecipe.getIngredients();
        if (ingredientCommand.getId() == null) {
            savedIngredient = savedRecipe.getIngredients().stream()
                    .filter(ingredient -> ingredientCommand.equals(ingredientToIngredientCommand.convert(ingredient)))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Problem with finding new ingredient"));
        }
        else {
            savedIngredient = savedRecipe.getIngredients().stream()
                    .filter(ingredients -> ingredients.getId().equals(ingredientCommand.getId()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Couldn't find ingredient after saving recipe!"));
        }
        return ingredientToIngredientCommand.convert(savedIngredient);
    }
}
