package com.example.task2;

public class CircularBuffer {
    private final String[] buffer;
    private int head = 0;
    private int tail = 0;
    private int count = 0;
    private final int capacity;

    public CircularBuffer(int capacity) {
        this.capacity = capacity;
        this.buffer = new String[capacity];
    }

    public synchronized void put(String message) throws InterruptedException {
        while (count == capacity) {
            wait(); // Чекаємо, поки з'явиться місце
        }
        
        buffer[tail] = message;
        tail = (tail + 1) % capacity; 
        count++;
        notifyAll(); 
    }

    // Метод для Consumer (забрати дані)
    public synchronized String get() throws InterruptedException {
        while (count == 0) {
            wait(); // Чекаємо, поки з'являться дані
        }

        String message = buffer[head];
        head = (head + 1) % capacity; // Кільцевий зсув
        count--;
        notifyAll(); // Повідомляємо Producer-ам, що з'явилося місце
        return message;
    }
}
