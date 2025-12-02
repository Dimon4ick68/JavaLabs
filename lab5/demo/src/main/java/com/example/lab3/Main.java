package com.example.lab3;

import com.example.lab3.controller.ShapeController;
import com.example.lab3.view.ShapeView;

public class Main {
    public static void main(String[] args) {
        // Ініціалізація MVC
        ShapeView view = new ShapeView();
        ShapeController controller = new ShapeController(view);
        
        // Запуск
        controller.run();
    }
}