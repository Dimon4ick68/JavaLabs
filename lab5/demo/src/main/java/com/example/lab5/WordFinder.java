package com.example.lab5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class WordFinder {
    public void findMaxWordsLine() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть шлях до текстового файлу: ");
        String path = scanner.nextLine();

        File file = new File(path);
        if (!file.exists()) {
            System.out.println("Файл не знайдено!");
            return;
        }

        String maxLine = "";
        int maxCount = -1;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Розбиваємо по пробілах
                String[] words = line.trim().split("\\s+");
                if (words.length > maxCount) {
                    maxCount = words.length;
                    maxLine = line;
                }
            }
            System.out.println("Рядок з максимальною кількістю слів (" + maxCount + "):");
            System.out.println(maxLine);
        } catch (IOException e) {
            System.err.println("Помилка: " + e.getMessage());
        }
    }
}
