package com.astro.recipeapp.service;


import java.io.File;

public interface FilesService {
    boolean saveToFile(String json);

    String readFromFile();

    File getDataFile();

    boolean cleanDataFile();
}
