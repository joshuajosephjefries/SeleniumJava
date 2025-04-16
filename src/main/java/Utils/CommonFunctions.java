package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CommonFunctions {

    private WebDriver driver;

    // Constructor
    public CommonFunctions(WebDriver driver) {
        this.driver = driver;
    }

    public void closeAllTabs() {
        List<WebElement> closeButtons = null;
        try {
            closeButtons = driver.findElements(By.xpath("//ul[@role='presentation']/li[@data-aura-class='navexConsoleTabItem']/div[2]/button"));

            for (WebElement closeBtn : closeButtons) {
                try {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", closeBtn);
                    closeBtn.click();
                    new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.stalenessOf(closeBtn));
                } catch (Exception e) {
                    System.out.println("❌ Could not close one tab: " + e.getMessage());
                }
            }

            System.out.println("✅ All open tabs closed.");
        } catch (Exception e) {
            System.out.println("❌ Error while closing tabs: " + e.getMessage());
        }
        System.out.println("✅ Closed " + closeButtons.size() + " open tabs.");
    }

    //Toast Message for Duplicate Lead
    public void ToastMessagePopUp() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            List<WebElement> toastMessages = driver.findElements(By.xpath("//div[@data-aura-class='forceToastMessage']"));

            if (!toastMessages.isEmpty() && toastMessages.get(0).isDisplayed()) {
                WebElement close_button = toastMessages.get(0).findElement(By.xpath("//div[@class='slds-notify__close']/button"));
                close_button.click();
                System.out.println("✅ Closed toast message");
            }
        } catch (Exception e) {
            System.out.println("⚠️ No toast message found or couldn't close: " + e.getMessage());
        }
    }
}
