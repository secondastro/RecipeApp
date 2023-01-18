package com.astro.recipeapp.model;

import lombok.Data;

import java.util.Objects;

@Data
public class Ingredient {
    private String name;
    private int quantity;
    private String measureUnit;


}
