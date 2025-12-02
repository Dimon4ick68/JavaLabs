package com.example.lab3.models;

public class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(String shapeColor, double width, double height) {
        super(shapeColor);
        this.width = width;
        this.height = height;
    }

    @Override
    public double calcArea() {
        return width * height;
    }

    @Override
    public void draw() {
        System.out.println("Malyuyu pryamokutnyk (" + getShapeColor() + ")");
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", Area: %.2f (w=%.1f, h=%.1f)", calcArea(), width, height);
    }
}