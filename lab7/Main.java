import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StringProcessor processor = new StringProcessor();
        Scanner scanner = new Scanner(System.in); // Прибрав "UTF-8", щоб уникнути проблем на Windows

        // Початковий масив для тесту
        String[] data = {
            "Java", "is", "a", "powerful", "language", 
            "Lambda", "expressions", "are", "cool"
        };

        while (true) {
            System.out.println("\n=== LAB 7: Lambdas & Streams ===");
            System.out.println("Поточний масив: " + Arrays.toString(data));
            System.out.println("1. Ввести новий масив рядків");
            System.out.println("2. Знайти рядки, МЕНШІ за середню довжину");
            System.out.println("3. Знайти рядки, БІЛЬШІ за середню довжину");
            System.out.println("0. Вихід");
            System.out.print("Ваш вибір: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    System.out.println("Введіть рядок (слова через пробіл):");
                    String inputLine = scanner.nextLine();
                    if (!inputLine.isBlank()) {
                        data = inputLine.split("\\s+"); // Розбиваємо по пробілах
                    }
                }
                case "2" -> {
                    String[] result = processor.findShorterThanAverage(data);
                    System.out.println("Результат (< середнього): " + Arrays.toString(result));
                }
                case "3" -> {
                    String[] result = processor.findLongerThanAverage(data);
                    System.out.println("Результат (> середнього): " + Arrays.toString(result));
                }
                case "0" -> {
                    return;
                }
                default -> System.out.println("Невірний вибір.");
            }
        }
    }
}