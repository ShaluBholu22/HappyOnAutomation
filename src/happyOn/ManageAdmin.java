package happyOn;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ManageAdmin {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "E:\\SeleniumWebDriver\\chromedriver-win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
		
		 driver.get("http://192.168.191.39/happyonbranchwise/");
         driver.manage().window().maximize();
         Thread.sleep(2000);

         driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("ollie@yopmail.com");
         driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys("Admin@123");
         driver.findElement(By.cssSelector("button[class='btn']")).click();
         Thread.sleep(3000);
         
      // Scroll the sidebar to bottom (first scroll)
         WebElement sidebar = driver.findElement(By.cssSelector(".sidebar-wrapper .simplebar-content-wrapper"));
         JavascriptExecutor js = (JavascriptExecutor) driver;
         js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", sidebar);
         Thread.sleep(2000);

         // Click the 'System' link
         driver.findElement(By.xpath("//a[contains(.,'System')]")).click();
         Thread.sleep(2000); // optional wait for section to load

         // Scroll the sidebar again after 'System' is clicked
         js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", sidebar);
         Thread.sleep(2000);
         
         driver.findElement(By.xpath("//a[normalize-space()='Manage Admin']")).click();
         
         driver.findElement(By.xpath("//button[normalize-space()='Add New']")).click();
         
         driver.findElement(By.cssSelector("#name")).sendKeys("#name");
         
	}

}
