package com.similiz.dictionary.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PropertiesUtilTest {
    private Properties properties;

    @BeforeEach
    void initProperties() {
        properties = new Properties();
        try (InputStream inputStream = PropertiesUtil.class.getClassLoader()
                .getResourceAsStream("application.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getterReturnsCorrectValue() {
        for (Object obj : properties.keySet()) {
            String key = (String) obj;
            assertThat(properties.getProperty(key))
                    .isEqualTo(PropertiesUtil.get(key));
        }
    }

    @Test
    void getterThrowsExceptionIfCanNotLoadFile() {
        String propertiesFile = "application.properties";
        try (InputStream inputStream = PropertiesUtil.class.getClassLoader()
                .getResourceAsStream(propertiesFile)) {

//            File file = new File("");
//
//            assertThat(inputStream).isNotNull();
//            FileUtils.copyInputStreamToFile(inputStream, file);

            Field propertyField = PropertiesUtil.class.getDeclaredField("PROPERTIES");
            propertyField.setAccessible(true);
            Properties property = (Properties) propertyField.get(PropertiesUtil.class);
            assertThrows(IOException.class, () -> property.load(inputStream));
        } catch (NoSuchFieldException | IllegalAccessException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
