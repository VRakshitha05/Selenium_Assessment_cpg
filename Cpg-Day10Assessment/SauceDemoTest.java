
package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.LoginPage;
import pages.ProductsPage;
import pages.CartPage;
import pages.CheckoutPage;
import pages.OverviewPage;
import utilities.ExcelReader;

public class SauceDemoTest extends BaseClass {

    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    OverviewPage overviewPage;

    @Test(priority = 1)
    public void loginTest() {

        // Read login details from Excel
        String username = ExcelReader.getData(
                "Sheet1", 1, 0);

        String password = ExcelReader.getData(
                "Sheet1", 1, 1);

        // Create Login Page object
        loginPage = new LoginPage(driver);

        // Login using valid credentials
        loginPage.login(username, password);

        // Create Products Page object
        productsPage = new ProductsPage(driver);

        // Verify Products page is displayed
        Assert.assertEquals(
                productsPage.getPageTitle(),
                "Products",
                "Products page is not displayed");

        System.out.println(
                "Login successful - Products page displayed");
    }

    @Test(priority = 2, dependsOnMethods = "loginTest")
    public void orderPalcementTest() {

        // Add Sauce Labs Backpack to cart
        productsPage.addBackpackToCart();

        // Verify cart contains 1 item
        Assert.assertEquals(
                productsPage.getCartItemCount(),
                "1",
                "Cart does not contain 1 item");

        System.out.println("Cart contains 1 item");

        // Open cart
        productsPage.clickCart();

        // Create Cart Page object
        cartPage = new CartPage(driver);

        // Verify Backpack is displayed
        Assert.assertTrue(
                cartPage.isBackpackDisplayed(),
                "Sauce Labs Backpack is not displayed");

        System.out.println(
                "Sauce Labs Backpack is displayed");

        // Click Checkout
        cartPage.clickCheckout();

        // Read checkout details from Excel
        String firstName = ExcelReader.getData(
                "Sheet1", 1, 2);

        String lastName = ExcelReader.getData(
                "Sheet1", 1, 3);

        String postalCode = ExcelReader.getData(
                "Sheet1", 1, 4);

        // Create Checkout Page object
        checkoutPage = new CheckoutPage(driver);

        // Enter details and continue
        checkoutPage.fillCheckoutDetails(
                firstName, lastName, postalCode);

        // Verify Checkout: Overview page
        Assert.assertEquals(
                checkoutPage.getOverviewTitle(),
                "Checkout: Overview",
                "Checkout Overview page not displayed");

        System.out.println(
                "Checkout: Overview page displayed");

        // Create Overview Page object
        overviewPage = new OverviewPage(driver);

        // Click Finish
        overviewPage.clickFinish();

        // Verify order confirmation
        Assert.assertEquals(
                overviewPage.getSuccessMessage(),
                "Thank you for your order!",
                "Order confirmation message not displayed");

        // Required console output
        System.out.println("Added to cart");
        System.out.println("Thank you for your order!");
    }
}