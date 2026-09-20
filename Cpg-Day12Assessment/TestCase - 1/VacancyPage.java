package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VacancyPage {

    WebDriver driver;
    WebDriverWait wait;

    By vacancyName = By.xpath(
            "//label[normalize-space()='Vacancy Name']"
            + "/ancestor::div[contains(@class,'oxd-input-group')]//input");

    By jobTitleDropdown = By.xpath(
            "//label[normalize-space()='Job Title']"
            + "/ancestor::div[contains(@class,'oxd-input-group')]"
            + "//div[contains(@class,'oxd-select-text')]");

    By description = By.xpath(
            "//label[normalize-space()='Description']"
            + "/ancestor::div[contains(@class,'oxd-input-group')]//textarea");

    By hiringManager = By.xpath(
            "//label[normalize-space()='Hiring Manager']"
            + "/ancestor::div[contains(@class,'oxd-input-group')]//input");

    By numberOfPositions = By.xpath(
            "//label[normalize-space()='Number of Positions']"
            + "/ancestor::div[contains(@class,'oxd-input-group')]//input");

    By saveButton = By.xpath(
            "//button[@type='submit']");

    public VacancyPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void enterVacancyName(String name) {

        WebElement field = wait.until(ExpectedConditions
                .visibilityOfElementLocated(vacancyName));

        field.clear();
        field.sendKeys(name);
    }

    public void selectJobTitle(String jobTitle) {

        wait.until(ExpectedConditions
                .elementToBeClickable(jobTitleDropdown))
                .click();

        By jobTitleOption = By.xpath(
                "//div[@role='option']//span[normalize-space()='"
                + jobTitle + "']");

        wait.until(ExpectedConditions
                .elementToBeClickable(jobTitleOption))
                .click();
    }

    public void enterDescription(String text) {

        WebElement field = wait.until(ExpectedConditions
                .visibilityOfElementLocated(description));

        field.clear();
        field.sendKeys(text);
    }

    public void selectHiringManager(String managerName) {

        WebElement managerField = wait.until(ExpectedConditions
                .elementToBeClickable(hiringManager));

        managerField.click();
        managerField.clear();
        managerField.sendKeys(managerName);

        // Select the first available suggestion.
        // This supports names such as Rahul Mulge Patil.
        By suggestion = By.xpath(
                "//div[@role='option'][1]");

        wait.until(ExpectedConditions
                .elementToBeClickable(suggestion))
                .click();
    }

    public void enterNumberOfPositions(String positions) {

        WebElement field = wait.until(ExpectedConditions
                .visibilityOfElementLocated(numberOfPositions));

        field.clear();
        field.sendKeys(positions);
    }

    public void clickSave() {

        wait.until(ExpectedConditions
                .elementToBeClickable(saveButton))
                .click();
    }
}
