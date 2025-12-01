package com.example;

// Main.java
import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {

        // a. Створюємо екземпляр Person
        Person originalPerson = new Person("Шевченко", "Тарас", 47);
        System.out.println("1. Початковий об'єкт:\n   " + originalPerson);
        System.out.println("   (hashCode: " + originalPerson.hashCode() + ")");
        System.out.println("---");


        // b. Конвертуємо його в JSON
        Gson gson = new Gson(); // Створюємо об'єкт Gson
        String jsonString = gson.toJson(originalPerson);
        System.out.println("2. Конвертовано в JSON:\n   " + jsonString);
        System.out.println("---");


        // c. Конвертуємо назад в об'єкт
        Person deserializedPerson = gson.fromJson(jsonString, Person.class);
        System.out.println("3. Десеріалізований об'єкт:\n   " + deserializedPerson);
        System.out.println("   (hashCode: " + deserializedPerson.hashCode() + ")");
        System.out.println("---");


        // d. Перевіряємо equals-ом початковий і одержаний об'єкти
        
        boolean areSameObject = (originalPerson == deserializedPerson);
        System.out.println("4. Порівняння посилань (==): " + areSameObject);

        // Перевірка .equals() (логічне порівняння)
        // Завдяки нашому методу equals(), Java порівняє вміст полів.
        // Оскільки вміст однаковий, тут буде true.
        boolean areEqual = originalPerson.equals(deserializedPerson);
        System.out.println("5. Порівняння логічне (.equals()): " + areEqual);
    }
}