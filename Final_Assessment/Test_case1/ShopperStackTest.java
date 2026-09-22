package assessment;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class ShopperStackTest {
public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://www.shoppersstack.com/");
		
		driver.findElement(By.xpath("//span[text() = 'Apple AirPods (2nd Generation)']")).click();
		driver.findElement(By.name("Check Delivery")).sendKeys("583101");

		WebElement ref = driver.findElement(By.id("Check"));
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
		wait.pollingEvery(Duration.ofMillis(200));
		wait.ignoring(TimeoutException.class);
		wait.withMessage("Element is not clickable within duration");
		wait.withTimeout(Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(ref));
		ref.click();
		
		WebElement actualText = driver.findElement(By.id("Check Delivery-helper-text"));
		if (actualText.isDisplayed()) {
			System.out.println("Deliverable");
		} 
		else 
		{
		    System.out.println("Not Deliverable");
		}
		
}
		

}