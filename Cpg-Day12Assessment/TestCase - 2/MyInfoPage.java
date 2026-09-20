package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyInfoPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    // =========================
    // LOCATORS
    // =========================

    private By myInfoMenu =
            By.xpath("//a[contains(@href,'viewMyDetails')]");

    private By firstNameField =
            By.cssSelector("input[name='firstName']");

    private By lastNameField =
            By.cssSelector("input[name='lastName']");

    /*
     * Employee ID is located using its label because the
     * input does not reliably expose name='employeeId'.
     */
    private By employeeIdField =
            By.xpath(
                    "//label[contains(normalize-space(),'Employee Id')]" +
                    "/ancestor::div[contains(@class,'oxd-input-group')]" +
                    "//input"
            );

    /*
     * First Save button on the Personal Details section.
     */
    private By personalDetailsSaveButton =
            By.xpath("(//button[@type='submit'])[1]");

    // =========================
    // CONSTRUCTOR
    // =========================

    public MyInfoPage(WebDriver driver) {

        this.driver = driver;

        this.wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(30)
                );

        this.js =
                (JavascriptExecutor) driver;
    }

    // =========================
    // OPEN MY INFO
    // =========================

    public void clickMyInfo() {

        System.out.println("Clicking My Info...");

        WebElement myInfo =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                myInfoMenu
                        )
                );

        myInfo.click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        firstNameField
                )
        );

        System.out.println("My Info page loaded");

        /*
         * Wait until OrangeHRM has populated the
         * First Name field.
         */
        wait.until(driver -> {

            try {

                WebElement first =
                        getVisibleElement(firstNameField);

                String value =
                        getInputValue(first);

                return value != null
                        && !value.trim().isEmpty();

            } catch (Exception e) {

                return false;
            }
        });

        System.out.println(
                "Personal Details fields loaded successfully"
        );
    }

    // =========================
    // CHANGE FIRST NAME
    // =========================

    public void changeFirstName(String firstName) {

        System.out.println(
                "Changing first name to: " + firstName
        );

        WebElement field =
                getVisibleElement(firstNameField);

        String before =
                getInputValue(field);

        System.out.println(
                "First name before change: [" +
                before +
                "]"
        );

        scrollIntoView(field);

        field.click();

        field.sendKeys(
                Keys.CONTROL,
                "a"
        );

        field.sendKeys(
                Keys.BACK_SPACE
        );

        field.sendKeys(firstName);

        String after =
                getInputValue(field);

        System.out.println(
                "First name after change: [" +
                after +
                "]"
        );

        if (!firstName.equals(after)) {

            setValueUsingJavaScript(
                    field,
                    firstName
            );

            System.out.println(
                    "First name after JavaScript update: [" +
                    getInputValue(field) +
                    "]"
            );
        }
    }

    // =========================
    // CHANGE LAST NAME
    // =========================

    public void changeLastName(String lastName) {

        System.out.println(
                "Changing last name to: " + lastName
        );

        WebElement field =
                getVisibleElement(lastNameField);

        String before =
                getInputValue(field);

        System.out.println(
                "Last name before change: [" +
                before +
                "]"
        );

        scrollIntoView(field);

        field.click();

        field.sendKeys(
                Keys.CONTROL,
                "a"
        );

        field.sendKeys(
                Keys.BACK_SPACE
        );

        field.sendKeys(lastName);

        String after =
                getInputValue(field);

        System.out.println(
                "Last name after change: [" +
                after +
                "]"
        );

        if (!lastName.equals(after)) {

            setValueUsingJavaScript(
                    field,
                    lastName
            );

            System.out.println(
                    "Last name after JavaScript update: [" +
                    getInputValue(field) +
                    "]"
            );
        }
    }

    // =========================
    // CHANGE EMPLOYEE ID
    // =========================

    public void changeEmployeeId(String employeeId) {

        System.out.println(
                "Changing Employee ID to: " +
                employeeId
        );

        WebElement field =
                getVisibleElement(employeeIdField);

        String before =
                getInputValue(field);

        System.out.println(
                "Employee ID before change: [" +
                before +
                "]"
        );

        scrollIntoView(field);

        field.click();

        field.sendKeys(
                Keys.CONTROL,
                "a"
        );

        field.sendKeys(
                Keys.BACK_SPACE
        );

        field.sendKeys(employeeId);

        String after =
                getInputValue(field);

        System.out.println(
                "Employee ID after change: [" +
                after +
                "]"
        );

        if (!employeeId.equals(after)) {

            setValueUsingJavaScript(
                    field,
                    employeeId
            );

            System.out.println(
                    "Employee ID after JavaScript update: [" +
                    getInputValue(field) +
                    "]"
            );
        }
    }

    // =========================
    // SAVE
    // =========================

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
                "Save button text: " +
                saveButton.getText()
        );

        scrollIntoView(saveButton);

        saveButton.click();

        System.out.println(
                "Save button clicked"
        );

        /*
         * Wait until all three fields contain
         * the values entered from Excel.
         */
        wait.until(driver -> {

            try {

                String first =
                        getInputValue(
                                getVisibleElement(firstNameField)
                        );

                String last =
                        getInputValue(
                                getVisibleElement(lastNameField)
                        );

                String empId =
                        getInputValue(
                                getVisibleElement(employeeIdField)
                        );

                System.out.println(
                        "Checking saved values -> " +
                        "First: [" + first + "] " +
                        "Last: [" + last + "] " +
                        "Employee ID: [" + empId + "]"
                );

                return expectedFirstName.equals(first)
                        && expectedLastName.equals(last)
                        && expectedEmployeeId.equals(empId);

            } catch (Exception e) {

                return false;
            }
        });

        System.out.println(
                "Employee details saved successfully"
        );
    }

    // =========================
    // GET FIRST NAME
    // =========================

    public String getFirstName() {

        WebElement field =
                getVisibleElement(firstNameField);

        String value =
                getInputValue(field);

        System.out.println(
                "First Name read from page: [" +
                value +
                "]"
        );

        return value == null
                ? ""
                : value.trim();
    }

    // =========================
    // GET LAST NAME
    // =========================

    public String getLastName() {

        WebElement field =
                getVisibleElement(lastNameField);

        String value =
                getInputValue(field);

        System.out.println(
                "Last Name read from page: [" +
                value +
                "]"
        );

        return value == null
                ? ""
                : value.trim();
    }

    // =========================
    // GET EMPLOYEE ID
    // =========================

    public String getEmployeeId() {

        WebElement field =
                getVisibleElement(employeeIdField);

        String value =
                getInputValue(field);

        System.out.println(
                "Employee ID read from page: [" +
                value +
                "]"
        );

        return value == null
                ? ""
                : value.trim();
    }

    // =========================
    // FIND VISIBLE ELEMENT
    // =========================

    private WebElement getVisibleElement(By locator) {

        wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                        locator
                )
        );

        List<WebElement> elements =
                driver.findElements(locator);

        for (WebElement element : elements) {

            try {

                if (element.isDisplayed()
                        && element.isEnabled()) {

                    return element;
                }

            } catch (Exception e) {
                // Continue searching
            }
        }

        throw new RuntimeException(
                "No visible and enabled element found for locator: "
                + locator
        );
    }

    // =========================
    // READ INPUT VALUE
    // =========================

    private String getInputValue(WebElement element) {

        String value =
                element.getAttribute("value");

        if (value == null || value.isEmpty()) {

            value =
                    element.getDomProperty("value");
        }

        if (value == null || value.isEmpty()) {

            Object result =
                    js.executeScript(
                            "return arguments[0].value;",
                            element
                    );

            if (result != null) {
                value = result.toString();
            }
        }

        return value == null
                ? ""
                : value;
    }

    // =========================
    // SET VALUE USING JAVASCRIPT
    // =========================

    private void setValueUsingJavaScript(
            WebElement element,
            String value) {

        js.executeScript(
                "arguments[0].focus();" +
                "arguments[0].value = '';" +
                "arguments[0].dispatchEvent(" +
                "new Event('input', { bubbles: true })" +
                ");" +
                "arguments[0].value = arguments[1];" +
                "arguments[0].dispatchEvent(" +
                "new Event('input', { bubbles: true })" +
                ");" +
                "arguments[0].dispatchEvent(" +
                "new Event('change', { bubbles: true })" +
                ");",
                element,
                value
        );
    }

    // =========================
    // SCROLL INTO VIEW
    // =========================

    private void scrollIntoView(WebElement element) {

        js.executeScript(
                "arguments[0].scrollIntoView({" +
                "block: 'center'," +
                "inline: 'nearest'" +
                "});",
                element
        );
    }
}
