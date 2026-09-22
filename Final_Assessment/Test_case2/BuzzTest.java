package tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import listeners.TestListener;
import pages.BuzzPage;
import pages.DashboardPage;
import pages.LoginPage;
import utils.ConfigReader;

@Listeners(TestListener.class)
public class BuzzTest {

    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        System.out.println(
                "========== BROWSER SETUP STARTED =========="
        );

        try {

            String browser =
                    ConfigReader.getProperty("browser");

            System.out.println(
                    "Browser from config: " + browser
            );

            if (browser == null ||
                    browser.trim().isEmpty()) {

                throw new RuntimeException(
                        "browser is missing in config.properties"
                );
            }

            if (browser.equalsIgnoreCase("chrome")) {

                ChromeOptions options =
                        new ChromeOptions();

                options.addArguments(
                        "--start-maximized"
                );

                options.addArguments(
                        "--disable-notifications"
                );

                System.out.println(
                        "Creating ChromeDriver..."
                );

                driver = new ChromeDriver(options);

                System.out.println(
                        "ChromeDriver created successfully"
                );

            } else {

                throw new RuntimeException(
                        "Unsupported browser: " + browser
                );
            }

            driver.manage().timeouts().implicitlyWait(
                    Duration.ofSeconds(5)
            );

            driver.manage().timeouts().pageLoadTimeout(
                    Duration.ofSeconds(30)
            );

            String url =
                    ConfigReader.getProperty("url");

            System.out.println(
                    "Opening URL: " + url
            );

            driver.get(url);

            System.out.println(
                    "URL opened successfully"
            );

            System.out.println(
                    "========== BROWSER SETUP COMPLETED =========="
            );

        } catch (Exception e) {

            System.out.println(
                    "========== BROWSER SETUP FAILED =========="
            );

            e.printStackTrace();

            throw e;
        }
    }


    @Test
    public void verifyBuzzPostAndLogout() {

        System.out.println(
                "========== ORANGEHRM BUZZ ASSESSMENT =========="
        );


        
        System.out.println(
                "STEP 1: Login to OrangeHRM"
        );

        LoginPage loginPage =
                new LoginPage(driver);

        String username =
                ConfigReader.getProperty("username");

        String password =
                ConfigReader.getProperty("password");

        loginPage.login(
                username,
                password
        );

        Assert.assertTrue(
                loginPage.isDashboardDisplayed(),
                "Dashboard was not displayed after login"
        );

        System.out.println(
                "Login successful"
        );



        System.out.println(
                "STEP 2: Click Buzz"
        );

        DashboardPage dashboardPage =
                new DashboardPage(driver);

        Assert.assertTrue(
                dashboardPage.isDashboardDisplayed(),
                "Dashboard is not displayed"
        );

        dashboardPage.clickBuzz();

        System.out.println(
                "Buzz clicked successfully"
        );


     

        System.out.println(
                "STEP 3: Verify Buzz page"
        );

        BuzzPage buzzPage =
                new BuzzPage(driver);

        Assert.assertTrue(
                buzzPage.isBuzzPageDisplayed(),
                "Buzz page was not displayed"
        );

        System.out.println(
                "Buzz page displayed successfully"
        );


       

        System.out.println(
                "STEP 4: Enter post"
        );

        String postText =
                ConfigReader.getProperty("postText");

        buzzPage.enterPost(postText);

        System.out.println(
                "Post entered: " + postText
        );


       

        System.out.println(
                "STEP 5: Click Post"
        );

        buzzPage.clickPost();

        System.out.println(
                "Post button clicked"
        );


       

        System.out.println(
                "STEP 6: Verify Recent Posts"
        );

        Assert.assertTrue(
                buzzPage.isRecentPostsDisplayed(),
                "Recent Posts section was not displayed"
        );

        System.out.println(
                "Recent Posts section displayed"
        );


        

        System.out.println(
                "STEP 7: Verify posted text"
        );

        Assert.assertTrue(
                buzzPage.isPostDisplayed(postText),
                "Posted text was not found in Recent Posts"
        );

        System.out.println(
                "Post verified successfully"
        );


        

        System.out.println(
                "STEP 8: Logout"
        );

        buzzPage.logout();

        System.out.println(
                "Logout successful"
        );


        System.out.println(
                "========== ASSESSMENT COMPLETED =========="
        );
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        System.out.println(
                "========== TEAR DOWN STARTED =========="
        );

        if (driver != null) {

            driver.quit();

            System.out.println(
                    "Browser closed successfully"
            );

        } else {

            System.out.println(
                    "Driver is null"
            );
        }

        System.out.println(
                "========== TEAR DOWN COMPLETED =========="
        );
    }
}