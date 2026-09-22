package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.WaitUtils;

public class LoginPage {

    private WebDriver driver;
    private WaitUtils wait;

    // Locators
    private By usernameField =
            By.name("username");

    private By passwordField =
            By.name("password");

    private By loginButton =
            By.xpath("//button[@type='submit']");

    private By dashboardText =
            By.xpath("//h6[normalize-space()='Dashboard']");

    public LoginPage(WebDriver driver) {

        this.driver = driver;

        this.wait =
                new WaitUtils(driver);
    }

    public void enterUsername(String username) {

        wait.waitForElementVisible(
                usernameField
        ).sendKeys(username);
    }

    public void enterPassword(String password) {

        wait.waitForElementVisible(
                passwordField
        ).sendKeys(password);
    }

    public void clickLogin() {

        wait.waitForElementClickable(
                loginButton
        ).click();
    }

    public void login(
            String username,
            String password) {

        enterUsername(username);

        enterPassword(password);

        clickLogin();
    }

    public boolean isDashboardDisplayed() {

        return wait.waitForElementPresent(
                dashboardText
        );
    }
}