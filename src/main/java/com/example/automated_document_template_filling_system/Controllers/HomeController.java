package com.example.automated_document_template_filling_system.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/login", "/register", "/home", "/recovering", "/account"})
    public String index() {
        // Возвращаем auth.html для всех этих маршрутов
        return "auth";
    }

    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/home";
    }
}

