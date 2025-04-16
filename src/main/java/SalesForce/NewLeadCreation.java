package SalesForce;

import Listener.CustomListener;
import Utils.BaseTest;
import Utils.ExcelReader;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Utils.CommonFunctions;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class NewLeadCreation extends BaseTest {

    @Test
    public void LeadNavigation() {
        CustomListener.logStep(("🔗 Navigating to Leads"));
        WebElement chevronButton = driver.findElement(By.xpath("//button[@title='Show Navigation Menu']/lightning-primitive-icon"));
        chevronButton.click();
        WebElement LeadButton = driver.findElement(By.xpath("//span[text()='Leads']"));
        LeadButton.click();
        CustomListener.logStep(("🔗 Navigated to Leads"));
        CustomListener.logStep(("🔗 Closing Open Tabs"));
        ToastMessagePopUp();
        closeAllTabs();
        CustomListener.logStep(("🔗 Closed Open Tabs"));
    }

    @Test(dataProvider = "leadData")
    public void NewLead(String Execute, String Status, String Salutation, String FirstName, String LastName, String Title, String Company, String Email, String Phone, String NoOfEmployees, String Rating, String Source, String Address, String Street, String City, String ZipPostalCode, String StateProvince, String Country) throws InterruptedException {

        if ("Yes".equalsIgnoreCase(Execute)) {

            // Navigate to Leads
            CustomListener.logStep(("🔗 Navigating to Leads"));
            WebElement chevronButton = driver.findElement(By.xpath("//button[@title='Show Navigation Menu']/lightning-primitive-icon"));
            chevronButton.click();
            WebElement LeadButton = driver.findElement(By.xpath("//span[@class='slds-media__body']/span[text()='Leads']"));
            LeadButton.click();

            // Click New to create new Lead
            CustomListener.logStep(("🔗 Creating New Lead"));
            WebElement NewButton = driver.findElement(By.xpath("//button[text()='New']"));
            NewButton.click();
            // Filling the fields
            CustomListener.logStep(("🔗 Entering new lead information"));

            // Field: Status
            WebElement status_dropdown = driver.findElement(By.xpath("//button[@aria-label='Lead Status']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", status_dropdown);
            status_dropdown.click();
            WebElement status_option = driver.findElement(By.xpath("//lightning-base-combobox-item//span[text()='" + Status + "']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", status_option);
            status_option.click();

            // Field: Salutation
            WebElement salutation_dropdown = driver.findElement(By.xpath("//button[@aria-label='Salutation']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", salutation_dropdown);
            salutation_dropdown.click();
            WebElement salutation_option = driver.findElement(By.xpath("//lightning-base-combobox-item//span[text()='" + Salutation + "']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", salutation_option);
            salutation_option.click();

            driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(FirstName);
            driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys(LastName);
            driver.findElement(By.xpath("//input[@name='Title']")).sendKeys(Title);
            driver.findElement(By.xpath("//input[@name='Company']")).sendKeys(Company);
            driver.findElement(By.xpath("//input[@name='Email']")).sendKeys(Email);
            driver.findElement(By.xpath("//input[@name='Phone']")).sendKeys(Phone);
            driver.findElement(By.xpath("//input[@name='NumberOfEmployees']")).sendKeys(NoOfEmployees);

            CustomListener.logStep(("🔗 Entering more details..."));

            WebElement source_dropdown = driver.findElement(By.xpath("//button[@aria-label='Lead Source']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", source_dropdown);
            source_dropdown.click();
            WebElement source_option = driver.findElement(By.xpath("//lightning-base-combobox-item//span[text()='" + Source + "']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", source_option);
            source_option.click();

            WebElement rating_dropdown = driver.findElement(By.xpath("//button[@aria-label='Rating']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", rating_dropdown);
            rating_dropdown.click();
            WebElement rating_option = driver.findElement(By.xpath("//lightning-base-combobox-item//span[text()='" + Rating + "']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", rating_option);
            rating_option.click();

            driver.findElement(By.xpath("//input[@placeholder='Search Address']")).sendKeys(Address);
            driver.findElement(By.xpath("//textarea[@name='street']")).sendKeys(Street);
            driver.findElement(By.xpath("//input[@name='city']")).sendKeys(City);
            driver.findElement(By.xpath("//input[@name='postalCode']")).sendKeys(ZipPostalCode);
            driver.findElement(By.xpath("//input[@name='province']")).sendKeys(StateProvince);
            driver.findElement(By.xpath("//input[@name='country']")).sendKeys(Country);
            CustomListener.logStep(("🔗 Entered all the details..."));

            // If duplicate, refrain from creating the lead
            Duplicate_Leads();

            // Submit
            CustomListener.logStep(("🔗 Submitting the lead"));
            driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();

            CustomListener.logStep(("✅ Lead submitted successfully"));
        } else {
            CustomListener.logStep(("⚠️ Test Skipped"));
            throw new SkipException("Skipping test based on Execute column");
        }
    }

    @DataProvider(name = "leadData")
    public String[][] getData() throws IOException {
        String path = "C:\\Users\\josep\\IdeaProjects\\SeleniumJava\\SeleniumJava\\src\\test\\data\\Lead_Data.xlsx";

        ExcelReader exceldata = new ExcelReader(path);

        int totalrows = exceldata.getRowCount("Sheet1");
        int totalcols = exceldata.getCellCount("Sheet1", 1);

        String LeadData[][] = new String[totalrows][totalcols];

        for (int i = 1; i <= totalrows; i++) {
            for (int j = 0; j < totalcols; j++) {
                String cellValue = exceldata.getCellData("Sheet1", i, j);
                LeadData[i - 1][j] = (cellValue == null || cellValue.trim().isEmpty()) ? null : cellValue;
            }
        }
        return LeadData;
    }

    //Close all tabs, if open
    public void closeAllTabs() {
        CommonFunctions closetabs = new CommonFunctions(driver);
        closetabs.closeAllTabs();
    }

    //Toast Message for Duplicate Lead
    public void ToastMessagePopUp() {
        CommonFunctions toasthandler = new CommonFunctions(driver);
        toasthandler.ToastMessagePopUp();
    }

    public void Duplicate_Leads() {
        try {
            List<WebElement> duplicateWarnings = driver.findElements(By.xpath("//button[@id='window']/lightning-icon[@icon-name='utility:warning' or @title='Error']"));

            if (!duplicateWarnings.isEmpty()) {
                System.out.println("⚠️ Duplicate Lead detected. Lead creation not allowed.");
                CustomListener.logStep("⚠️ Duplicate Lead Detected. Lead creation not allowed.");
                WebElement cancel_button = driver.findElement(By.xpath("//lightning-button/button[@name='CancelEdit']"));
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
                cancel_button.click();
                Assert.fail("❌ Duplicate lead detected. Lead creation is not allowed.");
            } else {
                System.out.println("✅ No duplicate leads. Proceeding to save.");
            }
        } catch (Exception e) {
            System.out.println("❌ Error while checking duplicate lead: " + e.getMessage());
        }
    }
}