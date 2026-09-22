package assessment;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ToggleTest {
	 public static void main(String[] args) throws InterruptedException {
		 WebDriver driver = new ChromeDriver();
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		 //Navigate to DemoApps Qspyder
		 driver.get("https://demoapps.qspiders.com/ui/toggle?sublist=0");
		 //click on disabled link
		 driver.findElement(By.xpath("//a[normalize-space()='Disabled']")).click();
		 
		 
		 JavascriptExecutor js = (JavascriptExecutor) driver;

		 List<WebElement> toggles = driver.findElements(By.xpath("//input[@type='checkbox' and @disabled]"));

		 for (WebElement toggle : toggles) {
		     js.executeScript("arguments[0].removeAttribute('disabled');", toggle);
		     js.executeScript("arguments[0].click();", toggle);
		 }
		 
		 
		 driver.findElement(By.xpath("//button[@id='togglers']")).click();
		 WebElement verify = driver.findElement(By.xpath("//p[@class='text-center pt-3 text-lg']"));
		 if(verify.isDisplayed()) {
			 System.out.println("Order is Placed");
		 }else {
			 System.out.println("Order is not Placed");
		 }
		Thread.sleep(3000);
		driver.quit();
	}
}