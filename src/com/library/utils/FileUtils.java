package com.library.utils;

import java.io.*;
import java.util.List;

public class FileUtils {

    // Save list of objects to file
    public static <T> void saveToFile(List<T> list, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(list);
        } catch (IOException e) {
            System.out.println("❌ Error saving file: " + filePath);
            e.printStackTrace();
        }
    }

    // Load list of objects from file
    @SuppressWarnings("unchecked")
    public static <T> List<T> loadFromFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) return null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("❌ Error loading file: " + filePath);
            e.printStackTrace();
        }
        return null;
    }
}
