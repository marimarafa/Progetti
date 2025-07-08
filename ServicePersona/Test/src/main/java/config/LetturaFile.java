package main.java.config;

import java.io.*;
import java.util.Properties;

public class LetturaFile {

    public String getFile(String path) {
        Properties prop = new Properties();
        try (FileInputStream file = new FileInputStream(path)) {
            prop.load(file);
            return prop.getProperty("persone");
        } catch (IOException e) {
            System.err.println("Errore nella lettura del file properties: " + e.getMessage());
            return null;
        }
    }
}

