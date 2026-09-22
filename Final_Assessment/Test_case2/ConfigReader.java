package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {
        loadProperties();
    }

    private static void loadProperties() {

        properties = new Properties();

        String path = System.getProperty("user.dir")
                + "/src/main/resources/config.properties";

        try (FileInputStream fis = new FileInputStream(path)) {

            properties.load(fis);

            System.out.println("config.properties loaded successfully");

        } catch (IOException e) {

            System.out.println("Unable to load config.properties");

            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {

        return properties.getProperty(key);
    }
}