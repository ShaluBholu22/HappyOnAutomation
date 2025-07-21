package happyOn;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TicketBooking {
	
	public void selectDOB(WebDriver driver, String date) {
		WebElement ipdob = driver.findElement(By.xpath("//input[@id='dob']"));
		ipdob.clear();
		ipdob.sendKeys(date);
	}

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
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.findElement(By.xpath("//input[@id='phone']")).sendKeys("7040102030");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#nameOfKid")).sendKeys("Rutul Dave");
		Thread.sleep(1000);

		WebElement kidsdrpdwn = driver.findElement(By.xpath("//select[@id='kidsQty']"));
		Select slctkds = new Select(kidsdrpdwn);
		slctkds.selectByValue("1");
		
		// Write code for Date
		Thread.sleep(1000);
		
		TicketBooking dph = new TicketBooking();
		dph.selectDOB(driver, "07152025");
		
/*		WebElement dob = driver.findElement(By.xpath("//input[@id='dob']"));
		dob.sendKeys("07152025");
*/		
		// Not use
//		WebElement dobInput = driver.findElement(By.id("dob"));
//		dobInput.sendKeys("07142020");
		
	/*	JavascriptExecutor dtjs = (JavascriptExecutor) driver;
		dtjs.executeScript("document.getElementById('dob').value = '07142020';");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		driver.findElement(By.xpath("//input[@placeholder='Kid Name ']")).sendKeys("Urvieka Dave");
		WebElement hrsdrpdwn = driver.findElement(By.cssSelector("#kid_hrs1"));
		Select slcthrs = new Select(hrsdrpdwn);
		slcthrs.selectByValue("Unlimited");

		driver.findElement(By.cssSelector("#extra-time")).click();		
		WebElement extrtim = driver.findElement(By.xpath("//select[@name='product_quantity[]']"));
		Select slctextrim = new Select(extrtim);
		slctextrim.selectByValue("1");
		
		driver.findElement(By.xpath("//a[@id='adult']")).click();
		WebElement adlttim = driver.findElement(By.cssSelector("body > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > form:nth-child(3) > div:nth-child(2) > table:nth-child(8) > tr:nth-child(2) > td:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > select:nth-child(2)"));
		Select slctadltim = new Select(adlttim);
		slctadltim.selectByValue("1");
		
		Thread.sleep(3000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		for (int i = 0; i < 5; i++) {
		    js.executeScript("window.scrollBy(0, 500)");
		    Thread.sleep(1000); // wait to simulate user scroll
		}

//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("window.scrollBy(0, 1500)");  // Scrolls down by 500 pixels
		
		driver.findElement(By.xpath("//button[contains(text(),'Payment')]")).click();
		*/
	}

}
