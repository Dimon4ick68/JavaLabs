package com.example.task1;

import java.util.List;
import java.util.Random;

public class Bank {
    private List<Account> accounts;

    public Bank(List<Account> accounts) {
        this.accounts = accounts;
    }

    // Thread-safe метод переказу
    public void transfer(Account from, Account to, int amount) {
        Account firstLock = from.getId() < to.getId() ? from : to;
        Account secondLock = from.getId() < to.getId() ? to : from;

        synchronized (firstLock) {
            synchronized (secondLock) {
                if (from.getBalance() >= amount) {
                    from.withdraw(amount);
                    to.deposit(amount);
                }
            }
        }
    }

    // Підрахунок загальної суми грошей у банку
    public long getTotalBalance() {
        long total = 0;

        for (Account account : accounts) {
            synchronized (account) {
                total += account.getBalance();
            }
        }
        return total;
    }

    public void testBankOperations() {
        System.out.println("Початковий загальний баланс: " + getTotalBalance());
        long startBalance = getTotalBalance();

        int threadCount = 2000;
        Thread[] threads = new Thread[threadCount];
        Random rand = new Random();

        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(() -> {
                Account from = accounts.get(rand.nextInt(accounts.size()));
                Account to = accounts.get(rand.nextInt(accounts.size()));
                int amount = rand.nextInt(100);

                if (from != to) {
                    transfer(from, to, amount);
                }
            });
            threads[i].start();
        }

        // Чекаємо завершення
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long endBalance = getTotalBalance();
        System.out.println("Кінцевий загальний баланс:   " + endBalance);

        if (startBalance == endBalance) {
            System.out.println(" Тест пройдено успішно! Гроші не зникли.");
        } else {
            System.out.println(" Помилка! Баланс не збігається.");
        }
    }
}