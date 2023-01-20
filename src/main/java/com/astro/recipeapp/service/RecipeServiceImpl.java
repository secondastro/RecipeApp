package com.astro.recipeapp.service;

import com.astro.recipeapp.model.Recipe;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {
    private long recipeId = 0;
    private Map<Long, Recipe> recipeMap = new HashMap<>();

    private final FilesService filesService;



    public RecipeServiceImpl(FilesService filesService) {
        this.filesService = filesService;
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        if (StringUtils.isBlank(recipe.getName())) {
            throw new IllegalArgumentException("Введите имя рецепта");
        }
        recipeMap.put(this.recipeId++, recipe);
        saveToFile();
        return recipe;
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }

    @Override
    public Recipe getRecipe(long i) {
        return recipeMap.get(i);
    }

    @Override
    public Map<Long, Recipe> getAllRecipes() {
        return recipeMap;
    }

    @Override
    public Recipe editRecipe(long i, Recipe recipe) {
        if (!recipeMap.containsKey(i)) {
            return null;
        }
        if (StringUtils.isBlank(recipe.getName())) {
            throw new IllegalArgumentException("Введите имя рецепта");
        }
        recipeMap.put(i, recipe);
        saveToFile();
        return recipe;
    }

    @Override
    public boolean deleteRecipe(long id) {
        if (!recipeMap.containsKey(id)) {
            return false;
        }
        recipeMap.remove(id);
        saveToFile();
        return true;
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipeMap);
            filesService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        String json = filesService.readFromFile();
        try {
            recipeMap = new ObjectMapper().readValue(json, new TypeReference<HashMap<Long, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
