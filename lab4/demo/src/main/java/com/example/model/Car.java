package com.example.model;

public class Car<T extends Human> extends Vehicle<T> {
    public Car(int maxSeats) {
        super(maxSeats);
    }
}