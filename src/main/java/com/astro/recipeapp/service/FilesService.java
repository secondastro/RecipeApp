package com.astro.recipeapp.service;


public interface FilesService {
    boolean saveToFile(String json);

    String readFromFile();
}
