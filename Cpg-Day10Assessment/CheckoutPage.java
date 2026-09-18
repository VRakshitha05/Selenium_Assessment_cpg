
package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {

    WebDriver driver;
    WebDriverWait wait;

    // Locators
    By firstName = By.id("first-name");
    By lastName = By.id("last-name");
    By postalCode = By.id("postal-code");
    By continueButton = By.id("continue");
    By overviewTitle = By.className("title");

    // Constructor
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(20));
    }

    // Enter First Name
    public void enterFirstName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                firstName)).clear();

        driver.findElement(firstName).sendKeys(name);
    }

    // Enter Last Name
    public void enterLastName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                lastName)).clear();

        driver.findElement(lastName).sendKeys(name);
    }

    // Enter Postal Code
    public void enterPostalCode(String code) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                postalCode)).clear();

        driver.findElement(postalCode).sendKeys(code);
    }

    // Click Continue
    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(
                continueButton)).click();
    }

    // Fill all checkout details
    public void fillCheckoutDetails(
            String fname, String lname, String postal) {

        System.out.println("Entering checkout details...");

        enterFirstName(fname);
        System.out.println("First Name entered: " + fname);

        enterLastName(lname);
        System.out.println("Last Name entered: " + lname);

        enterPostalCode(postal);
        System.out.println("Postal Code entered: " + postal);

        clickContinue();

        System.out.println("Clicked Continue");
    }

    // Verify Overview page
    public String getOverviewTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                overviewTitle)).getText();
    }
}