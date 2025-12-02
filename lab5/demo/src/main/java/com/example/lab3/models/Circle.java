package com.example.lab3.models;

public class Circle extends Shape {
    private double radius;

    public Circle(String shapeColor, double radius) {
        super(shapeColor);
        this.radius = radius;
    }

    @Override
    public double calcArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void draw() {
        System.out.println("Malyuyu kolo (" + getShapeColor() + ")");
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", Area: %.2f (r=%.1f)", calcArea(), radius);
    }
}