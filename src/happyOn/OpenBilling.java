package happyOn;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OpenBilling {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriverManager.firefoxdriver().setup();
		
		WebDriver driver = new FirefoxDriver();
		
		driver.get("http://192.168.191.39/happyonbranchwise/");

		driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("ollie@yopmail.com");
		driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys("Admin@123");
		driver.findElement(By.cssSelector("button[class='btn']")).click();
		
		driver.findElement(By.xpath("//a[.//div[text()='Event Bookings']]")).click();
		
		driver.findElement(By.cssSelector("a[class*='btn-primary']")).click();
		
		
	}

}
