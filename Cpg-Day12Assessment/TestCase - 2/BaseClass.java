package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import utilities.ConfigReader;

public class BaseClass {

    protected static WebDriver driver;


    @BeforeSuite
    public void setup() {

        System.out.println(
                "========== Test Suite Started ==========");

        try {

            // Load configuration
            ConfigReader.loadProperties();


            // Get application URL
            String url =
                    ConfigReader.getProperty("url");


            System.out.println(
                    "Opening URL: " + url);


            // Start Chrome
            driver = new ChromeDriver();


            // Maximize browser
            driver.manage()
                  .window()
                  .maximize();


            // Implicit wait
            driver.manage()
                  .timeouts()
                  .implicitlyWait(
                          Duration.ofSeconds(10));


            // Open application
            driver.get(url);


            System.out.println(
                    "Browser launched successfully");


            System.out.println(
                    "Current URL: "
                    + driver.getCurrentUrl());


        } catch (Exception e) {

            System.out.println(
                    "Browser setup failed");

            e.printStackTrace();

            throw new RuntimeException(
                    "Unable to start browser", e);
        }
    }


    @AfterSuite
    public void tearDown() {

        System.out.println(
                "========== Test Suite Finished ==========");


        if (driver != null) {

            System.out.println(
                    "Closing browser...");

            try {

                driver.quit();

            } catch (Exception e) {

                e.printStackTrace();
            }

            driver = null;
        }
    }
}