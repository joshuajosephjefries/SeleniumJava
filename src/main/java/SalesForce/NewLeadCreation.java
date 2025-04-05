package SalesForce;

import Listener.CustomListener;
import Utils.DriverSetup;
import Utils.ExcelReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import Utils.DropdownUtility;
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
        WebElement LeadButton = driver.findElement(By.xpath("//span[@class='slds-media__body']/span[text()='Leads']"));
        LeadButton.click();
        CustomListener.logStep(("🔗 Navigated to Leads"));

        CustomListener.logStep(("🔗 Creating New Leads"));
        WebElement NewButton = driver.findElement(By.xpath("//button[text()='New']"));
        NewButton.click();
    }

    @Test(dataProvider="leadData")
    public void NewLead(String FirstName, String LastName, String Title, String Company, String Email, String Country) throws InterruptedException {
        String Expected_Title = "New Lead | Salesforce";
        String Actual_Title = driver.getTitle();
        if (Expected_Title.equals(Actual_Title)){
            WebElement Firstname = driver.findElement(By.xpath("//input[@name='firstName']"));
            Firstname.sendKeys(FirstName);
            WebElement Lastname = driver.findElement(By.xpath("//input[@name='lastName']"));
            Lastname.sendKeys(LastName);
            WebElement title = driver.findElement(By.xpath("//input[@name='Title']"));
            title.sendKeys(Title);
            WebElement company = driver.findElement(By.xpath("//input[@name='Company']"));
            company.sendKeys(Company);
            WebElement email = driver.findElement(By.xpath("//input[@name='Email']"));
            email.sendKeys(Email);
            WebElement country = driver.findElement(By.xpath("//input[@name='country']"));
            country.sendKeys(Country);
            driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
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
