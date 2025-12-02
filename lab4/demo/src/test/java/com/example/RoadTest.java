package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.model.FireTruck;
import com.example.model.Firefighter;
import com.example.model.Human;
import com.example.model.PoliceCar;
import com.example.model.Policeman;
import com.example.model.Taxi;

class RoadTest {

    private Road road;

    @BeforeEach
    void setUp() {
        road = new Road();
    }

    @Test
    void testAddDifferentCarsToRoadAndCountHumans() throws Exception {
        // 1. Створюємо машини
        Taxi taxi = new Taxi(4);
        PoliceCar policeCar = new PoliceCar(2);
        FireTruck fireTruck = new FireTruck(2);

        // 2. Наповнюємо їх пасажирами
        taxi.addPassenger(new Human("Passenger 1"));
        taxi.addPassenger(new Human("Passenger 2")); // 2 людини в таксі

        policeCar.addPassenger(new Policeman("Officer 1")); // 1 поліцейський

        fireTruck.addPassenger(new Firefighter("Fireman 1")); 
        fireTruck.addPassenger(new Firefighter("Fireman 2")); // 2 пожежники

        // 3. Додаємо машини на дорогу
        // Завдяки List<Vehicle<? extends Human>> це працює для всіх типів
        road.addCarToRoad(taxi);
        road.addCarToRoad(policeCar);
        road.addCarToRoad(fireTruck);

        // 4. Перевіряємо підрахунок
        // 2 (таксі) + 1 (поліція) + 2 (пожежна) = 5
        assertEquals(5, road.getCountOfHumans());
    }

    @Test
    void testEmptyRoadCount() {
        assertEquals(0, road.getCountOfHumans());
    }
    
    @Test
    void testRoadWithEmptyCars() {
        Taxi emptyTaxi = new Taxi(3);
        road.addCarToRoad(emptyTaxi);
        assertEquals(0, road.getCountOfHumans());
    }
}