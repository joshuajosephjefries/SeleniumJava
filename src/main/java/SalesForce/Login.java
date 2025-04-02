package SalesForce;

import Listener.CustomListener;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.*;
import java.time.Duration;

public class Login {
    WebDriver driver;
    @BeforeTest
    public void DriverSetup(){
        System.setProperty("webdriver.chrome.driver","C:\\Drivers\\chromedriver-win64\\chromedriver.exe");
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void LoginSF(){
        CustomListener.logStep("🔗 Navigating to Salesforce");
        driver.get("https://www.salesforce.com");

        //This Element is inside 2 nested shadow DOM.
        SearchContext shadow1 = driver.findElement(By.cssSelector("hgf-c360nav[locale='in']")).getShadowRoot();
        SearchContext shadow2 = shadow1.findElement(By.cssSelector("hgf-c360login[aria-haspopup='true']")).getShadowRoot();
        shadow2.findElement(By.cssSelector(" hgf-popover:nth-child(1) > hgf-button:nth-child(1) > span:nth-child(2)")).click();

        //This Element is inside 2 nested shadow DOM.
        SearchContext shadow3 = driver.findElement(By.cssSelector("hgf-c360nav[locale='in']")).getShadowRoot();
        SearchContext shadow4 = shadow3.findElement(By.cssSelector("hgf-c360login[aria-haspopup='true']")).getShadowRoot();
        shadow4.findElement(By.cssSelector("a[href='https://login.salesforce.com/?locale=in']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        CustomListener.logStep("🔗 Entering Username");
        WebElement UserName = driver.findElement(By.xpath("//div[@id='username_container']/input[@id='username']"));
        UserName.click();
        UserName.sendKeys(Credentials.getUsername());
        System.out.println("Username entered securely!");
        CustomListener.logStep("✅ Entered Username");

        CustomListener.logStep("🔗 Entering SF Password");
        WebElement Password = driver.findElement(By.xpath("//input[@id='password']"));
        Password.sendKeys(Credentials.getPassword());
        System.out.println("Password entered securely!");
        CustomListener.logStep("✅ Entered SF password");
        Password.click();

        WebElement LoginButton = driver.findElement(By.xpath("//input[@id='Login']"));
        LoginButton.click();
        CustomListener.logStep("✅ Logged in Successfully");
    }
}
