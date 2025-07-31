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

public class TicketBooking {

	WebDriver driver;

	// ✅ 1. Setup Browser
	public void setupBrowser() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://192.168.191.39/happyonbranchwise/");
	}

	// ✅ 2. Login Method
	public void login(String email, String password) throws InterruptedException {
		driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys(email);
		driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys(password);
		driver.findElement(By.cssSelector("button[class='btn']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[contains(@href, '/tickets')]")).click();
		Thread.sleep(1000);
	}

	// ✅ 3. Fill Ticket Details
	public void fillTicketDetails(String phone, String mbrName, String kidsCount, String kidnm)
			throws InterruptedException {
		driver.findElement(By.xpath("//input[@id='phone']")).sendKeys(phone);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#nameOfKid")).sendKeys(mbrName);
		Thread.sleep(1000);

		WebElement kidsDropdown = driver.findElement(By.xpath("//select[@id='kidsQty']"));
		Select selectKids = new Select(kidsDropdown);
		selectKids.selectByValue(kidsCount);

		driver.findElement(By.xpath("//input[@placeholder='Kid Name ']")).sendKeys(kidnm);

		WebElement hrsdrpdwn = driver.findElement(By.cssSelector("#kid_hrs1"));
		Select slcthrs = new Select(hrsdrpdwn);
		slcthrs.selectByValue("Unlimited");

		driver.findElement(By.cssSelector("#extra-time")).click();
		WebElement extrtim = driver.findElement(By.xpath("//select[@name='product_quantity[]']"));
		Select slctextrim = new Select(extrtim);
		slctextrim.selectByValue("1");

		driver.findElement(By.xpath("//a[@id='adult']")).click();
		WebElement adlttim = driver.findElement(By.cssSelector(
				"body > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > form:nth-child(3) > div:nth-child(2) > table:nth-child(8) > tr:nth-child(2) > td:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > select:nth-child(2)"));
		Select slctadltim = new Select(adlttim);
		slctadltim.selectByValue("1");

		Thread.sleep(3000);

		Actions actscrldwn = new Actions(driver);
		actscrldwn.sendKeys(Keys.PAGE_DOWN).build().perform();
	}

	public void clickButtonByText(String buttonText) {
		String xpath = String.format("//button[contains(normalize-space(),'%s')]", buttonText);
		WebElement button = driver.findElement(By.xpath(xpath));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
	}

	// ✅ 4. Generic DOB Method with JS Fallback
	public void selectDOB(String dob) {
		String formattedDOB = formatDOB(dob);

		WebElement dobInput = driver.findElement(By.id("dob"));
		dobInput.clear();
		dobInput.sendKeys(formattedDOB);

		if (!dobInput.getAttribute("value").equals(formattedDOB)) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].value = arguments[1];", dobInput, formattedDOB);
		}
	}

	// ✅ Helper: Format DOB to yyyy-MM-dd
	private String formatDOB(String dob) {
		if (dob.matches("\\d{8}")) { // e.g., 07312025
			String month = dob.substring(0, 2);
			String day = dob.substring(2, 4);
			String year = dob.substring(4, 8);
			return year + "-" + month + "-" + day;
		} else if (dob.matches("\\d{1,2}[/\\-]\\d{1,2}[/\\-]\\d{4}")) { // 07/31/2025 or 7-31-2025
			String[] parts = dob.split("[/\\-]");
			String month = String.format("%02d", Integer.parseInt(parts[0]));
			String day = String.format("%02d", Integer.parseInt(parts[1]));
			String year = parts[2];
			return year + "-" + month + "-" + day;
		}
		return dob; // fallback (already formatted)
	}

	// ✅ 5. Close Browser
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	// ✅ MAIN Execution
	public static void main(String[] args) throws InterruptedException {
		TicketBooking booking = new TicketBooking();

		booking.setupBrowser();
		booking.login("ollie@yopmail.com", "Admin@123");
		booking.fillTicketDetails("7040102030", "Rutul Dave", "1", "Urvieka Dave");
		booking.selectDOB("07312025"); // Flexible input
		booking.clickButtonByText("Payment");
		// booking.tearDown();
	}
}
