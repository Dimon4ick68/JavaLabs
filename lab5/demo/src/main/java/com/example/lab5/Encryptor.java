package com.example.lab5;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Encryptor {

    // Клас для шифрування (FilterOutputStream)
    private static class MyEncryptionFilter extends FilterOutputStream {
        private final int key;

        public MyEncryptionFilter(OutputStream out, int key) {
            super(out);
            this.key = key;
        }

        @Override
        public void write(int b) throws IOException {
            super.write(b + key); // Зсув байта
        }
    }

    // Клас для дешифрування (FilterInputStream)
    private static class MyDecryptionFilter extends FilterInputStream {
        private final int key;

        public MyDecryptionFilter(InputStream in, int key) {
            super(in);
            this.key = key;
        }

        @Override
        public int read() throws IOException {
            int b = super.read();
            if (b == -1) return -1;
            return b - key; // Зворотній зсув
        }
    }

    public void processFile(String src, String dest, int key, boolean encrypt) {
        try {
            if (encrypt) {
                try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(src));
                     MyEncryptionFilter out = new MyEncryptionFilter(new FileOutputStream(dest), key)) {
                    int b;
                    while ((b = in.read()) != -1) out.write(b);
                }
                System.out.println("Файл зашифровано.");
            } else {
                try (MyDecryptionFilter in = new MyDecryptionFilter(new FileInputStream(src), key);
                     BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(dest))) {
                    int b;
                    while ((b = in.read()) != -1) out.write(b);
                }
                System.out.println("Файл розшифровано.");
            }
        } catch (IOException e) {
            System.err.println("Помилка: " + e.getMessage());
        }
    }
}