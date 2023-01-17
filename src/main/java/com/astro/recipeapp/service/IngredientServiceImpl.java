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

    @Override
    public Map<Long, Ingredient> getAllIngredients() {
        return ingredientMap;
    }

    @Override
    public Ingredient editIngredient(long i, Ingredient ingredient) {
        if (ingredientMap.containsKey(i)) {
            ingredientMap.put(i, ingredient);
            return ingredient;
        }
        return null;
    }

    @Override
    public boolean deleteIngredient(long id) {
        if (ingredientMap.containsKey(id)) {
            ingredientMap.remove(id);
            return true;
        }
        return false;
    }
}
