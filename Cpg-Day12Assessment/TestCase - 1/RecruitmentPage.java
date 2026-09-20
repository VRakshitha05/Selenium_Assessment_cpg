package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RecruitmentPage {

    WebDriver driver;
    WebDriverWait wait;

    By recruitmentMenu = By.xpath(
            "//span[normalize-space()='Recruitment']");

    By vacanciesMenu = By.xpath(
            "//a[normalize-space()='Vacancies']");

    By addButton = By.xpath(
            "//button[normalize-space()='Add']");

    public RecruitmentPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void openVacancyPage() {

        wait.until(ExpectedConditions
                .elementToBeClickable(recruitmentMenu))
                .click();

        wait.until(ExpectedConditions
                .elementToBeClickable(vacanciesMenu))
                .click();
    }

    public void clickAddVacancy() {

        wait.until(ExpectedConditions
                .elementToBeClickable(addButton))
                .click();
    }
}
