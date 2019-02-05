package com.solar.guru.spring5recipeproject.services;

import com.solar.guru.spring5recipeproject.model.Recipe;
import com.solar.guru.spring5recipeproject.repositories.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveImageFile(Long recipeId, MultipartFile file) {
        try {
            Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);
            Recipe recipe = optionalRecipe.orElseThrow(() -> new RuntimeException("recipe was not found"));

            Byte[] bytes = new Byte[file.getBytes().length];
            int i = 0;
            for (byte b : file.getBytes()) {
                bytes[i++] = b;
            }
            recipe.setImage(bytes);
            recipeRepository.save(recipe);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
