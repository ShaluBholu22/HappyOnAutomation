package happyOn;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;

public class EventBooking {

	 private static WebDriver driver;

	public void setup() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "E:\\SeleniumWebDriver\\chromedriver-win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://192.168.191.39/happyonbranchwise/");
		driver.manage().window().maximize();
		Thread.sleep(2000);
	}

	public void login(String email, String password) {
		driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys(email);
		driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys(password);
		driver.findElement(By.cssSelector("button[class='btn']")).click();
	}

	public void navigateToEvenetBooking() {
		driver.findElement(By.xpath("//a/div[contains(.,'Event Bookings')]")).click();
	}

	public void openEventBooking() {
		driver.findElement(By.xpath("//a[normalize-space()='Add New +']")).click();
	}

	public void fillEventBooking(String pnnmbr, String billnm, String cntctprsn, String kidnm) {
		driver.findElement(By.id("phone")).sendKeys(pnnmbr);
		driver.findElement(By.xpath("//input[@placeholder='Billing Name']")).sendKeys(billnm);
		driver.findElement(By.id("contact")).sendKeys(cntctprsn);
		driver.findElement(By.xpath("//input[@placeholder='Kid Name']")).sendKeys(kidnm);

		// Add multiple items
		addItem("#item_name", "#item_price", "#item_qty", "#item_tax", "Foods", "456.89", "30", "18%");
		clickPlus();
		addItem("#item_name2", "#item_price2", "#item_qty2", "#item_tax2", "Beverages", "122.15", "35", "12%");
		clickPlus();
		addItem("#item_name3", "#item_price3", "#item_qty3", "#item_tax3", "Gifts", "250.00", "40", "5%");
		
		 // Apply coupon
        click(By.xpath("//a[normalize-space()='Calculate']"));
        scrollPageDown();
        type(By.cssSelector("#couponCode"), "JulyP25");
        click(By.cssSelector("#couponCodebutton"));
	}

	public static void addItem(String nameSel, String priceSel, String qtySel, String taxSel, String name, String price,
			String qty, String tax) {
		type(By.cssSelector(nameSel), name);
		type(By.cssSelector(priceSel), price);
		clearAndType(By.cssSelector(qtySel), qty);

		Select selectTax = new Select(driver.findElement(By.cssSelector(taxSel)));
		selectTax.selectByVisibleText(tax);
		waitFor(500);
	}

	public static void clickPlus() {
		click(By.cssSelector(".fa.fa-plus.fs-4.btn.btn-primary"));
		waitFor(1000);
	}

	public static void click(By locator) {
		driver.findElement(locator).click();
	}

	public static void type(By locator, String text) {
		driver.findElement(locator).sendKeys(text);
	}

	public static void clearAndType(By locator, String text) {
		WebElement element = driver.findElement(locator);
		element.clear();
		waitFor(500);
		element.sendKeys(text);
	}

	public static void scrollPageDown() {
		new Actions(driver).sendKeys(Keys.PAGE_DOWN).perform();
		waitFor(2000);
	}

	public static void waitFor(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// ✅ Generic Date Selector (Booking Date / Kid DOB)
	public void selectDate(By locator, String date) {
		String formattedDate = formatDate(date);

		WebElement dateInput = driver.findElement(locator);
		dateInput.clear();
		dateInput.sendKeys(formattedDate);

		if (!dateInput.getAttribute("value").equals(formattedDate)) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].value = arguments[1];", dateInput, formattedDate);
		}
	}

	// ✅ Helper: Format Date to yyyy-MM-dd
	private String formatDate(String date) {
		if (date.matches("\\d{8}")) { // e.g., 07312025
			String month = date.substring(0, 2);
			String day = date.substring(2, 4);
			String year = date.substring(4, 8);
			return year + "-" + month + "-" + day;
		} else if (date.matches("\\d{1,2}[/\\-]\\d{1,2}[/\\-]\\d{4}")) { // 07/31/2025 or 7-31-2025
			String[] parts = date.split("[/\\-]");
			String month = String.format("%02d", Integer.parseInt(parts[0]));
			String day = String.format("%02d", Integer.parseInt(parts[1]));
			String year = parts[2];
			return year + "-" + month + "-" + day;
		}
		return date; // fallback (already formatted)
	}

	// ✅ Booking From Time
	public void selectBookingFromTime(String time) {
		String formattedTime = convertTo24Hour(time);

		WebElement timeInputFrom = driver.findElement(By.id("time_from"));
		timeInputFrom.clear();
		timeInputFrom.sendKeys(formattedTime);

		if (!timeInputFrom.getAttribute("value").equals(formattedTime)) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].value = arguments[1];", timeInputFrom, formattedTime);
		}
	}

	// ✅ Booking To Time
	public void selectBookingToTime(String time) {
		String formattedTime = convertTo24Hour(time);

		WebElement timeInputTo = driver.findElement(By.id("time_to"));
		timeInputTo.clear();
		timeInputTo.sendKeys(formattedTime);

		if (!timeInputTo.getAttribute("value").equals(formattedTime)) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].value = arguments[1];", timeInputTo, formattedTime);
		}
	}

	// ✅ Helper: Convert hh:mm AM/PM → 24-hour format
	private String convertTo24Hour(String time12h) {
		try {
			SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
			SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
			return displayFormat.format(parseFormat.parse(time12h));
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public void clickButtonByText(String buttonText) {
		String xpath = String.format("//button[contains(normalize-space(),'%s')]", buttonText);
		WebElement button = driver.findElement(By.xpath(xpath));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
	}

	public static void main(String[] args) throws InterruptedException {
		EventBooking obj = new EventBooking();
		obj.setup();
		obj.login("ollie@yopmail.com", "Admin@123");
		obj.navigateToEvenetBooking();
		obj.openEventBooking();

		// ✅ Booking Date
		obj.selectDate(By.id("bdate"), "07312025");

		// ✅ Kid DOB
		obj.selectDate(By.name("kid_dob"), "12/25/2020");

		// ✅ Booking Time
		obj.selectBookingFromTime("03:45 PM");
		obj.selectBookingToTime("06:15 PM");

		// ✅ Fill other details
		obj.fillEventBooking("7312025731", "Bhavin Modi", "Bhavin Modi", "Ziva Modi");
		
		obj.clickButtonByText("Create");
	}
}
