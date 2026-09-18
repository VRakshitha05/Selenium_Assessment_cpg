
package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import utilities.ConfigReader;

public class BaseClass {

    public static WebDriver driver;

    @BeforeSuite
    public void setup() {

        String browser = ConfigReader.getProperty("browser");

        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else {
            throw new RuntimeException(
                    "Unsupported browser: " + browser);
        }

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(10));

        driver.get(ConfigReader.getProperty("url"));
    }

    @AfterSuite
    public void tearDown() throws InterruptedException {

        // Keep the browser open for 5 seconds before closing
        Thread.sleep(5000);

        if (driver != null) {
            driver.quit();
        }
    }
}