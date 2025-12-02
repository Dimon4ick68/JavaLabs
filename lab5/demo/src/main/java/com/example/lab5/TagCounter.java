package com.example.lab5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagCounter {
    public void countTags(String urlString) {
        Map<String, Integer> tags = new HashMap<>();
        // Регулярний вираз для пошуку тегів
        Pattern pattern = Pattern.compile("<\\/?([a-zA-Z][a-zA-Z0-9]*)\\b[^>]*>");

        try {
            URL url = new URL(urlString);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Matcher matcher = pattern.matcher(line);
                    while (matcher.find()) {
                        String tagName = matcher.group(1).toLowerCase();
                        tags.put(tagName, tags.getOrDefault(tagName, 0) + 1);
                    }
                }
            }

            System.out.println("Результати (Лексикографічно):");
            tags.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));

            System.out.println("\nРезультати (За частотою):");
            tags.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));

        } catch (IOException e) {
            System.err.println("Помилка URL: " + e.getMessage());
        }
    }
}
