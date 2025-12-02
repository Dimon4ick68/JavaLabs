package com.example;

import java.util.ArrayList;
import java.util.List;

import com.example.model.Human;
import com.example.model.Vehicle;

public class Road {
    // Список машин, які везуть "когось, хто є Human"
    public List<Vehicle<? extends Human>> carsInRoad = new ArrayList<>();

    public void addCarToRoad(Vehicle<? extends Human> vehicle) {
        carsInRoad.add(vehicle);
        System.out.println(vehicle.getClass().getSimpleName() + " виїхав на дорогу.");
    }

    public int getCountOfHumans() {
        int count = 0;
        for (Vehicle<? extends Human> vehicle : carsInRoad) {
            count += vehicle.getOccupiedSeats();
        }
        return count;
    }
}