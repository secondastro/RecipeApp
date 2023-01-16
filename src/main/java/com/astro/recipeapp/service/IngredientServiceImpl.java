package com.astro.recipeapp.service;

import com.astro.recipeapp.model.Ingredient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final Map<Long, Ingredient> ingredientMap = new HashMap<>();
    private long ingredientId = 0;

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        ingredientMap.put(this.ingredientId++, ingredient);
        return ingredient;
    }

    @Override
    public Ingredient getIngredient(long i) {
        return ingredientMap.get(i);
    }
}
