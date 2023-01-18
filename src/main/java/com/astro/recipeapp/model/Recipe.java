package com.astro.recipeapp.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Recipe {
    private String name;
    private int time;
    private List<Ingredient> ingredientSet = new ArrayList<>();
    private List<String> steps = new ArrayList<>();
}
