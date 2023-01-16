package com.astro.recipeapp.service;

import com.astro.recipeapp.model.Recipe;

public interface RecipeService {

    Recipe addRecipe(Recipe recipe);
    Recipe getRecipe(long i);
}
