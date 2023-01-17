package com.astro.recipeapp.service;

import com.astro.recipeapp.model.Recipe;

import java.util.Map;

public interface RecipeService {

    Recipe addRecipe(Recipe recipe);
    Recipe getRecipe(long i);

    Map<Long, Recipe> getAllRecipes();

    Recipe editRecipe(long i, Recipe recipe);

    boolean deleteRecipe(long id);
}
