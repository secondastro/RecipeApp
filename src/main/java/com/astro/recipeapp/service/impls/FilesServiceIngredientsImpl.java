package com.astro.recipeapp.service.impls;

import com.astro.recipeapp.service.FilesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@Component("FilesServiceIngredients")
public class FilesServiceIngredientsImpl implements FilesService {
    @Value("${application.file.ingredients.path}")
    private String ingredientsFilePath;
    @Value("${application.ingredients.name}")
    private String ingredientsFileName;

    @Override
    public boolean saveToFile(String json) {
        try {
            cleanDataFile();
            Files.writeString(Path.of(ingredientsFilePath, ingredientsFileName), json);

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String readFromFile() {
        Path path = Path.of(ingredientsFilePath, ingredientsFileName);
        try {
            if (!Files.exists(path)) {
                Files.writeString(path, "{}");
            }
            return Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public File getDataFile() {
        return new File(ingredientsFilePath + "/" + ingredientsFileName);
    }
    @Override
    public boolean cleanDataFile() {
        try {
            Path path = Path.of(ingredientsFilePath, ingredientsFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
