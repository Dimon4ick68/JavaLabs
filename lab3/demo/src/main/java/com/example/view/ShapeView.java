package com.example.view;

import com.example.models.Shape;

public class ShapeView {
    public void printShapes(Shape[] shapes) {
        System.out.println("--------------------------------------------------");
        for (Shape shape : shapes) {
            System.out.println(shape);
        }
        System.out.println("--------------------------------------------------");
    }

    public void printTotalArea(double area) {
        System.out.printf("Total area: %.2f%n", area);
    }

    public void printTotalAreaByType(String type, double area) {
        System.out.printf("Total area of %s: %.2f%n", type, area);
    }

    public void printMessage(String message) {
        System.out.println("\n>>> " + message);
    }
}