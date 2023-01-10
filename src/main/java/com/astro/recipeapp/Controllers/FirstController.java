package com.astro.recipeapp.Controllers;

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
    public  String getData(@RequestParam String name, String projectName, LocalDate date, String description) {
        return "Имя: " + name + "; " +
                "Название проекта: " + projectName + "; " +
                "Дата создания проекта: " + date + "; "+
                "Описание: " + description;
    }
}
