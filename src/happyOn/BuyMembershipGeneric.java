package happyOn;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BuyMembershipGeneric {

	WebDriver driver;
	WebDriverWait wait;

	// Launch browser
public void setup() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		//wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("http://192.168.191.39/happyonbranchwise");
	}

	// Login method
	public void login(String email, String password) {
		driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys(email);
		driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys(password);
		driver.findElement(By.cssSelector("button[class='btn']")).click();
	}

	// Navigation method
	public void navigateToMembership() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(.,'Member Services')]"))).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Memberships']"))).click();
	}

	// Generic textbox fill method
	public void fillTextbox(By locator, String value) {
		WebElement input = driver.findElement(locator);
		input.clear();
		input.sendKeys(value);
	}

	// Select dropdown by visible text
	public void selectDropdown(By locator, String visibleText) {
		Select dropdown = new Select(driver.findElement(locator));
		dropdown.selectByVisibleText(visibleText);
	}

	// Apply coupon code
	public void applyCoupon(String couponCode) throws InterruptedException {
		fillTextbox(By.cssSelector("#coupon_code"), couponCode);
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button[id*='apply-coupon']")).click();
	}

	// Perform membership purchase
	public void buyMembership(String name, String phone, String planText, String couponCode)
			throws InterruptedException {
		driver.findElement(By.cssSelector("button[class*='btn-primary']")).click();

		fillTextbox(By.cssSelector("input[id*='name']"), name);
		fillTextbox(By.xpath("//input[@id='phone']"), phone);
		selectDropdown(By.cssSelector("#membershipPlan"), planText);
		applyCoupon(couponCode);

		driver.findElement(By.xpath("//button[contains(text(),'Create')]")).click();
	}

	// Close browser
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	// Main method
	public static void main(String[] args) throws InterruptedException {
		BuyMembershipGeneric obj = new BuyMembershipGeneric();
		obj.setup();
		obj.login("ollie@yopmail.com", "Admin@123");
		obj.navigateToMembership();
		obj.buyMembership("Viresh", "7414561590", "Shooting Star - 25 Entry", "JulyP25");
		obj.tearDown();
	}
}
