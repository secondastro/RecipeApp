package com.astro.recipeapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;


public class Ingredient {
    private String name;
    private int quantity;
    private String measureUnit;

    public Ingredient(String name, int quantity, String measureUnit) {
        this.name = name;
        this.quantity = quantity;
        this.measureUnit = measureUnit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingredient that)) return false;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
