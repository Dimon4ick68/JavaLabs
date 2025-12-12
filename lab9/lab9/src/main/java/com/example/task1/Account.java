package com.example.task1;

import java.util.concurrent.atomic.AtomicInteger;

public class Account {
    private static final AtomicInteger idGenerator = new AtomicInteger(0);
    
    private final int id;
    private int balance;

    public Account(int initialBalance) {
        this.id = idGenerator.incrementAndGet();
        this.balance = initialBalance;
    }

    public int getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }

    // Методи не синхронізовані тут, бо ми будемо синхронізувати їх у Bank
    public void withdraw(int amount) {
        this.balance -= amount;
    }

    public void deposit(int amount) {
        this.balance += amount;
    }
    
    @Override
    public String toString() {
        return "Acc" + id + "(" + balance + ")";
    }
}
