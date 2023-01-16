package com.astro.recipeapp.controller;

import com.astro.recipeapp.model.Ingredient;
import com.astro.recipeapp.service.IngredientService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    public final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.addIngredient(ingredient);
    }

    @GetMapping("/{id}")
    public Ingredient getIngredient(@PathVariable("id") long id) {
        return ingredientService.getIngredient(id);
    }
}
