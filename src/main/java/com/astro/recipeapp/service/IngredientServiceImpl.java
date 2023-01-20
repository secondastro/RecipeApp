package com.astro.recipeapp.service;

import com.astro.recipeapp.model.Ingredient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final FilesService filesService;
    private Map<Long, Ingredient> ingredientMap = new HashMap<>();
    private long ingredientId = 0;

    public IngredientServiceImpl(FilesService filesService) {
        this.filesService = filesService;
    }

    @PostConstruct
    private void init() {
        readFromFIle();
    }
    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        if (StringUtils.isBlank(ingredient.getName())) {
            throw new IllegalArgumentException("Введите имя игредиента");
        }
        ingredientMap.put(this.ingredientId++, ingredient);
        saveToFile();
        return ingredient;
    }

    @Override
    public Ingredient getIngredient(long i) {
        return ingredientMap.get(i);
    }

    @Override
    public Map<Long, Ingredient> getAllIngredients() {
        return ingredientMap;
    }

    @Override
    public Ingredient editIngredient(long i, Ingredient ingredient) {
        if (!ingredientMap.containsKey(i)) {
            return null;
        }
        if (StringUtils.isBlank(ingredient.getName())) {
            throw new IllegalArgumentException("Введите имя ингредиента");
        }
        ingredientMap.put(i, ingredient);
        saveToFile();
        return ingredient;
    }

    @Override
    public boolean deleteIngredient(long id) {
        if (!ingredientMap.containsKey(id)) {
            return false;
        }
        ingredientMap.remove(id);
        saveToFile();
        return true;
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredientMap);
            filesService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFIle() {
        String json = filesService.readFromFile();
        try {
            ingredientMap = new ObjectMapper().readValue(json, new TypeReference<HashMap<Long, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
