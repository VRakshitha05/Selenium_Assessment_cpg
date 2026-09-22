package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import utils.ConfigReader;

public class BaseClass {

    public WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUp() {

        System.out.println(
                "========== BROWSER SETUP STARTED =========="
        );

        try {

            String browser =
                    ConfigReader.getProperty("browser");

            System.out.println(
                    "Browser: " + browser
            );

            if (browser == null || browser.trim().isEmpty()) {

                throw new RuntimeException(
                        "browser is missing from config.properties"
                );
            }

            if (browser.equalsIgnoreCase("chrome")) {

                ChromeOptions options =
                        new ChromeOptions();

                options.addArguments(
                        "--start-maximized"
                );

                options.addArguments(
                        "--disable-notifications"
                );

                System.out.println(
                        "Creating ChromeDriver..."
                );

                driver =
                        new ChromeDriver(options);

                System.out.println(
                        "ChromeDriver created successfully"
                );

            } else {

                throw new RuntimeException(
                        "Unsupported browser: "
                        + browser
                );
            }

            driver.manage().timeouts().implicitlyWait(
                    Duration.ofSeconds(5)
            );

            driver.manage().timeouts().pageLoadTimeout(
                    Duration.ofSeconds(30)
            );

            String url =
                    ConfigReader.getProperty("url");

            System.out.println(
                    "URL: " + url
            );

            if (url == null || url.trim().isEmpty()) {

                throw new RuntimeException(
                        "url is missing from config.properties"
                );
            }

            System.out.println(
                    "Opening URL: " + url
            );

            driver.get(url);

            System.out.println(
                    "URL opened successfully"
            );

            System.out.println(
                    "========== BROWSER SETUP COMPLETED =========="
            );

        } catch (Exception e) {

            System.out.println(
                    "========== BROWSER SETUP FAILED =========="
            );

            e.printStackTrace();

            throw e;
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {

        System.out.println(
                "========== TEAR DOWN STARTED =========="
        );

        if (driver != null) {

            driver.quit();

            System.out.println(
                    "Browser closed successfully"
            );

        } else {

            System.out.println(
                    "Driver is null"
            );
        }

        System.out.println(
                "========== TEAR DOWN COMPLETED =========="
        );
    }
}