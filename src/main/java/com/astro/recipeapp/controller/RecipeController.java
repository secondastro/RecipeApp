package com.astro.recipeapp.controller;

import com.astro.recipeapp.model.Recipe;
import com.astro.recipeapp.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/recipe")
@Tag(name = "Рецепты")
public class RecipeController {
    public final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/{id}")
    @Operation (
            description = "Получение рецепта по id"
    )
    @Parameter (
            name = "id",
            example = "12"
    )
    public ResponseEntity<Recipe> getRecipe(@PathVariable("id") long id) {
        return ResponseEntity.ok(recipeService.getRecipe(id));
    }

    @GetMapping
    @Operation (description = "Получение всех рецептов")
    public Map<Long, Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @PostMapping
    @Operation(
            description = "Добавление рецепта"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт добавлен",
                    content = @Content(
                            mediaType = "application/json"
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Рецепт не добавлен")
    }
    )

    public ResponseEntity<?> addRecipe(@RequestBody Recipe recipe) {

        if (StringUtils.isBlank(recipe.getName())) {
            return ResponseEntity.badRequest().body("Не введено название рецепта");
        }
        recipeService.addRecipe(recipe);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Operation(description = "Обновление рецепта")
    public ResponseEntity<Recipe> editRecipe(@PathVariable("id") long id, @RequestBody Recipe recipe) {
        if (recipeService.editRecipe(id, recipe) != null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation (description = "Удаление рецепта по id")
    public ResponseEntity<Void> deleteRecipe(@PathVariable("id") long id) {
        if (recipeService.deleteRecipe(id)) {
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
