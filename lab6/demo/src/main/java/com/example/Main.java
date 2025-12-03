package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Translator translator = new Translator();
        Scanner scanner = new Scanner(System.in);

        // Попереднє наповнення словника (щоб одразу щось працювало)
        translator.addWord("hello", "привіт");
        translator.addWord("world", "світ");
        translator.addWord("cat", "кіт");
        translator.addWord("dog", "пес");
        translator.addWord("is", "є");
        translator.addWord("a", "");
        translator.addWord("the", "");
        translator.addWord("i", "я");
        translator.addWord("love", "люблю");
        translator.addWord("java", "джава");

        while (true) {
            System.out.println("\n=== ПЕРЕКЛАДАЧ (Lab 6) ===");
            System.out.println("1. Додати нове слово до словника");
            System.out.println("2. Перекласти фразу");
            System.out.println("0. Вихід");
            System.out.print("Ваш вибір: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    System.out.print("Введіть слово англійською: ");
                    String en = scanner.nextLine().trim();
                    System.out.print("Введіть переклад українською: ");
                    String ua = scanner.nextLine().trim();
                    
                    if (!en.isEmpty() && !ua.isEmpty()) {
                        translator.addWord(en, ua);
                        System.out.println("Слово додано!");
                    } else {
                        System.out.println("Помилка: слова не можуть бути порожніми.");
                    }
                }
                case "2" -> {
                    System.out.println("Введіть фразу англійською:");
                    String phrase = scanner.nextLine();
                    String translation = translator.translatePhrase(phrase);
                    System.out.println("Переклад: " + translation);
                }
                case "0" -> {
                    System.out.println("До побачення!");
                    return;
                }
                default -> System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }
}