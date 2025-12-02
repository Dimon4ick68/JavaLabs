package com.example.lab3.controller;

import java.util.Arrays; // Імпортуємо всі моделі
import java.util.Random; // Імпортуємо View

import com.example.lab3.models.Circle;
import com.example.lab3.models.Rectangle;
import com.example.lab3.models.Shape;
import com.example.lab3.models.ShapeAreaComparator;
import com.example.lab3.models.ShapeColorComparator;
import com.example.lab3.models.Triangle;
import com.example.lab3.view.ShapeView;

public class ShapeController {
    private Shape[] shapes;
    private ShapeView view;

    public ShapeController(ShapeView view) {
        this.view = view;
        this.shapes = new Shape[12]; // Масив мінімум на 10 елементів
    }
    
    public Shape[] getShapes() {
        return shapes;
    }

    public void setShapes(Shape[] shapes) {
        this.shapes = shapes;
    }

    public void generateData() {
        String[] colors = { "Red", "Green", "Blue", "Yellow", "Black", "White" };
        Random rand = new Random();

        for (int i = 0; i < shapes.length; i++) {
            int type = rand.nextInt(3);
            String color = colors[rand.nextInt(colors.length)];

            switch (type) {
                case 0 -> shapes[i] = new Rectangle(color, 1 + rand.nextInt(20), 1 + rand.nextInt(20));
                case 1 -> shapes[i] = new Triangle(color, 1 + rand.nextInt(20), 1 + rand.nextInt(20));
                case 2 -> shapes[i] = new Circle(color, 1 + rand.nextInt(10));
            }
        }
    }

    public void run() {
        generateData();

        view.printMessage("Initial Data Set:");
        view.printShapes(shapes);

        // Сумарна площа
        double totalArea = 0;
        for (Shape s : shapes) {
            totalArea += s.calcArea();
        }
        view.printTotalArea(totalArea);

        // Площа конкретних типів
        view.printTotalAreaByType("Rectangle", calculateAreaByType(Rectangle.class));
        view.printTotalAreaByType("Triangle", calculateAreaByType(Triangle.class));
        view.printTotalAreaByType("Circle", calculateAreaByType(Circle.class));

        // Сортування за площею
        Arrays.sort(shapes, new ShapeAreaComparator());
        view.printMessage("Sorted by Area:");
        view.printShapes(shapes);

        // Сортування за кольором
        Arrays.sort(shapes, new ShapeColorComparator());
        view.printMessage("Sorted by Color:");
        view.printShapes(shapes);
    }

    private double calculateAreaByType(Class<?> type) {
        double sum = 0;
        for (Shape s : shapes) {
            if (type.isInstance(s)) {
                sum += s.calcArea();
            }
        }
        return sum;
    }
}