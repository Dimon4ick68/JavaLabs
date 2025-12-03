import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

public class ParallelMonteCarloPi {

    private static final AtomicLong pointsInCircle = new AtomicLong(0);
    private static final int TOTAL_ITERATIONS = 100_000_000; // 100 мільйонів точок

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть кількість потоків (наприклад, 1, 2, 4, 8...): ");
        
        // Перевірка на коректність введення
        int numThreads;
        try {
            String input = scanner.nextLine();
            numThreads = Integer.parseInt(input);
            if (numThreads < 1) numThreads = 1;
        } catch (NumberFormatException e) {
            System.out.println("Некоректне число. Використовую 1 потік.");
            numThreads = 1;
        }

        // Обчислюємо, скільки точок має "кинути" кожен потік
        int iterationsPerThread = TOTAL_ITERATIONS / numThreads;

        System.out.println("Запуск обчислень...");
        long startTime = System.currentTimeMillis();

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < numThreads; i++) {
            Thread thread = new Thread(() -> {
                long inCircleCount = 0;
                // Використовуємо ThreadLocalRandom
                for (int j = 0; j < iterationsPerThread; j++) {
                    double x = ThreadLocalRandom.current().nextDouble();
                    double y = ThreadLocalRandom.current().nextDouble();

                    // Перевіряємо, чи потрапила точка в чверть кола (x^2 + y^2 <= 1)
                    if (x * x + y * y <= 1) {
                        inCircleCount++;
                    }
                }
                // Додаємо локальний результат до глобального лічильника
                pointsInCircle.addAndGet(inCircleCount);
            });
            threads.add(thread);
            thread.start();
        }

        // Чекаємо (join), поки всі потоки завершать роботу
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();
        
        // Формула: PI = 4 * (точки в колі / загальна кількість)
        double pi = 4.0 * pointsInCircle.get() / (numThreads * iterationsPerThread); // Перерахунок точної кількості ітерацій

        System.out.println("\nРезультати:");
        System.out.println("PI is " + pi);
        System.out.println("THREADS " + numThreads);
        System.out.println("ITERATIONS " + (numThreads * iterationsPerThread));
        System.out.println("TIME " + (endTime - startTime) + "ms");
        
        // Скидаємо лічильник для наступних запусків (якщо будемо запускати в циклі)
        pointsInCircle.set(0);
    }
}