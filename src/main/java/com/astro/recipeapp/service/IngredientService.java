package com.astro.recipeapp.service;

import com.astro.recipeapp.model.Ingredient;

public interface IngredientService {
    Ingredient addIngredient(Ingredient ingredient);
    Ingredient getIngredient(long i);
}
