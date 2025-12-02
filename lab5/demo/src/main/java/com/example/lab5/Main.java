package com.example.lab5;

import java.util.Scanner;

import com.example.lab3.controller.ShapeController;
import com.example.lab3.models.Shape;
import com.example.lab3.view.ShapeView;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Ініціалізація компонентів з Lab 3
        ShapeView view = new ShapeView();
        ShapeController controller = new ShapeController(view);
        controller.generateData(); // Генеруємо початкові дані

        FileManager fileManager = new FileManager();
        WordFinder wordFinder = new WordFinder();
        Encryptor encryptor = new Encryptor();
        TagCounter tagCounter = new TagCounter();

        while (true) {
            System.out.println("\n=== МЕНЮ LAB 5 ===");
            System.out.println("1. Знайти рядок з макс. кількістю слів");
            System.out.println("2. Зберегти фігури у файл");
            System.out.println("3. Завантажити фігури з файлу");
            System.out.println("4. Показати поточні фігури");
            System.out.println("5. Шифрування/Дешифрування файлу");
            System.out.println("6. Аналіз HTML тегів");
            System.out.println("0. Вихід");
            System.out.print("Вибір: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> wordFinder.findMaxWordsLine();
                case "2" -> {
                    System.out.print("Ім'я файлу для збереження: ");
                    String path = scanner.nextLine();
                    fileManager.saveShapes(controller.getShapes(), path);
                }
                case "3" -> {
                    System.out.print("Ім'я файлу для завантаження: ");
                    String path = scanner.nextLine();
                    Shape[] loaded = fileManager.loadShapes(path);
                    if (loaded != null) {
                        controller.setShapes(loaded);
                        System.out.println("Фігури завантажено.");
                    }
                }
                case "4" -> view.printShapes(controller.getShapes());
                case "5" -> {
                    System.out.print("Вхідний файл: ");
                    String src = scanner.nextLine();
                    System.out.print("Вихідний файл: ");
                    String dest = scanner.nextLine();
                    System.out.print("Ключ (число): ");
                    int key = Integer.parseInt(scanner.nextLine());
                    System.out.print("1-Шифрувати, 2-Дешифрувати: ");
                    boolean encrypt = scanner.nextLine().equals("1");
                    encryptor.processFile(src, dest, key, encrypt);
                }
                case "6" -> {
                    System.out.print("Введіть URL: ");
                    tagCounter.countTags(scanner.nextLine());
                }
                case "0" -> System.exit(0);
                default -> System.out.println("Невірний вибір.");
            }
        }
    }
}