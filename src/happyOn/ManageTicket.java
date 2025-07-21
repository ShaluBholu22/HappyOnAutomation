package happyOn;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.reactwebdriver.ReactWebDriver;

public class ManageTicket {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriverManager.firefoxdriver().setup();

		WebDriver driver = new FirefoxDriver();

		driver.manage().window().maximize();

		driver.get("http://192.168.191.39/happyonbranchwise/");

		driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("ollie@yopmail.com");
		driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys("Admin@123");
		driver.findElement(By.cssSelector("button[class='btn']")).click();

		driver.findElement(By.xpath("//a [contains(.,'Manage Ticket')]")).click();
		driver.findElement(By.cssSelector("input[class*='form-control-sm']")).sendKeys("Suniel");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.findElement(By.xpath("(//a[@class='btn btn-sm btn-primary'])[2]")).click();

		WebElement extrtimdd = driver.findElement(By.cssSelector("select[name*='extra_time_select[]']"));
		Select slctextrtim = new Select(extrtimdd);
		slctextrtim.selectByVisibleText("30 Min ");

		Thread.sleep(1500);

		driver.switchTo().alert().accept();
		
		Actions actscrldwn = new Actions(driver);
		actscrldwn.sendKeys(Keys.PAGE_DOWN).build().perform();
		Thread.sleep(3000);
		
		//ReactWebDriver rctwbdrvr = new ReactWebDriver((JavascriptExecutor) driver, "#root");

/*		JavascriptExecutor js = (JavascriptExecutor) driver;
		for (int i = 0; i < 5; i++) {
			js.executeScript("window.scrollBy(0, 500)");
			Thread.sleep(1000); // wait to simulate user scroll
		}
*/
		driver.findElement(By.xpath("//button[contains(text(),'Update')]")).click();
	}

}
