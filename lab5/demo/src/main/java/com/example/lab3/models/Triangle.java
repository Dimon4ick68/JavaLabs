package com.example.lab3.models;

public class Triangle extends Shape {
    private double base;
    private double height;

    public Triangle(String shapeColor, double base, double height) {
        super(shapeColor);
        this.base = base;
        this.height = height;
    }

    @Override
    public double calcArea() {
        return 0.5 * base * height;
    }

    @Override
    public void draw() {
        System.out.println("Malyuyu trykutnyk (" + getShapeColor() + ")");
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", Area: %.2f (base=%.1f, h=%.1f)", calcArea(), base, height);
    }
}