package happyOn;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BuyMembership {
	
	WebDriver driver;
	
	public void setup() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();		
		driver.get("http://192.168.191.39/happyonbranchwise/");
	}
	
	public void login(String email, String password) {
		driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys(email);
		driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys(password);
		driver.findElement(By.cssSelector("button[class='btn']")).click();
	}
	
	public void navigateToMambership() throws InterruptedException {
		driver.findElement(By.xpath("//a[contains(.,'Member Services')]")).click();		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(normalize-space(),'Memberships')]")).click();
	}
	
	public void openMembership() {
		driver.findElement(By.cssSelector("button[class*='btn-primary']")).click();
	}
	
	public void fillMembership(String kidnm, String phnnmbr, String cuopn) throws InterruptedException {
		driver.findElement(By.cssSelector("input[id*='name']")).sendKeys(kidnm);
		driver.findElement(By.xpath("//input[@id='phone']")).sendKeys(phnnmbr);		
		WebElement plan = driver.findElement(By.cssSelector("#membershipPlan"));
		Select slctpln = new Select(plan);
		slctpln.selectByVisibleText(" Shooting Star - 25 Entry");		
		driver.findElement(By.cssSelector("#coupon_code")).sendKeys(cuopn);
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button[id*='apply-coupon']")).click();
	}
	
	public void clickButtonByText(String buttonText) {
	    String xpath = String.format("//button[contains(normalize-space(),'%s')]", buttonText);
	    WebElement button = driver.findElement(By.xpath(xpath));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
	}
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		BuyMembership obj = new BuyMembership();
		obj.setup();
		obj.login("ollie@yopmail.com", "Admin@123");
		obj.navigateToMambership();
		obj.openMembership();
		obj.fillMembership("Viresh", "7414561590", "JulyP25");
		obj.clickButtonByText("Create");
	}

}
