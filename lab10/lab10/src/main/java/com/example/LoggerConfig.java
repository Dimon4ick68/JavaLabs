package com.example;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerConfig {
    // Створюємо глобальний логер для нашого пакету
    public static final Logger LOGGER = Logger.getLogger(LoggerConfig.class.getName());

    public static void setup() {
        try {

            LogManager.getLogManager().reset();
            LOGGER.setLevel(Level.ALL);


            ConsoleHandler ch = new ConsoleHandler();
            ch.setLevel(Level.INFO);
            LOGGER.addHandler(ch);


            FileHandler fh = new FileHandler("lab10_app.log", true);
            fh.setLevel(Level.ALL);
            fh.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fh);

        } catch (IOException e) {
            System.err.println("Не вдалося налаштувати логування: " + e.getMessage());
        }
    }
}