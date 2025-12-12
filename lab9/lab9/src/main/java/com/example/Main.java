package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.task1.Account;
import com.example.task1.Bank;
import com.example.task2.ProducerConsumerDemo;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== LAB 9: Multi-threading ===");
            System.out.println("1. Task 1: Bank Transfer (Deadlock Free)");
            System.out.println("2. Task 2: Producer-Consumer (Circular Buffer)");
            System.out.println("0. Вихід");
            System.out.print("Вибір: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> runTask1();
                case "2" -> new ProducerConsumerDemo().run();
                case "0" -> System.exit(0);
                default -> System.out.println("Невірний вибір.");
            }
        }
    }

    private static void runTask1() {
        System.out.println("Створення 100 рахунків...");
        List<Account> accounts = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            accounts.add(new Account(1000)); // 1000 умовних одиниць на кожному
        }
        Bank bank = new Bank(accounts);
        bank.testBankOperations();
    }
}