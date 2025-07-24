package happyOn;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class OpenBilling {

    public static void main(String[] args) throws InterruptedException {
        // Setup ChromeDriver
        System.setProperty("webdriver.chrome.driver", "E:\\SeleniumWebDriver\\chromedriver-win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        try {
            // 1. Login
            driver.get("http://192.168.191.39/happyonbranchwise/");
            driver.manage().window().maximize();
            Thread.sleep(2000);

            driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("ollie@yopmail.com");
            driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys("Admin@123");
            driver.findElement(By.cssSelector("button[class='btn']")).click();
            Thread.sleep(3000);

            // 2. Scroll sidebar using JS
            WebElement sidebar = driver.findElement(By.cssSelector(".sidebar-wrapper .simplebar-content-wrapper"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", sidebar);
            Thread.sleep(2000);

            // 3. Click 'Open Billing'
            driver.findElement(By.xpath("(//a)[18]")).click();
            Thread.sleep(2000);

            // 4. Open Add Billing form
            driver.findElement(By.cssSelector(".btn.btn-sm.btn-primary")).click();
            Thread.sleep(1000);
            
            // 5. Fill in billing name and phone
            driver.findElement(By.xpath("//input[@placeholder='Billing Name']")).sendKeys("Bhavin Modi");
            driver.findElement(By.xpath("//input[@id='phone']")).sendKeys("7252025725");

            // 6. Add exactly 3 valid items
            addItem(driver, "#item_name", "#item_price", "#item_qty", "#item_tax", "Foods", "456.89", "30", "18%");
            clickPlus(driver);  // ➕ Click to create 2nd row

            addItem(driver, "#item_name2", "#item_price2", "#item_qty2", "#item_tax2", "Beverages", "122.15", "35", "12%");
            clickPlus(driver);  // ➕ Click to create 3rd row

            addItem(driver, "#item_name3", "#item_price3", "#item_qty3", "#item_tax3", "Gifts", "250.00", "40", "5%");

            // ✅ Do NOT click plus again — to avoid adding a blank 4th item

            // 7. Scroll down and apply coupon
            driver.findElement(By.xpath("//a[normalize-space()='Calculate']")).click();

            Actions scroll = new Actions(driver);
            scroll.sendKeys(Keys.PAGE_DOWN).build().perform();
            Thread.sleep(2000);

            driver.findElement(By.cssSelector("#couponCode")).sendKeys("JulyP25");
            driver.findElement(By.cssSelector("#couponCodebutton")).click();

            // 8. Create bill
            driver.findElement(By.xpath("//button[normalize-space()='Create Bill']")).click();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Thread.sleep(3000);
            // driver.quit(); // Optional: close browser
        }
    }

    // Method to fill a single item row
    public static void addItem(WebDriver driver, String nameSel, String priceSel, String qtySel,
                               String taxSel, String name, String price, String qty, String tax) throws InterruptedException {
        driver.findElement(By.cssSelector(nameSel)).sendKeys(name);
        driver.findElement(By.cssSelector(priceSel)).sendKeys(price);

        WebElement qtyField = driver.findElement(By.cssSelector(qtySel));
        qtyField.clear();
        Thread.sleep(500);
        qtyField.sendKeys(qty);

        WebElement taxDropdown = driver.findElement(By.cssSelector(taxSel));
        Select selectTax = new Select(taxDropdown);
        selectTax.selectByVisibleText(tax);

        Thread.sleep(500);
    }

    // Method to click ➕ button to add next row
    public static void clickPlus(WebDriver driver) throws InterruptedException {
        driver.findElement(By.cssSelector(".fa.fa-plus.fs-4.btn.btn-primary")).click();
        Thread.sleep(1000);
    }
}
