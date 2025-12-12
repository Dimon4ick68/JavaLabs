package com.example;

import java.lang.reflect.Field;
import java.util.Scanner;

public class ReflectionDemo {

    public void run(Scanner scanner) {
        System.out.println("--- REFLECTION DEMO ---");
        
        // 1. Створення рядків
        String literal = "Java"; // Літерал (в String Pool)
        System.out.print("Введіть динамічний рядок: ");
        String dynamic = scanner.nextLine(); // Об'єкт у купі (Heap)

        System.out.print("На що замінити (текст): ");
        String replacement = scanner.nextLine();

        System.out.println("\nЛітерал до зміни: " + literal);
        System.out.println("Динамічний до зміни: " + dynamic);

        // Змінюємо обидва рядки
        changeStringValue(literal, replacement);
        changeStringValue(dynamic, replacement);

        System.out.println("\nЛітерал після зміни: " + literal);
        System.out.println("Динамічний після зміни: " + dynamic);
        
        // Ефект "String Pool":
        System.out.println("Інша змінна з текстом \"Java\": " + "Java");
    }

    private void changeStringValue(String target, String newValue) {
        try {
            // Отримуємо доступ до поля "value" класу String
            Field valueField = String.class.getDeclaredField("value");
            valueField.setAccessible(true); // "Ламаємо" приватність


            Object value = valueField.get(target);

            if (value instanceof byte[]) {

                byte[] newBytes = newValue.getBytes();
                valueField.set(target, newBytes);
            } else if (value instanceof char[]) {

                char[] newChars = newValue.toCharArray();
                valueField.set(target, newChars);
            }

        } catch (Exception e) {
            System.err.println("Помилка рефлексії: " + e.getMessage());
            System.err.println("Спробуйте додати VM options: --add-opens java.base/java.lang=ALL-UNNAMED");
        }
    }
}