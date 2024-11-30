package com.example.automated_document_template_filling_system.Controllers;

import com.example.automated_document_template_filling_system.Models.User;
import com.example.automated_document_template_filling_system.Repository.UserRepository;
import com.example.automated_document_template_filling_system.Service.AuthService;
import com.example.automated_document_template_filling_system.Service.EmailService;
import com.example.automated_document_template_filling_system.Service.PasswordGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private EmailService emailService;


    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestParam String email, @RequestParam String password) {

        return authService.authenticate(email, password);
    }

    @PostMapping("/register")
    public String register(@RequestParam String email, @RequestParam String password) {
        if (userRepository.findByEmail(email).isPresent()) {
            return "Такой пользователь уже существует";
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password)); // Хэшируем пароль
        userRepository.save(user);

        return "Регистрация прошла успешно";
    }
    @PostMapping("/recovering")
    public String recover(@RequestParam String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            User user = userRepository.findByEmail(email).orElse(null);
            String password = PasswordGeneratorService.generatePassword(8);
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
            emailService.sendEmail(email,"Ваш пароль", password) ;
        }
        else{
            return "Пользователя с таким email не существует";
        }

        return "Ваш пароль успешно отправлен на почту";

    }

    @PostMapping("/update-password")
    public String updatePassword(@RequestParam String email, @RequestParam String newPassword) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            return "Пользователь не найден";
        }

        User user = userOptional.get();
        user.setPassword(passwordEncoder.encode(newPassword)); // Хэшируем новый пароль
        userRepository.save(user);

        return "Пароль успешно обновлен";
    }

}
