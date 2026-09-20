package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyInfoPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By myInfoMenu =
            By.xpath("//a[contains(@href,'viewMyDetails')]");

    private By firstNameField =
            By.name("firstName");

    private By lastNameField =
            By.name("lastName");

    private By employeeIdField =
            By.xpath(
                    "//label[contains(normalize-space(),'Employee Id')]"
                    + "/ancestor::div[contains(@class,'oxd-input-group')]"
                    + "//input"
            );

    private By personalDetailsSaveButton =
            By.xpath("(//button[@type='submit'])[1]");

    public MyInfoPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // ==================================================
    // OPEN MY INFO
    // ==================================================

    public void clickMyInfo() {

        System.out.println("Clicking My Info...");

        WebElement myInfo =
                wait.until(
                        ExpectedConditions.elementToBeClickable(myInfoMenu)
                );

        myInfo.click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(firstNameField)
        );

        System.out.println("My Info page loaded");
    }

    // ==================================================
    // CHANGE FIRST NAME
    // ==================================================

    public void changeFirstName(String firstName) {

        System.out.println("Changing first name to: " + firstName);

        WebElement field =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                firstNameField
                        )
                );

        System.out.println(
                "First name before change: [" +
                field.getDomProperty("value") + "]"
        );

        field.click();
        field.sendKeys(Keys.CONTROL, "a");
        field.sendKeys(Keys.BACK_SPACE);
        field.sendKeys(firstName);

        System.out.println(
                "First name after change: [" +
                field.getDomProperty("value") + "]"
        );
    }

    // ==================================================
    // CHANGE LAST NAME
    // ==================================================

    public void changeLastName(String lastName) {

        System.out.println("Changing last name to: " + lastName);

        WebElement field =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                lastNameField
                        )
                );

        System.out.println(
                "Last name before change: [" +
                field.getDomProperty("value") + "]"
        );

        field.click();
        field.sendKeys(Keys.CONTROL, "a");
        field.sendKeys(Keys.BACK_SPACE);
        field.sendKeys(lastName);

        System.out.println(
                "Last name after change: [" +
                field.getDomProperty("value") + "]"
        );
    }

    // ==================================================
    // CHANGE EMPLOYEE ID
    // ==================================================

    public void changeEmployeeId(String employeeId) {

        System.out.println("Changing Employee ID to: " + employeeId);

        WebElement field =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                employeeIdField
                        )
                );

        System.out.println(
                "Employee ID before change: [" +
                field.getDomProperty("value") + "]"
        );

        field.click();
        field.sendKeys(Keys.CONTROL, "a");
        field.sendKeys(Keys.BACK_SPACE);
        field.sendKeys(employeeId);

        System.out.println(
                "Employee ID after change: [" +
                field.getDomProperty("value") + "]"
        );
    }

    // ==================================================
    // SAVE AND WAIT FOR VALUES
    // ==================================================

    public void clickSave(
            String expectedFirstName,
            String expectedLastName,
            String expectedEmployeeId) {

        System.out.println("Clicking Save...");

        WebElement saveButton =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                personalDetailsSaveButton
                        )
                );

        System.out.println("Save button found");
        System.out.println(
                "Save button text: " + saveButton.getText()
        );

        saveButton.click();

        /*
         * Wait until OrangeHRM has actually processed the update.
         * Instead of assuming that clicking Save means the operation
         * is finished, wait until all three fields contain the
         * expected values.
         */
        wait.until(driver -> {

            String first =
                    driver.findElement(firstNameField)
                          .getDomProperty("value");

            String last =
                    driver.findElement(lastNameField)
                          .getDomProperty("value");

            String empId =
                    driver.findElement(employeeIdField)
                          .getDomProperty("value");

            return expectedFirstName.equals(first)
                    && expectedLastName.equals(last)
                    && expectedEmployeeId.equals(empId);
        });

        System.out.println(
                "Employee details saved successfully"
        );

        System.out.println(
                "Saved First Name: [" + getFirstName() + "]"
        );

        System.out.println(
                "Saved Last Name: [" + getLastName() + "]"
        );

        System.out.println(
                "Saved Employee ID: [" + getEmployeeId() + "]"
        );
    }

    // ==================================================
    // GET FIRST NAME
    // ==================================================

    public String getFirstName() {

        waitForFieldValue(firstNameField);

        WebElement field =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                firstNameField
                        )
                );

        String value = field.getDomProperty("value");

        System.out.println(
                "First Name read from page: [" + value + "]"
        );

        return value == null ? "" : value.trim();
    }

    // ==================================================
    // GET LAST NAME
    // ==================================================

    public String getLastName() {

        waitForFieldValue(lastNameField);

        WebElement field =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                lastNameField
                        )
                );

        String value = field.getDomProperty("value");

        System.out.println(
                "Last Name read from page: [" + value + "]"
        );

        return value == null ? "" : value.trim();
    }

    // ==================================================
    // GET EMPLOYEE ID
    // ==================================================

    public String getEmployeeId() {

        waitForFieldValue(employeeIdField);

        WebElement field =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                employeeIdField
                        )
                );

        String value = field.getDomProperty("value");

        System.out.println(
                "Employee ID read from page: [" + value + "]"
        );

        return value == null ? "" : value.trim();
    }

    // ==================================================
    // WAIT UNTIL FIELD IS POPULATED
    // ==================================================

    private void waitForFieldValue(By locator) {

        wait.until(driver -> {

            WebElement field =
                    driver.findElement(locator);

            String value =
                    field.getDomProperty("value");

            return value != null && !value.trim().isEmpty();
        });
    }
}