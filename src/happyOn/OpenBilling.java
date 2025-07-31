package happyOn;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class OpenBilling {

    private static WebDriver driver;

    public static void main(String[] args) {
    	
        setupDriver();
        try {
            login("ollie@yopmail.com", "Admin@123");
            scrollSidebar();
            click(By.xpath("(//a)[18]")); // Open Billing
            click(By.cssSelector(".btn.btn-sm.btn-primary")); // Add Billing
            
            type(By.xpath("//input[@placeholder='Billing Name']"), "Bhavin Modi");
            type(By.xpath("//input[@id='phone']"), "7252025725");

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

            // click(By.xpath("//button[normalize-space()='Create Bill']"));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            waitFor(3000);
            // driver.quit();
        }
    }

    // ---------------- Utility Methods ---------------- //

    public static void setupDriver() {
        System.setProperty("webdriver.chrome.driver", "E:\\SeleniumWebDriver\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://192.168.191.39/happyonbranchwise/");
        waitFor(2000);
    }

    public static void login(String email, String password) {
        type(By.xpath("//input[@placeholder='Email']"), email);
        type(By.cssSelector("input[placeholder='Password']"), password);
        click(By.cssSelector("button[class='btn']"));
        waitFor(3000);
    }

    public static void scrollSidebar() {
        WebElement sidebar = driver.findElement(By.cssSelector(".sidebar-wrapper .simplebar-content-wrapper"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", sidebar);
        waitFor(2000);
    }

    public static void addItem(String nameSel, String priceSel, String qtySel, String taxSel,
                               String name, String price, String qty, String tax) {
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
}
