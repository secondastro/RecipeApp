package com.astro.recipeapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class FirstController {
    @GetMapping
    public String startedApp() {
        return "Приложение запущено!";
    }

    @GetMapping("/info")
    public  String getData() {
        return "Имя: Треногин А.С" +'\n'+
                "Название проекта: Книга рецептов" +'\n'+
                "Дата создания проекта: " + LocalDate.now() +'\n'+
                "Описание: -------";
    }
}
