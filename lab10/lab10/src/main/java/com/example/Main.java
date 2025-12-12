package com.example;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Main {
    // Завантажуємо бандл. Базова назва "location.messages" (папка.файл)
    private static ResourceBundle bundle = ResourceBundle.getBundle("location.messages", Locale.ENGLISH);

    public static void main(String[] args) {
        // Налаштовуємо логер
        LoggerConfig.setup();
        LoggerConfig.LOGGER.info("Application started");

        Scanner scanner = new Scanner(System.in);
        ReflectionDemo reflectionDemo = new ReflectionDemo();
        Encryptor encryptor = new Encryptor();

        while (true) {
            printMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> reflectionDemo.run(scanner);
                case "2" -> {
                    System.out.print(bundle.getString("msg.file_in"));
                    String src = scanner.nextLine();
                    System.out.print(bundle.getString("msg.file_out"));
                    String dest = scanner.nextLine();
                    System.out.print(bundle.getString("msg.key"));
                    
                    try {
                        int key = Integer.parseInt(scanner.nextLine());
                        System.out.print(bundle.getString("msg.mode"));
                        boolean encrypt = scanner.nextLine().equals("1");
                        
                        encryptor.processFile(src, dest, key, encrypt);
                        System.out.println(bundle.getString("msg.success"));
                    } catch (NumberFormatException e) {
                        System.out.println(bundle.getString("msg.error"));
                        LoggerConfig.LOGGER.warning("User entered invalid number for key");
                    }
                }
                case "3" -> changeLanguage(scanner);
                case "0" -> {
                    LoggerConfig.LOGGER.info("Application closed by user");
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n" + bundle.getString("menu.title"));
        System.out.println(bundle.getString("menu.reflection"));
        System.out.println(bundle.getString("menu.encryptor"));
        System.out.println(bundle.getString("menu.language"));
        System.out.println(bundle.getString("menu.exit"));
        System.out.print(bundle.getString("menu.choice"));
    }

    private static void changeLanguage(Scanner scanner) {
        System.out.println("1. English");
        System.out.println("2. Українська");
        String lang = scanner.nextLine();

        if (lang.equals("2")) {
            bundle = ResourceBundle.getBundle("location.messages", new Locale("uk"));
        } else {
            bundle = ResourceBundle.getBundle("location.messages", Locale.ENGLISH);
        }
        System.out.println(bundle.getString("msg.lang_changed"));
        LoggerConfig.LOGGER.info("Language changed to " + bundle.getLocale());
    }
}