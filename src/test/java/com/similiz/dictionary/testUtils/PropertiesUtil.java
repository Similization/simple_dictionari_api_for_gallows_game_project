package com.similiz.dictionary.testUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }
    private static void loadProperties() {
        try (InputStream inputStream = com.similiz.dictionary.util.PropertiesUtil.class.getClassLoader().getResourceAsStream("applicationTest.properties")) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private PropertiesUtil() {
    }
}
