package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.WaitUtils;

public class DashboardPage {

    private WebDriver driver;
    private WaitUtils wait;

    private By buzzMenu =
            By.xpath(
                "//span[normalize-space()='Buzz']"
            );

    private By dashboardHeading =
            By.xpath(
                "//h6[normalize-space()='Dashboard']"
            );

    public DashboardPage(WebDriver driver) {

        this.driver = driver;

        this.wait =
                new WaitUtils(driver);
    }

    public boolean isDashboardDisplayed() {

        return wait.waitForElementPresent(
                dashboardHeading
        );
    }

    public void clickBuzz() {

        wait.waitForElementClickable(
                buzzMenu
        ).click();
    }
}