package com.example.automated_document_template_filling_system.Controllers;

import com.example.automated_document_template_filling_system.Models.User;
import com.example.automated_document_template_filling_system.Repository.UserRepository;
import com.example.automated_document_template_filling_system.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @Autowired
    private EmailService emailService;
    private UserRepository userRepository;

    @GetMapping({"/", "/login", "/register", "/home", "/recovering"})
    public String index() {
        // Возвращаем auth.html для всех этих маршрутов
        return "auth";
    }
}

