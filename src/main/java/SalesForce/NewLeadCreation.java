package SalesForce;

import Listener.CustomListener;
import Utils.DriverSetup;
import Utils.ExcelReader;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class NewLeadCreation {

    WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = DriverSetup.SetDriver(); // Get WebDriver from utility class
    }

    @Test
    public void LeadNavigation() {
        CustomListener.logStep(("🔗 Navigating to Leads"));
        WebElement chevronButton = driver.findElement(By.xpath("//button[@title='Show Navigation Menu']/lightning-primitive-icon"));
        chevronButton.click();
        WebElement LeadButton = driver.findElement(By.xpath("//span[@class='slds-media__body']/span[text()='Leads']"));
        LeadButton.click();
        CustomListener.logStep(("🔗 Navigated to Leads"));
    }

    @Test(dataProvider="leadData")
    public void NewLead(String Execute, String FirstName, String LastName, String Title, String Company, String Email, String Rating, String Source, String Country) throws InterruptedException {
        if ("Yes".equalsIgnoreCase(Execute)) {

            // Navigate to Leads
            CustomListener.logStep(("🔗 Navigating to Leads"));
            WebElement chevronButton = driver.findElement(By.xpath("//button[@title='Show Navigation Menu']/lightning-primitive-icon"));
            chevronButton.click();
            WebElement LeadButton = driver.findElement(By.xpath("//span[@class='slds-media__body']/span[text()='Leads']"));
            LeadButton.click();

            // Click New
            CustomListener.logStep(("🔗 Creating New Leads"));
            WebElement NewButton = driver.findElement(By.xpath("//button[text()='New']"));
            NewButton.click();

            // Filling the fields
            CustomListener.logStep(("🔗 Entering new lead information"));
            driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(FirstName);
            driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys(LastName);
            driver.findElement(By.xpath("//input[@name='Title']")).sendKeys(Title);
            driver.findElement(By.xpath("//input[@name='Company']")).sendKeys(Company);
            driver.findElement(By.xpath("//input[@name='Email']")).sendKeys(Email);

            CustomListener.logStep(("🔗 Entering more details..."));

            WebElement rating_dropdown = driver.findElement(By.xpath("//button[@aria-label='Rating']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", rating_dropdown);
            rating_dropdown.click();
            WebElement rating_option = driver.findElement(By.xpath("//lightning-base-combobox-item//span[text()='" + Rating + "']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", rating_option);
            rating_option.click();

            WebElement source_dropdown = driver.findElement(By.xpath("//button[@aria-label='Lead Source']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", source_dropdown);
            source_dropdown.click();
            WebElement source_option = driver.findElement(By.xpath("//lightning-base-combobox-item//span[text()='" + Source + "']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", source_option);
            source_option.click();

            driver.findElement(By.xpath("//input[@name='country']")).sendKeys(Country);

            // Submit
            CustomListener.logStep(("🔗 Submitting the lead"));
            driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();

            CustomListener.logStep(("✅ Lead submitted successfully"));
            Thread.sleep(2000); // optional: slight wait for UI response
        } else {
            CustomListener.logStep(("⚠️ Test Skipped"));
            throw new SkipException("Skipping test based on Execute column");
        }
    }


    @DataProvider(name = "leadData")
    public String [][] getData() throws IOException {
        String path = "C:\\Users\\josep\\IdeaProjects\\SeleniumJava\\SeleniumJava\\src\\test\\data\\Lead_Data.xlsx";

        ExcelReader exceldata = new ExcelReader(path);

        int totalrows = exceldata.getRowCount("Sheet1");
        int totalcols = exceldata.getCellCount("Sheet1", 1);

        String LeadData[][] = new String[totalrows][totalcols];

        for(int i = 1; i<=totalrows; i++)
        {
            for(int j= 0; j<totalcols; j++)
            {
                LeadData[i-1][j]= exceldata.getCellData("Sheet1", i, j);
            }
        }
        return LeadData;
    }
}
