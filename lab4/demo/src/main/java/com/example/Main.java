package com.example;

import com.example.model.FireTruck; // Імпортуємо всі моделі
import com.example.model.Firefighter;
import com.example.model.Human;
import com.example.model.PoliceCar;
import com.example.model.Policeman;
import com.example.model.Taxi;

public class Main {
    public static void main(String[] args) {
        // Створюємо людей
        Human humanJohn = new Human("John");
        Firefighter firemanSam = new Firefighter("Sam");
        Policeman copRob = new Policeman("Rob");

        // Створюємо транспорт
        Taxi taxi = new Taxi(4);
        FireTruck fireTruck = new FireTruck(2);
        PoliceCar policeCar = new PoliceCar(2);

        try {
            System.out.println("--- Посадка ---");
            // Таксі везе всіх
            taxi.addPassenger(humanJohn);
            taxi.addPassenger(firemanSam);

            // Спецтранспорт - тільки своїх
            fireTruck.addPassenger(firemanSam);
            policeCar.addPassenger(copRob);
            
            // Цей рядок викличе помилку компіляції, якщо розкоментувати (і це правильно!):
            // policeCar.addPassenger(humanJohn); 

            System.out.println("\n--- Дорога ---");
            Road road = new Road();
            road.addCarToRoad(taxi);
            road.addCarToRoad(fireTruck);
            road.addCarToRoad(policeCar);

            System.out.println("Всього людей на дорозі: " + road.getCountOfHumans());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}