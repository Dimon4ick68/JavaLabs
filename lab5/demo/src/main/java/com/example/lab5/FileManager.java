package com.example.lab5;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.example.lab3.models.Shape;

public class FileManager {

    // Серіалізація (Збереження у файл)
    public void saveShapes(Shape[] shapes, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(shapes);
            System.out.println("Дані успішно збережено у " + filePath);
        } catch (IOException e) {
            System.err.println("Помилка збереження: " + e.getMessage());
        }
    }

    // Десеріалізація (Читання з файлу)
    public Shape[] loadShapes(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Shape[]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Помилка зчитування: " + e.getMessage());
            return null;
        }
    }
}