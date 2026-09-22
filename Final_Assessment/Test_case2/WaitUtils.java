package utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

    private WebDriver driver;
    private WebDriverWait wait;

    public WaitUtils(WebDriver driver) {

        this.driver = driver;

        int timeout = Integer.parseInt(
                ConfigReader.getProperty("timeout")
        );

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(timeout)
        );
    }

    public WebElement waitForElementVisible(By locator) {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }

    public WebElement waitForElementClickable(By locator) {

        return wait.until(
                ExpectedConditions.elementToBeClickable(locator)
        );
    }

    public boolean waitForElementPresent(By locator) {

        return wait.until(
                ExpectedConditions.presenceOfElementLocated(locator)
        ) != null;
    }

    public void waitForUrlContains(String text) {

        wait.until(
                ExpectedConditions.urlContains(text)
        );
    }
}