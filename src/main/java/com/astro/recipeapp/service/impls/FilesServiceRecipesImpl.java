package com.astro.recipeapp.service.impls;

import com.astro.recipeapp.service.FilesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
@Component("FilesServiceRecipes")
public class FilesServiceRecipesImpl implements FilesService {
    @Value("${application.recipes.path}")
    private String recipesFilePath;
    @Value("${application.recipes.name}")
    private String recipesFileName;

    @Value("${application.recipes.textfile.name}")
    private String recipesTextFileName;

    @Override
    public boolean saveToFile(String json) {
        try {
            cleanDataFile();
            Files.writeString(Path.of(recipesFilePath, recipesFileName), json);

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
@Override
    public boolean saveToTextFile(List<String> list) {
        try {
            cleanTextFile();
            Files.write(Path.of(recipesFilePath, recipesTextFileName), list);

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String readFromFile() {
        Path path = Path.of(recipesFilePath,recipesFileName);
        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public File getDataFile() {
        return new File(recipesFilePath + "/" + recipesFileName);
    }
    @Override
    public File getTextFile(){
        return new File(recipesFilePath + "/" + recipesTextFileName);
    }

    @Override
    public boolean cleanDataFile() {
        try {
            Path path = Path.of(recipesFilePath, recipesFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean cleanTextFile() {
        try {
            Path path = Path.of(recipesFilePath, recipesTextFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


}

