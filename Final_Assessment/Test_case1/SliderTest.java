package assessment;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class SliderTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://demoapps.qspiders.com/ui/slider?sublist=0");
		
		// Locate slider
        WebElement slider = driver.findElement(By.xpath("//input[@type='range']"));
        

        Actions action = new Actions(driver);

        action.clickAndHold(slider)
              .moveByOffset(100, 0)
              .release()
              .perform();
        WebElement product = driver.findElement(
			    By.xpath("//h3[text()='Mens Cotton Jacket']")
			);

			if (product.isDisplayed()) {
			    System.out.println("Men Cotton Jacket is displayed ");
			} else {
			    System.out.println("Men Cotton Jacket is not displayed - Test Failed");
			}
  
	}

}