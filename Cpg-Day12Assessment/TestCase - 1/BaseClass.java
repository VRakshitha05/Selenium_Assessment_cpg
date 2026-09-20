package base;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import utilities.ConfigReader;

public class BaseClass {

    public static WebDriver driver;

    @BeforeSuite
    public void setup() {

        String browser = ConfigReader.getProperty("browser");

        if (browser.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();

            Map<String, Object> preferences = new HashMap<>();

            preferences.put("credentials_enable_service", false);
            preferences.put("profile.password_manager_enabled", false);
            preferences.put("profile.password_manager_leak_detection", false);

            options.setExperimentalOption("prefs", preferences);

            driver = new ChromeDriver(options);

        } else {
            throw new RuntimeException("Unsupported browser: " + browser);
        }

        driver.manage().window().maximize();

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(5));

        driver.get(ConfigReader.getProperty("url"));
    }

    @AfterSuite
    public void tearDown() throws InterruptedException {

        Thread.sleep(5000);

        if (driver != null) {
            driver.quit();
        }
    }
}