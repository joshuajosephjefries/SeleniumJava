package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DropdownUtility {
    WebDriver driver;
    public void selectDropdownValue(String label, String value) throws InterruptedException {
        // Find dropdown button by aria-label
        WebElement dropdown = driver.findElement(By.xpath("//button[@aria-label='" + label + "']"));

        // Scroll and click dropdown
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", dropdown);
        Thread.sleep(500);
        js.executeScript("arguments[0].click();", dropdown); // open the dropdown

        // Wait for the dropdown options to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement option = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//span[@class='slds-truncate' and text()='" + value + "']")));

        option.click(); // this will update the data-value behind the scenes
    }


}
