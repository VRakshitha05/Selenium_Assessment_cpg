package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties =
            new Properties();

    private static boolean loaded = false;


    public static void loadProperties() {

        if (loaded) {
            return;
        }


        try {

            InputStream inputStream =
                    ConfigReader.class
                            .getClassLoader()
                            .getResourceAsStream(
                                    "config.properties");


            if (inputStream == null) {

                throw new RuntimeException(
                        "config.properties not found "
                        + "in src/test/resources");
            }


            properties.load(inputStream);

            inputStream.close();

            loaded = true;


            System.out.println(
                    "config.properties loaded successfully");


        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to load config.properties",
                    e);
        }
    }


    public static String getProperty(
            String key) {

        if (!loaded) {
            loadProperties();
        }


        String value =
                properties.getProperty(key);


        if (value == null) {

            throw new RuntimeException(
                    "Property '" + key
                    + "' not found in "
                    + "config.properties");
        }


        return value.trim();
    }
}