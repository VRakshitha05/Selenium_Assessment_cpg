package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.WaitUtils;

public class BuzzPage {

    private WebDriver driver;
    private WaitUtils wait;

    /*
     * Buzz page locators
     */

    private By buzzHeading =
            By.xpath(
                "//h6[normalize-space()='Buzz']"
            );

    /*
     * The Buzz input can be represented by a textarea/input
     * depending on the OrangeHRM build.
     */
    private By whatsOnYourMind =
            By.xpath(
                "//textarea[contains(@placeholder,\"What's on your mind\")]"
                + " | "
                + "//input[contains(@placeholder,\"What's on your mind\")]"
            );

    /*
     * Post button
     */
    private By postButton =
            By.xpath(
                "//button[normalize-space()='Post']"
            );

    /*
     * Recent Posts heading
     */
    private By recentPosts =
            By.xpath(
                "//*[contains(normalize-space(),'Recent Posts')]"
            );

    /*
     * Logout
     */
    private By profileButton =
            By.xpath(
                "//span[contains(@class,'oxd-userdropdown-tab')]"
                + " | "
                + "//p[contains(@class,'oxd-userdropdown-name')]"
            );

    private By logoutButton =
            By.xpath(
                "//a[normalize-space()='Logout']"
                + " | "
                + "//button[normalize-space()='Logout']"
            );

    public BuzzPage(WebDriver driver) {

        this.driver = driver;

        this.wait =
                new WaitUtils(driver);
    }

    public boolean isBuzzPageDisplayed() {

        return wait.waitForElementPresent(
                buzzHeading
        );
    }

    public void enterPost(String text) {

        WebElement postField =
                wait.waitForElementVisible(
                        whatsOnYourMind
                );

        postField.clear();

        postField.sendKeys(text);
    }

    public void clickPost() {

        wait.waitForElementClickable(
                postButton
        ).click();
    }

    public boolean isRecentPostsDisplayed() {

        return wait.waitForElementPresent(
                recentPosts
        );
    }

    public boolean isPostDisplayed(
            String postText) {

        By postLocator =
                By.xpath(
                    "//*[contains(normalize-space(),\""
                    + postText
                    + "\")]"
                );

        try {

            return wait.waitForElementPresent(
                    postLocator
            );

        } catch (Exception e) {

            return false;
        }
    }

    public void logout() {

        wait.waitForElementClickable(
                profileButton
        ).click();

        wait.waitForElementClickable(
                logoutButton
        ).click();
    }
}