import java.util.Arrays;

public class StringProcessor {

    // Метод для пошуку рядків, коротших за середню довжину
    public String[] findShorterThanAverage(String[] input) {
        // 1. Обчислюємо середню довжину
        double averageLength = calculateAverageLength(input);
        System.out.printf("Середня довжина: %.2f%n", averageLength);

        // 2. Фільтруємо за допомогою Stream API та Lambda
        return Arrays.stream(input)
                .filter(s -> s.length() < averageLength) // Лямбда: залишаємо ті, що менші
                .toArray(String[]::new); // Збираємо назад у масив
    }

    // Метод для пошуку рядків, довших за середню довжину
    public String[] findLongerThanAverage(String[] input) {
        double averageLength = calculateAverageLength(input);
        // System.out.printf("Середня довжина: %.2f%n", averageLength); // Можна розкоментувати

        return Arrays.stream(input)
                .filter(s -> s.length() > averageLength) // Лямбда: залишаємо ті, що більші
                .toArray(String[]::new);
    }

    // Допоміжний метод для обчислення середнього
    private double calculateAverageLength(String[] input) {
        if (input == null || input.length == 0) {
            return 0;
        }
        return Arrays.stream(input)
                .mapToInt(String::length) // Перетворюємо кожен рядок на його довжину (int)
                .average()                // Рахуємо середнє
                .orElse(0);               // Якщо масив порожній, повертаємо 0
    }
}