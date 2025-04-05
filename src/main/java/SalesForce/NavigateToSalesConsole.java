package SalesForce;

import Listener.CustomListener;
import Utils.DriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class NavigateToSalesConsole {

    WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = DriverSetup.SetDriver(); // Get WebDriver from utility class
    }

    @Test
    public void SalesConsole(){
        CustomListener.logStep("🔗 Navigating to Sales Console");
        WebElement AppLauncher = driver.findElement(By.xpath("//button[@title='App Launcher']"));
        AppLauncher.click();
        WebElement ViewAll = driver.findElement(By.xpath("//button[@aria-label='View All Applications']"));
        ViewAll.click();
        WebElement Sales_Console = driver.findElement(By.xpath("//p[normalize-space()='Sales Console']"));
        Sales_Console.click();
        CustomListener.logStep("🔗 Navigated to Sales Console");
        boolean actualTitle = driver.findElement(By.xpath("//h1/span[@title='Sales Console']")).isDisplayed();
        AssertJUnit.assertTrue(actualTitle);
        WebElement dropdown = driver.findElement(By.xpath("//button[@title='Show Navigation Menu']"));
        dropdown.click();
    }
}
