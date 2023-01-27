package com.astro.recipeapp.controller;

import com.astro.recipeapp.model.Ingredient;
import com.astro.recipeapp.service.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ingredient")
@Tag(name = "Ингредиенты")
public class IngredientController {
    public final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/{id}")
    @Operation(description = "Получение ингредиента по id")
    public Ingredient getIngredient(@PathVariable("id") long id) {
        return ingredientService.getIngredient(id);
    }

    @GetMapping
    @Operation(description = "Получение всех ингредиентов")
    public Map<Long, Ingredient> getAllIngredients() {
        return ingredientService.getAllIngredients();
    }

    @PostMapping
    @Operation(description = "Добавление ингредиента")
    public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.addIngredient(ingredient);
    }


    @PutMapping("/{id}")
    @Operation (description = "Обновление ингредиента")

    public ResponseEntity<Ingredient> editIngredient(@PathVariable("id") long id, @RequestBody Ingredient ingredient) {
        if (ingredientService.editIngredient(id, ingredient) != null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Удаление ингредиента")
    public ResponseEntity<Void> deleteIngredient(@PathVariable("id") long id) {
        if (ingredientService.deleteIngredient(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> handleException(IllegalArgumentException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage(exception.getMessage()));

    }
}
