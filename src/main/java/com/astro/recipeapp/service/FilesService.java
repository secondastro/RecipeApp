package com.astro.recipeapp.service;


import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FilesService {
    boolean saveToFile(String json);

    boolean saveToTextFile(List<String> list);

    String readFromFile();


    File getDataFile();

    File getTextFile();

    boolean cleanDataFile();

    boolean cleanTextFile();
}
