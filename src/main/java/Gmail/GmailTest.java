package Gmail;

import Listener.CustomListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import javax.swing.*;
import java.awt.*;
import java.time.Duration;

public class GmailTest {
    WebDriver driver;

    @BeforeTest
    public void setDriver(){
        System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        driver = new ChromeDriver(options);
        CustomListener.setDriver(driver);
    }
    @Test
    public void OpenGmail() {
        driver.get("https://www.gmail.com");
        driver.manage().window().maximize();
        CustomListener.logStep("🔗 Navigating to GMail");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
    }

    @Test(dependsOnMethods = "OpenGmail", priority = 1)
    public void Logon() {
        WebElement EmailSection = driver.findElement(By.xpath("//input[@type='email']"));
        CustomListener.logStep("🔗 Entering GMail Username");
        EmailSection.sendKeys("tywin2061");
        WebElement NextButton1 = driver.findElement(By.xpath("//span[text()='Next']"));
        NextButton1.click();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        CustomListener.logStep("🔗 Entering GMail Password");
        JPasswordField passwordField = new JPasswordField();
        JLabel label = new JLabel("Enter Password: ");
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.add(label);
        panel.add(passwordField);
        int option = JOptionPane.showConfirmDialog(null, panel, "Password Input", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            char[] password = passwordField.getPassword();
            String passwordStr = new String(password);
            WebElement Password = driver.findElement(By.xpath("//input[@aria-label='Enter your password']"));
            Password.sendKeys(passwordStr);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.textToBePresentInElementValue(Password, passwordStr));
            System.out.println("Password entered securely!");
            CustomListener.logStep("✅ Entered password");
            Password.click();
        } else {
            System.out.println("Operation canceled.");
        }
        WebElement NextButton2 = driver.findElement(By.xpath("//span[text()='Next']"));
        NextButton2.click();
        System.out.println("Success!");
        WebElement ComposeButton = driver.findElement(By.xpath("//div[text()='Compose']"));
        WebDriverWait Compose_wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Compose_wait.until(ExpectedConditions.elementToBeClickable(ComposeButton));
        System.out.println("GMail is logged in");
        CustomListener.logStep("✅ Successfully logged in");
    }
}




