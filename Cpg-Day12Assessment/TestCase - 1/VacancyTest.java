package tests;

import org.testng.annotations.Test;

import base.BaseClass;
import pages.LoginPage;
import pages.RecruitmentPage;
import pages.VacancyPage;
import utilities.ExcelReader;

public class VacancyTest extends BaseClass {

    LoginPage loginPage;
    RecruitmentPage recruitmentPage;
    VacancyPage vacancyPage;

    @Test
    public void createVacancyTest() {

        String username = ExcelReader.getData("Sheet1", 1, 0);
        String password = ExcelReader.getData("Sheet1", 1, 1);
        String vacancyName = ExcelReader.getData("Sheet1", 1, 2);
        String jobTitle = ExcelReader.getData("Sheet1", 1, 3);
        String vacancyDescription = ExcelReader.getData("Sheet1", 1, 4);
        String hiringManager = ExcelReader.getData("Sheet1", 1, 5);
        String positions = ExcelReader.getData("Sheet1", 1, 6);

        System.out.println("Step 1: Logging in");

        loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        System.out.println("Login successful");

        System.out.println("Step 2: Opening Recruitment and Vacancies");

        recruitmentPage = new RecruitmentPage(driver);
        recruitmentPage.openVacancyPage();

        System.out.println("Recruitment and Vacancies opened");

        System.out.println("Step 3: Clicking Add button");

        recruitmentPage.clickAddVacancy();

        System.out.println("Add Vacancy page opened");

        vacancyPage = new VacancyPage(driver);

        System.out.println("Step 4: Entering vacancy name");

        vacancyPage.enterVacancyName(vacancyName);

        System.out.println("Vacancy name entered");

        System.out.println("Step 5: Selecting job title");

        vacancyPage.selectJobTitle(jobTitle);

        System.out.println("Job title selected");

        System.out.println("Step 6: Entering description");

        vacancyPage.enterDescription(vacancyDescription);

        System.out.println("Description entered");

        System.out.println("Step 7: Selecting hiring manager");

        vacancyPage.selectHiringManager(hiringManager);

        System.out.println("Hiring manager selected");

        System.out.println("Step 8: Entering number of positions");

        vacancyPage.enterNumberOfPositions(positions);

        System.out.println("Number of positions entered");

        System.out.println("Vacancy details entered successfully");

        System.out.println("Step 9: Clicking Save button");

        vacancyPage.clickSave();

        System.out.println("Save button clicked successfully");

        System.out.println(
                "Vacancy creation process completed successfully");
    }
}
