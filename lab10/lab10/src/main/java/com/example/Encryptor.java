package com.example;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Encryptor {
    private static final Logger LOGGER = LoggerConfig.LOGGER;

    public void processFile(String src, String dest, int key, boolean encrypt) {
        LOGGER.log(Level.INFO, "Start processing file. Mode: {0}, Src: {1}", 
                   new Object[]{encrypt ? "Encrypt" : "Decrypt", src});

        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(src));
             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(dest))) {

            int b;
            while ((b = in.read()) != -1) {
                // Шифрування/Дешифрування (простий XOR або зсув)
                int processedByte = encrypt ? (b + key) : (b - key);
                out.write(processedByte);
            }
            
            LOGGER.log(Level.INFO, "File successfully processed to: {0}", dest);
            
        } catch (FileNotFoundException e) {
            // SEVERE - серйозна помилка, запишеться і в консоль, і в файл
            LOGGER.log(Level.SEVERE, "File not found: " + src, e);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IO Error during processing", e);
        }
    }
}