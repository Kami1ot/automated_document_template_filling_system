package com.example.automated_document_template_filling_system.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    /**
     * Отправляет простое письмо.
     * @param to      Адрес получателя.
     * @param subject Тема письма.
     * @param text    Текст письма.
     */
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("template_filling_system@mail.ru"); // Ваш email
        message.setTo(to);                      // Email получателя
        message.setSubject(subject);            // Тема
        message.setText("Ваш пароль: " + text); // Текст письма

        mailSender.send(message);               // Отправка письма
        System.out.println("Письмо отправлено на " + to);
    }
}
