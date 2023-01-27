package com.astro.recipeapp.service;

import com.astro.recipeapp.model.Ingredient;

import java.util.Map;

public interface IngredientService {
    Ingredient addIngredient(Ingredient ingredient);
    Ingredient getIngredient(long i);

    Map<Long, Ingredient> getAllIngredients();

    Ingredient editIngredient(long i, Ingredient ingredient);

    boolean deleteIngredient(long ingredientId);
}
