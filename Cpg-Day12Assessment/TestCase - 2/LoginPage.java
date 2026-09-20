package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;

    private WebDriverWait wait;


    // ==========================================
    // Login Page Locators
    // ==========================================

    private By usernameField =
            By.cssSelector(
                    "input[name='username']");


    private By passwordField =
            By.cssSelector(
                    "input[name='password']");


    private By loginButton =
            By.cssSelector(
                    "button[type='submit']");


    // ==========================================
    // User Menu Locators
    // ==========================================

    private By userDropdown =
            By.cssSelector(
                    ".oxd-userdropdown");


    private By logoutLink =
            By.xpath(
                    "//a[normalize-space()='Logout']");


    // ==========================================
    // Constructor
    // ==========================================

    public LoginPage(WebDriver driver) {

        this.driver = driver;

        this.wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(20));
    }


    // ==========================================
    // Login
    // ==========================================

    public void login(
            String username,
            String password) {


        System.out.println(
                "Entering username...");


        WebElement usernameElement =
                wait.until(
                        ExpectedConditions
                                .visibilityOfElementLocated(
                                        usernameField));


        usernameElement.clear();

        usernameElement.sendKeys(
                username);


        System.out.println(
                "Entering password...");


        WebElement passwordElement =
                wait.until(
                        ExpectedConditions
                                .visibilityOfElementLocated(
                                        passwordField));


        passwordElement.clear();

        passwordElement.sendKeys(
                password);


        System.out.println(
                "Clicking Login...");


        wait.until(
                ExpectedConditions
                        .elementToBeClickable(
                                loginButton))
                .click();


        // Wait for dashboard
        wait.until(
                ExpectedConditions.urlContains(
                        "/web/index.php/dashboard"));


        System.out.println(
                "Login successful");
    }


    // ==========================================
    // Logout
    // ==========================================

    public void logout() {


        System.out.println(
                "Logging out...");


        wait.until(
                ExpectedConditions
                        .elementToBeClickable(
                                userDropdown))
                .click();


        wait.until(
                ExpectedConditions
                        .elementToBeClickable(
                                logoutLink))
                .click();


        wait.until(
                ExpectedConditions.urlContains(
                        "/web/index.php/auth/login"));


        System.out.println(
                "Logout successful");
    }
}