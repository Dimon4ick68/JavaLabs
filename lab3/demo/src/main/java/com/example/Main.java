package com.example;

import com.example.controller.ShapeController;
import com.example.view.ShapeView;

public class Main {
    public static void main(String[] args) {
        // Ініціалізація MVC
        ShapeView view = new ShapeView();
        ShapeController controller = new ShapeController(view);
        
        // Запуск
        controller.run();
    }
}