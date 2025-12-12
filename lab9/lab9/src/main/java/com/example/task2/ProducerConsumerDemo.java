package com.example.task2;

public class ProducerConsumerDemo {

    public void run() {

        CircularBuffer buffer1 = new CircularBuffer(10);
        CircularBuffer buffer2 = new CircularBuffer(10);

        // 1. П'ять потоків-генераторів (Producers) -> Buffer 1
        for (int i = 1; i <= 5; i++) {
            final int threadId = i;
            Thread producer = new Thread(() -> {
                try {
                    while (true) {
                        String msg = "Потік №" + threadId + " згенерував повідомлення";
                        buffer1.put(msg);
                        Thread.sleep(100); // Імітація роботи
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            producer.setDaemon(true); // Робимо демоном
            producer.start();
        }

        // 2. Два потоки-посередники (Middleman) Buffer 1 -> Buffer 2
        for (int i = 1; i <= 2; i++) {
            final int threadId = i;
            Thread middleman = new Thread(() -> {
                try {
                    while (true) {
                        String msgFrom1 = buffer1.get();
                        String newMsg = "Потік №" + threadId + " переклав повідомлення: [" + msgFrom1 + "]";
                        buffer2.put(newMsg);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            middleman.setDaemon(true); // Робимо демоном
            middleman.start();
        }

        // 3. Основний потік читає 100 повідомлень з Buffer 2
        System.out.println("=== Починаємо читання 100 повідомлень ===");
        try {
            for (int i = 1; i <= 100; i++) {
                String finalMsg = buffer2.get();
                System.out.println("MAIN отримав (" + i + "/100): " + finalMsg);
            }
            System.out.println("=== 100 повідомлень отримано. Програма завершується ===");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}