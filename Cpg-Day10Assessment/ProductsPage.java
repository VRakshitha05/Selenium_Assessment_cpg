
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage {

    WebDriver driver;

    By productsTitle = By.className("title");

    By backpack = By.id("add-to-cart-sauce-labs-backpack");

    By cartIcon = By.className("shopping_cart_link");

    By cartBadge = By.className("shopping_cart_badge");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return driver.findElement(productsTitle).getText();
    }

    public void addBackpackToCart() {
        driver.findElement(backpack).click();
    }

    public String getCartItemCount() {
        return driver.findElement(cartBadge).getText();
    }

    public void clickCart() {
        driver.findElement(cartIcon).click();
    }
}