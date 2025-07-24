package happyOn;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BuyMembership {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriverManager.firefoxdriver().setup();

		WebDriver driver = new FirefoxDriver();
		
		driver.get("http://192.168.191.39/happyon");
		
		driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("superadmin@gmail.com");
		driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys("admin@123");
		driver.findElement(By.cssSelector("button[class='btn']")).click();
		
		driver.findElement(By.xpath("//a[contains(.,'Member Services')]")).click();		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(normalize-space(),'Memberships')]")).click();
		
		driver.findElement(By.cssSelector("button[class*='btn-primary']")).click();
		
		driver.findElement(By.cssSelector("input[id*='name']")).sendKeys("Viresh");
		driver.findElement(By.xpath("//input[@id='phone']")).sendKeys("7414561590");
		
		WebElement plan = driver.findElement(By.cssSelector("#membershipPlan"));
		Select slctpln = new Select(plan);
		slctpln.selectByVisibleText(" Shooting Star - 25 Entry");
		
		driver.findElement(By.cssSelector("#coupon_code")).sendKeys("JulyP25");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button[id*='apply-coupon']")).click();
		
		driver.findElement(By.xpath("//button[contains(text(),'Create')]")).click();
	}

}
