package com.example;
import java.util.HashMap;
import java.util.Map;

public class Translator {
    // Колекція для зберігання пар слів: <Англійське, Українське>
    private Map<String, String> dictionary;

    public Translator() {
        this.dictionary = new HashMap<>();
    }

    // Метод додавання пари слів
    public void addWord(String englishWord, String ukrainianWord) {
        dictionary.put(englishWord.toLowerCase(), ukrainianWord.toLowerCase());
    }

    // Метод перекладу фрази
    public String translatePhrase(String phrase) {
        StringBuilder result = new StringBuilder();
        
        // Розбиваємо фразу на окремі слова по пробілу
        String[] words = phrase.split(" ");

        for (String word : words) {

            String key = word.toLowerCase();
            
            // getOrDefault: якщо слово є в словнику - беремо переклад,
            // якщо немає - залишаємо оригінальне англійське слово.
            String translatedWord = dictionary.getOrDefault(key, word);
            
            result.append(translatedWord).append(" ");
        }

        return result.toString().trim();
    }
}