package happyOn;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class ManageAdmin {
	
	WebDriver driver;
	
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
		
	public void navigateToManageAdmin() throws InterruptedException {
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
	}

	public void openManageAdmin() {
		driver.findElement(By.xpath("//a[normalize-space()='Manage Admin']")).click();
	}
	
	public void fillManageUser(String name, String email, String password, String cnfmpassword) throws InterruptedException {
		driver.findElement(By.xpath("//button[normalize-space()='Add New']")).click();
		Thread.sleep(2000); // Wait for modal to appear
		
		// Check if modal dialog is displayed
		WebElement modalDialog = driver.findElement(By.className("modal-dialog"));
		if (modalDialog.isDisplayed()) {
			// Fill modal fields
			driver.findElement(By.id("name")).sendKeys(name);
			driver.findElement(By.id("email")).sendKeys(email);
			driver.findElement(By.id("password")).sendKeys(password);
			driver.findElement(By.id("confirm_password")).sendKeys(cnfmpassword);
			
			// Select Role
			WebElement dropdownElement = driver.findElement(By.id("roles"));
			Select select = new Select(dropdownElement);
			select.selectByIndex(0); // Select first available role
		}
	}
	
	public void clickButtonByText(String buttonText) {
		String xpath = String.format("//button[contains(normalize-space(),'%s')]", buttonText);
		WebElement button = driver.findElement(By.xpath(xpath));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		ManageAdmin obj = new ManageAdmin();
		obj.setup();
		obj.login("ollie@yopmail.com", "Admin@123");
		obj.navigateToManageAdmin();
		obj.openManageAdmin();
		obj.fillManageUser("John Doe", "john@example.com", "pass@123", "pass@123");
		obj.clickButtonByText("Create");
	}

}
