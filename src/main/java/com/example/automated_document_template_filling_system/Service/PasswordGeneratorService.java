package com.example.automated_document_template_filling_system.Service;

import java.security.SecureRandom;

public class PasswordGeneratorService {

    // Символы для генерации пароля
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_+=<>?/";

    // Объединяем все символы
    private static final String ALL_CHARACTERS = UPPERCASE + LOWERCASE + DIGITS + SPECIAL_CHARACTERS;

    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * Генерирует случайный пароль заданной длины.
     * @param length Длина пароля.
     * @return Сгенерированный пароль.
     */
    public static String generatePassword(int length) {
        if (length < 4) {
            throw new IllegalArgumentException("Длина пароля должна быть не меньше 4 символов");
        }

        StringBuilder password = new StringBuilder();

        // Гарантируем наличие хотя бы одного символа из каждой категории
        password.append(UPPERCASE.charAt(RANDOM.nextInt(UPPERCASE.length())));
        password.append(LOWERCASE.charAt(RANDOM.nextInt(LOWERCASE.length())));
        password.append(DIGITS.charAt(RANDOM.nextInt(DIGITS.length())));
        password.append(SPECIAL_CHARACTERS.charAt(RANDOM.nextInt(SPECIAL_CHARACTERS.length())));

        // Добавляем оставшиеся символы из всех возможных
        for (int i = 4; i < length; i++) {
            password.append(ALL_CHARACTERS.charAt(RANDOM.nextInt(ALL_CHARACTERS.length())));
        }

        // Перемешиваем символы
        return shuffleString(password.toString());
    }

    /**
     * Перемешивает символы в строке.
     * @param input Входная строка.
     * @return Перемешанная строка.
     */
    private static String shuffleString(String input) {
        char[] characters = input.toCharArray();
        for (int i = characters.length - 1; i > 0; i--) {
            int index = RANDOM.nextInt(i + 1);
            char temp = characters[i];
            characters[i] = characters[index];
            characters[index] = temp;
        }
        return new String(characters);
    }

//    public static void main(String[] args) {
//        // Пример генерации пароля длиной 12 символов
//        String password = generatePassword(12);
//        System.out.println("Сгенерированный пароль: " + password);
//    }
}

