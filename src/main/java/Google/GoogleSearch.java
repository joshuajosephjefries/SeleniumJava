package Google;

import Listener.CustomListener;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.time.Duration;

public class GoogleSearch {

    WebDriver driver;

    @BeforeTest
    public void setDriver(){
        System.setProperty("webdriver.chrome.driver","C:\\Drivers\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        CustomListener.setDriver(driver);
    }
    @Test(priority = 1)
    public void OpenWebsite(){
        CustomListener.logStep("🔗 Navigating to Google");
        driver.get("https://www.google.com");
        CustomListener.logStep("🧐 Checking Page Title");
        String title = driver.getTitle();
        System.out.println("Title: " + title);
        assert title.contains("Google");
        CustomListener.logStep("✅ Title Verified: " + title);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        System.out.println("Page loaded Successfully");
    }

    @Test(priority = 2)
    public void PopUpFrame(){
        WebElement frame1 = driver.findElement(By.xpath("//iframe[@name='callout']"));
        driver.switchTo().frame(frame1);
        System.out.println("Frame found. Proceeding with the next steps...");
        driver.findElement(By.xpath("//button[@aria-label='Stay signed out']")).click();
        driver.switchTo().defaultContent();
    }

    @Test(priority = 3)
    public void WebSearch(){
        WebElement Searchbar = driver.findElement(By.xpath("//textarea[@title='Search']"));
        Searchbar.sendKeys("Joseph Jefries Github");
        Searchbar.sendKeys(Keys.ENTER);
        System.out.println("Searching for the information..");
    }

    @Test(priority = 4)
    public void reCAPTCHA() {
        if (isRecaptchaPresent()) {
            System.out.println("⚠️ reCAPTCHA detected! Exiting test.");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        else{
            WebElement Hyperlink = driver.findElement(By.xpath("//h3[normalize-space()='Joseph Jefries joshuajosephjefries']"));
            Actions actions = new Actions(driver);
            actions.contextClick(Hyperlink).perform();
        }
    }

    private boolean isRecaptchaPresent() {
        try {
            WebElement recaptcha = driver.findElement(By.cssSelector("iframe[title='reCAPTCHA']"));
            return recaptcha.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;  // reCAPTCHA not found, continue execution
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

