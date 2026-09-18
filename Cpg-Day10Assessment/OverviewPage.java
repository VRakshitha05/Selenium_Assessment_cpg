
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OverviewPage {

    WebDriver driver;

    By finishButton = By.id("finish");

    By successMessage = By.className("complete-header");

    public OverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFinish() {
        driver.findElement(finishButton).click();
    }

    public String getSuccessMessage() {
        return driver.findElement(successMessage).getText();
    }
}