package com.solar.guru.spring5recipeproject.bootstrap;

import com.solar.guru.spring5recipeproject.model.*;
import com.solar.guru.spring5recipeproject.repositories.CategoryRepository;
import com.solar.guru.spring5recipeproject.repositories.RecipeRepository;
import com.solar.guru.spring5recipeproject.repositories.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
CommandLineRunner and it's 'run' method will fire right after Spring Framework is ready.
 */
@Component
public class DataInitializer implements CommandLineRunner {
    private CategoryRepository categoryRepository;
    private RecipeRepository recipeRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public DataInitializer(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        recipeRepository.saveAll(initData());
    }

    private List<Recipe> initData() {
        List<Recipe> recipes = new ArrayList<>(2);

        Category polishCat = getCategoryByName("Polish");
        Category americanCat = getCategoryByName("American");
        UnitOfMeasure tablespoon = getUnitOfMeasureByName("tablespoon");
        UnitOfMeasure item = getUnitOfMeasureByName("item");

        Recipe scrambledEggs = new Recipe();
        scrambledEggs.setName("Scrambled eggs");
        scrambledEggs.getCategories().add(polishCat);
        scrambledEggs.setCookTime(10);
        Ingredient egg = new Ingredient("egg", new BigDecimal(2), item);
        Ingredient butter = new Ingredient("butter", new BigDecimal(1), tablespoon);
        scrambledEggs.addIngredient(egg);
        scrambledEggs.addIngredient(butter);
        scrambledEggs.setDifficulty(Difficulty.EASY);
        scrambledEggs.setDirections("throw butter on the pan\nthen add eggs and mix it.");
        Note n = new Note();
        n.setRecipeNote("Everybody can do it, so do I!");
        scrambledEggs.setNote(n);
        scrambledEggs.setPrepTime(5);
        scrambledEggs.setServings(1);
        scrambledEggs.setSource("Internet");
        scrambledEggs.setUrl("https://www.incredibleegg.org/recipe/basic-scrambled-eggs/");
        recipes.add(scrambledEggs);

        Recipe cake = new Recipe();
        cake.setName("Cake");
        cake.getCategories().add(polishCat);
        cake.getCategories().add(americanCat);
        cake.setCookTime(23);
        Ingredient flavour = new Ingredient("flavour", new BigDecimal(22), tablespoon);
        cake.addIngredient(egg);
        cake.addIngredient(flavour);
        cake.setDifficulty(Difficulty.HARD);
        cake.setDirections("throw all ingredients to the bowl\nthen mix it and cook it");
        Note cakeNote = new Note();
        cakeNote.setRecipeNote("You can do it, though it's hard!!");
        cake.setNote(cakeNote);
        cake.setPrepTime(7);
        cake.setServings(4);
        cake.setSource("Internet");
        cake.setUrl("https://nonexistingul.solar.com");
        recipes.add(cake);

        return recipes;
    }

    private Category getCategoryByName(String name) {
        return categoryRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Category " + name + "not found in db"));
    }

    private UnitOfMeasure getUnitOfMeasureByName(String name) {
        Optional<UnitOfMeasure> unitOfMeasure= unitOfMeasureRepository.findByDescription(name);
        if (unitOfMeasure.isPresent()) {
            return unitOfMeasure.get();
        } else {
            throw new RuntimeException("Unit of measure " + name + "not found in db");
        }
    }
}
