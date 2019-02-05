package com.solar.guru.spring5recipeproject.services;

import com.solar.guru.spring5recipeproject.model.Recipe;
import com.solar.guru.spring5recipeproject.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ImageServiceImplTest {

    ImageServiceImpl imageService;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        imageService = new ImageServiceImpl(recipeRepository);
    }

    @Test
    public void saveImageFile() throws Exception{
        MockMultipartFile mockMultipartFile = new MockMultipartFile("imagefile", "testing.txt",
                "text/plain", "any text".getBytes());
        Recipe r = new Recipe();
        Long recipeId = 1L;
        r.setId(recipeId);
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(r));
        ArgumentCaptor<Recipe> recipeArgumentCaptor= ArgumentCaptor.forClass(Recipe.class);

        imageService.saveImageFile(recipeId, mockMultipartFile);

        verify(recipeRepository, times(1)).save(recipeArgumentCaptor.capture());
        assertEquals(mockMultipartFile.getBytes().length, recipeArgumentCaptor.getValue().getImage().length);
    }
}