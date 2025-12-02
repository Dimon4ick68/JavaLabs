package com.example.model;

import java.util.ArrayList;
import java.util.List;

// T extends Human — це обмеження: пасажирами можуть бути тільки люди
public abstract class Vehicle<T extends Human> {
    private int maxSeats;
    private List<T> passengers = new ArrayList<>();

    public Vehicle(int maxSeats) {
        this.maxSeats = maxSeats;
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public int getOccupiedSeats() {
        return passengers.size();
    }

    public void addPassenger(T passenger) throws Exception {
        if (passengers.size() >= maxSeats) {
            throw new Exception("Немає вільних місць у " + this.getClass().getSimpleName());
        }
        passengers.add(passenger);
        System.out.println(passenger + " сів у " + this.getClass().getSimpleName());
    }

    public void removePassenger(T passenger) throws Exception {
        if (!passengers.contains(passenger)) {
            throw new Exception("Пасажира немає в " + this.getClass().getSimpleName());
        }
        passengers.remove(passenger);
        System.out.println(passenger + " вийшов з " + this.getClass().getSimpleName());
    }
}