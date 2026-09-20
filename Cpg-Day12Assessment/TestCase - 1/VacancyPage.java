package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VacancyPage {

    WebDriver driver;
    WebDriverWait wait;

    By vacancyName = By.xpath(
            "//label[text()='Vacancy Name']/ancestor::div[contains(@class,'oxd-input-group')]//input"
    );

    By jobTitleDropdown = By.xpath(
            "//label[text()='Job Title']/ancestor::div[contains(@class,'oxd-input-group')]//div[contains(@class,'oxd-select-text')]"
    );

    By description = By.xpath(
            "//label[text()='Description']/ancestor::div[contains(@class,'oxd-input-group')]//textarea"
    );

    By hiringManager = By.xpath(
            "//label[text()='Hiring Manager']/ancestor::div[contains(@class,'oxd-input-group')]//input"
    );

    By numberOfPositions = By.xpath(
            "//label[text()='Number of Positions']/ancestor::div[contains(@class,'oxd-input-group')]//input"
    );

    By saveButton = By.xpath("//button[@type='submit']");

    public VacancyPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void enterVacancyName(String name) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(vacancyName))
                .sendKeys(name);
    }

    public void selectJobTitle(String jobTitle) {

        wait.until(ExpectedConditions.elementToBeClickable(jobTitleDropdown))
                .click();

        By jobTitleOption = By.xpath(
                "//div[@role='option']//span[normalize-space()='" + jobTitle + "']"
        );

        wait.until(ExpectedConditions.elementToBeClickable(jobTitleOption))
                .click();
    }

    public void enterDescription(String text) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(description))
                .sendKeys(text);
    }

    public void selectHiringManager(String manager) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(hiringManager))
                .sendKeys(manager);

        By managerOption = By.xpath(
                "//div[@role='option']//span[contains(normalize-space(),'" + manager + "')]"
        );

        wait.until(ExpectedConditions.elementToBeClickable(managerOption))
                .click();
    }

    public void enterNumberOfPositions(String positions) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(numberOfPositions))
                .sendKeys(positions);
    }

    public void clickSave() {

        wait.until(ExpectedConditions.elementToBeClickable(saveButton))
                .click();
    }

    public boolean isVacancySaved() {

        By vacancyListHeading = By.xpath(
                "//h6[normalize-space()='Vacancies']"
        );

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(vacancyListHeading)
        ).isDisplayed();
    }
}