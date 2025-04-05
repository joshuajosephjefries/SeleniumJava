package SalesForce;

import Listener.CustomListener;
import Utils.ConfigReader;
import Utils.DriverSetup;
import com.google.common.annotations.VisibleForTesting;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.time.Duration;

public class SFLogin {

    WebDriver driver;

    @Test
    public void setup() {
        driver = DriverSetup.SetDriver(); // Get WebDriver from utility class
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
        UserName.sendKeys(ConfigReader.getProperty("username"));
        System.out.println("Username entered securely!");
        CustomListener.logStep("✅ Entered Username");

        CustomListener.logStep("🔗 Entering SF Password");
        WebElement Password = driver.findElement(By.xpath("//input[@id='password']"));
        Password.sendKeys(ConfigReader.getProperty("password"));
        System.out.println("Password entered securely!");
        CustomListener.logStep("✅ Entered SF password");
        Password.click();

        WebElement LoginButton = driver.findElement(By.xpath("//input[@id='Login']"));
        LoginButton.click();
        CustomListener.logStep("✅ Logged in Successfully");
    }
}
