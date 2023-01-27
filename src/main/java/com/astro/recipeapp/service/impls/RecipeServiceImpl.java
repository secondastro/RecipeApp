package com.astro.recipeapp.service.impls;

import com.astro.recipeapp.model.Ingredient;
import com.astro.recipeapp.model.Recipe;
import com.astro.recipeapp.service.FilesService;
import com.astro.recipeapp.service.RecipeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {
    private long recipeId;
    private HashMap<Long, Recipe> recipeMap = new HashMap<>();

    private final FilesService filesService;


    public RecipeServiceImpl(@Qualifier("FilesServiceRecipes") FilesService filesService) {
        this.filesService = filesService;
    }

    @PostConstruct
    private void init() {
        try {
            readFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        if (StringUtils.isBlank(recipe.getName())) {
            throw new IllegalArgumentException("Введите имя рецепта");
        }
        recipeId += 1;
        recipeMap.put(recipeId, recipe);
        saveToFile();
        return recipe;
    }

    @Override
    public Recipe getRecipe(long i) {
        return recipeMap.get(i);
    }

    @Override
    public Map<Long, Recipe> getAllRecipes() {
        return recipeMap;
    }

    @Override
    public Recipe editRecipe(long i, Recipe recipe) {
        if (!recipeMap.containsKey(i)) {
            return null;
        }
        if (StringUtils.isBlank(recipe.getName())) {
            throw new IllegalArgumentException("Введите имя рецепта");
        }
        recipeMap.put(i, recipe);
        saveToFile();
        return recipe;
    }

    @Override
    public boolean deleteRecipe(long id) {
        if (!recipeMap.containsKey(id)) {
            return false;
        }
        recipeMap.remove(id);
        saveToFile();
        return true;
    }

    private void saveToTextFile() {

        List<String> recipesList = new ArrayList<>();
        for (Recipe recipe : recipeMap.values()) {
            StringBuilder sb = new StringBuilder();
            sb.append(recipe.getName()).append('\n').append("Время приготовления: ").append(recipe.getTime()).append(" минут").append('\n').append("Ингредиенты: ").append('\n');
            for (Ingredient ingredient : recipe.getIngredientSet()) {
                sb.append('•').append(ingredient.getName()).append(" — ").append(ingredient.getQuantity()).append(" ").append(ingredient.getMeasureUnit()).append('\n');
            }
            int i=0;
            for (String s : recipe.getSteps()) {
                i++;
                sb.append(i).append(") ").append(s).append('\n');
            }
            recipesList.add(sb.toString());
        }
        filesService.saveToTextFile(recipesList);
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipeMap);
            filesService.saveToFile(json);
            saveToTextFile();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        try {
            String json = filesService.readFromFile();
            HashMap<Long, Recipe> recipeHashMap = new ObjectMapper().readValue(json, new TypeReference<>() {
            });
            long i = 0;
            for (Map.Entry<Long, Recipe> entry : recipeHashMap.entrySet()) {
                i = entry.getKey();
            }
            this.recipeId = i;
            recipeMap = recipeHashMap;
            createTxt();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void createTxt() {

        Path path = Path.of("src/main/resources", "recipes.txt");
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new RuntimeException();
            }
        } else {
            saveToTextFile();
        }
    }
//    @Data
//    private static class DataFile {
//        private  long recipeId;
//        private HashMap<Long, Recipe> recipeMap;
//
//        public DataFile() {
//        }
//
//        public DataFile(long recipeId, HashMap<Long, Recipe> recipeMap) {
//            this.recipeId = recipeId;
//            this.recipeMap = recipeMap;
//        }
//    }
}
