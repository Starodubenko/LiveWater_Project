package com.epam.star.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {

    private static final Properties PROPERTIES = new Properties();

    public PropertiesManager(String fileName) throws IOException {

        InputStream stream = PropertiesManager.class.getClassLoader().getResourceAsStream(fileName);
        try {
            PROPERTIES.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }

    public int getIntProperty(String key) {
        String proprty = PROPERTIES.getProperty(key);
        return Integer.parseInt(proprty);
    }
}
