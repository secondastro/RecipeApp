package com.astro.recipeapp.service;

import com.astro.recipeapp.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {
    private long recipeId = 0;
    private Map<Long, Recipe> recipeMap = new HashMap<>();


    @Override
    public Recipe addRecipe(Recipe recipe) {
        recipeMap.put(this.recipeId++, recipe);
        return recipe;
    }

    @Override
    public Recipe getRecipe(long i) {
        return recipeMap.get(i);
    }
}
