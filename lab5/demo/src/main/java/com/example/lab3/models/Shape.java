package com.example.lab3.models;

import java.io.Serializable;

public abstract class Shape implements Drawable, Serializable {
    private String shapeColor;

    public Shape(String shapeColor) {
        this.shapeColor = shapeColor;
    }

    public String getShapeColor() {
        return shapeColor;
    }

    public abstract double calcArea();

    @Override
    public String toString() {
        return "Shape: " + this.getClass().getSimpleName() + 
               ", Color: " + shapeColor;
    }
}