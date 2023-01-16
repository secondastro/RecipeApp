package com.astro.recipeapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Recipe {
    private String name;
    private int time;
    private List<Ingredient> ingredientSet = new ArrayList<>();
    private List<String> steps = new ArrayList<>();

    public Recipe(String name, int time, List<Ingredient> ingredientSet, List<String> steps) {
        this.name = name;
        this.time = time;
        this.ingredientSet = ingredientSet;
        this.steps = steps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<Ingredient> getIngredientSet() {
        return ingredientSet;
    }

    public void setIngredientSet(List<Ingredient> ingredientSet) {
        this.ingredientSet = ingredientSet;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recipe recipe)) return false;
        return getName().equals(recipe.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
