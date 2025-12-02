package com.example.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class VehicleTest {

    @Test
    void testTaxiCanTakeAnyPassenger() {
        // Taxi приймає <Human>, тому туди можуть сісти всі
        Taxi taxi = new Taxi(3);
        Human human = new Human("John");
        Firefighter fireman = new Firefighter("Sam");
        Policeman cop = new Policeman("Rob");

        assertDoesNotThrow(() -> {
            taxi.addPassenger(human);
            taxi.addPassenger(fireman);
            taxi.addPassenger(cop);
        });

        assertEquals(3, taxi.getOccupiedSeats());
    }

    @Test
    void testPoliceCarTakesPoliceman() {
        PoliceCar policeCar = new PoliceCar(2);
        Policeman cop = new Policeman("Officer Dibble");

        assertDoesNotThrow(() -> policeCar.addPassenger(cop));
        assertEquals(1, policeCar.getOccupiedSeats());
    }

    @Test
    void testFireTruckTakesFirefighter() {
        FireTruck fireTruck = new FireTruck(2);
        Firefighter fireman = new Firefighter("Fireman Sam");

        assertDoesNotThrow(() -> fireTruck.addPassenger(fireman));
        assertEquals(1, fireTruck.getOccupiedSeats());
    }

    @Test
    void testVehicleThrowsExceptionWhenFull() {
        Taxi taxi = new Taxi(1); // Тільки 1 місце
        Human passenger1 = new Human("P1");
        Human passenger2 = new Human("P2");

        // Садимо першого - все ок
        assertDoesNotThrow(() -> taxi.addPassenger(passenger1));

        // Садимо другого - має бути помилка
        Exception exception = assertThrows(Exception.class, () -> {
            taxi.addPassenger(passenger2);
        });

        assertEquals("Немає вільних місць у Taxi", exception.getMessage());
    }

    @Test
    void testRemovePassengerWorks() {
        Bus bus = new Bus(10);
        Human passenger = new Human("Passenger");

        assertDoesNotThrow(() -> {
            bus.addPassenger(passenger);
            assertEquals(1, bus.getOccupiedSeats());
            
            bus.removePassenger(passenger);
            assertEquals(0, bus.getOccupiedSeats());
        });
    }

    @Test
    void testRemovePassengerThrowsExceptionIfNotFound() {
        Bus bus = new Bus(10);
        Human passenger = new Human("Ghost");

        Exception exception = assertThrows(Exception.class, () -> {
            bus.removePassenger(passenger);
        });

        assertEquals("Пасажира немає в Bus", exception.getMessage());
    }
}