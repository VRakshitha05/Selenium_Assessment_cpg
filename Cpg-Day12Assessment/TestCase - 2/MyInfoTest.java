package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.LoginPage;
import pages.MyInfoPage;
import utilities.ExcelReader;

public class MyInfoTest extends BaseClass {

    private LoginPage loginPage;
    private MyInfoPage myInfoPage;

    @Test
    public void updateEmployeeDetailsTest() {

        System.out.println(
                "========== MyInfo Test Started =========="
        );

        // ==========================================
        // STEP 1: READ DATA FROM EXCEL
        // ==========================================

        System.out.println(
                "Reading employee data from Excel..."
        );

        String username =
                ExcelReader.getData(
                        "Sheet1",
                        2,
                        1
                );

        String password =
                ExcelReader.getData(
                        "Sheet1",
                        2,
                        2
                );

        String updatedFirstName =
                ExcelReader.getData(
                        "Sheet1",
                        2,
                        3
                );

        String updatedLastName =
                ExcelReader.getData(
                        "Sheet1",
                        2,
                        4
                );

        String updatedEmployeeId =
                ExcelReader.getData(
                        "Sheet1",
                        2,
                        5
                );

        System.out.println(
                "========================================"
        );

        System.out.println(
                "Username: " + username
        );

        System.out.println(
                "Password loaded successfully"
        );

        System.out.println(
                "First Name: " + updatedFirstName
        );

        System.out.println(
                "Last Name: " + updatedLastName
        );

        System.out.println(
                "Employee ID: " + updatedEmployeeId
        );

        System.out.println(
                "Excel data loaded successfully"
        );

        // ==========================================
        // STEP 2: LOGIN
        // ==========================================

        System.out.println(
                "Step 2: Login to OrangeHRM"
        );

        loginPage =
                new LoginPage(driver);

        loginPage.login(
                username,
                password
        );

        System.out.println(
                "Login successful"
        );

        // ==========================================
        // STEP 3: OPEN MY INFO
        // ==========================================

        System.out.println(
                "Step 3: Open My Info"
        );

        myInfoPage =
                new MyInfoPage(driver);

        myInfoPage.clickMyInfo();

        // ==========================================
        // STEP 4: CHANGE FIRST NAME
        // ==========================================

        System.out.println(
                "Step 4: Change first name"
        );

        myInfoPage.changeFirstName(
                updatedFirstName
        );

        // ==========================================
        // STEP 5: CHANGE LAST NAME
        // ==========================================

        System.out.println(
                "Step 5: Change last name"
        );

        myInfoPage.changeLastName(
                updatedLastName
        );

        // ==========================================
        // STEP 6: CHANGE EMPLOYEE ID
        // ==========================================

        System.out.println(
                "Step 6: Change employee ID"
        );

        myInfoPage.changeEmployeeId(
                updatedEmployeeId
        );

        // ==========================================
        // STEP 7: SAVE
        // ==========================================

        System.out.println(
                "Step 7: Click Save"
        );

        myInfoPage.clickSave(
                updatedFirstName,
                updatedLastName,
                updatedEmployeeId
        );

        // ==========================================
        // STEP 8: LOGOUT
        // ==========================================

        System.out.println(
                "Step 8: Logout"
        );

        loginPage.logout();

        // ==========================================
        // STEP 9: LOGIN AGAIN
        // ==========================================

        System.out.println(
                "Step 9: Login again"
        );

        loginPage.login(
                username,
                password
        );

        System.out.println(
                "Login successful again"
        );

        // ==========================================
        // STEP 10: OPEN MY INFO AGAIN
        // ==========================================

        System.out.println(
                "Step 10: Open My Info again"
        );

        myInfoPage.clickMyInfo();

        // ==========================================
        // STEP 11: READ UPDATED DETAILS
        // ==========================================

        System.out.println(
                "Step 11: Read updated details"
        );

        String actualFirstName =
                myInfoPage.getFirstName();

        String actualLastName =
                myInfoPage.getLastName();

        String actualEmployeeId =
                myInfoPage.getEmployeeId();

        // ==========================================
        // DISPLAY EXPECTED VS ACTUAL
        // ==========================================

        System.out.println(
                "========================================"
        );

        System.out.println(
                "Expected First Name: " +
                updatedFirstName
        );

        System.out.println(
                "Actual First Name: " +
                actualFirstName
        );

        System.out.println(
                "Expected Last Name: " +
                updatedLastName
        );

        System.out.println(
                "Actual Last Name: " +
                actualLastName
        );

        System.out.println(
                "Expected Employee ID: " +
                updatedEmployeeId
        );

        System.out.println(
                "Actual Employee ID: " +
                actualEmployeeId
        );

        System.out.println(
                "========================================"
        );

        // ==========================================
        // STEP 12: VERIFY FIRST NAME
        // ==========================================

        System.out.println(
                "Step 12: Verify first name"
        );

        Assert.assertEquals(
                actualFirstName,
                updatedFirstName,
                "First name was not updated"
        );

        System.out.println(
                "First name verified successfully"
        );

        // ==========================================
        // STEP 13: VERIFY LAST NAME
        // ==========================================

        System.out.println(
                "Step 13: Verify last name"
        );

        Assert.assertEquals(
                actualLastName,
                updatedLastName,
                "Last name was not updated"
        );

        System.out.println(
                "Last name verified successfully"
        );

        // ==========================================
        // STEP 14: VERIFY EMPLOYEE ID
        // ==========================================

        System.out.println(
                "Step 14: Verify employee ID"
        );

        Assert.assertEquals(
                actualEmployeeId,
                updatedEmployeeId,
                "Employee ID was not updated"
        );

        System.out.println(
                "Employee ID verified successfully"
        );

        System.out.println(
                "All employee details verified successfully"
        );

        // ==========================================
        // STEP 15: FINAL LOGOUT
        // ==========================================

        System.out.println(
                "Step 15: Final logout"
        );

        loginPage.logout();

        System.out.println(
                "Test Case 2 completed successfully"
        );

        System.out.println(
                "========== MyInfo Test Finished =========="
        );
    }
}
