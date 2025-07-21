package happyOn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TicketForMember {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriverManager.firefoxdriver().setup();
		
		WebDriver driver = new FirefoxDriver();
		
		driver.get("http://192.168.191.39/happyonbranchwise/");
		
		driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("ollie@yopmail.com");
		driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys("Admin@123");
		driver.findElement(By.cssSelector("button[class='btn']")).click();
		
		driver.findElement(By.xpath("//a[contains(@href, '/tickets')]")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		driver.findElement(By.cssSelector(".nav-link.right")).click();
	}
}
