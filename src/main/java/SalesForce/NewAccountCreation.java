package SalesForce;

import Listener.CustomListener;
import Utils.BaseTest;
import Utils.CommonFunctions;
import Utils.ExcelReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class NewAccountCreation extends BaseTest {

    @Test
    public void NewAccountNavigation() {
        CustomListener.logStep(("🔗 Navigating to Accounts"));
        WebElement chevronButton = driver.findElement(By.xpath("//button[@title='Show Navigation Menu']/lightning-primitive-icon"));
        chevronButton.click();
        WebElement AccountsButton = driver.findElement(By.xpath("//span[text()='Accounts']"));
        AccountsButton.click();
        CustomListener.logStep(("🔗 Navigated to Accounts"));
        CustomListener.logStep(("🔗 Closing Open Tabs"));
        ToastMessagePopUp();
        closeAllTabs();
        CustomListener.logStep(("🔗 Closed Open Tabs"));
    }

    @Test(dataProvider = "account_data")
    public void NewAccount(String Execute, String Name, String ParentAccount, String Type, String Industry, String Phone, String Website, String Description, String Employees, String BillingAddress, String BillingStreet, String BillingCity, String BillingPostalCode, String BillingState, String BillingCountry, String ShippingAddress, String ShippingStreet, String ShippingCity, String ShippingPostalCode, String ShippingState, String ShippingCountry)  throws InterruptedException {

        if ("Yes".equalsIgnoreCase(Execute)) {

            // Navigating to Accounts
            WebElement title = driver.findElement(By.xpath("//h1[text()='Accounts']"));
            String actualTitle = title.getText();
            Assert.assertEquals(actualTitle, "Accounts", "Title does not match!");


            // Click New to create new Account
            CustomListener.logStep(("🔗 Creating a new Accounts"));
            WebElement newButton = driver.findElement(By.xpath("//div[@title='New']"));
            newButton.click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[text()='New Account']")));


            // Filling the fields
            CustomListener.logStep(("🔗 Entering new account information"));
            CustomListener.logStep(("🔗 Account Information section"));

            // Field: Account Name
            WebElement accountName = driver.findElement(By.xpath("//input[@name='Name']"));
            accountName.sendKeys(Name);

            // Field: Parent Account
            WebElement parentAccount = driver.findElement(By.xpath("//input[@aria-haspopup='listbox']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", parentAccount);
            parentAccount.click();
            WebElement parentAccount_option = driver.findElement(By.xpath("//lightning-base-combobox-item//span[@title='" + ParentAccount + "']"));
            parentAccount_option.click();

            // Field: Type
            WebElement type_dropdown = driver.findElement(By.xpath("//button[@aria-label='Type']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", type_dropdown);
            type_dropdown.click();
            WebElement type_option = driver.findElement(By.xpath("//lightning-base-combobox-item//span[text()='" + Type + "']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", type_option);
            type_option.click();

            // Field: Industry
            WebElement industry_dropdown = driver.findElement(By.xpath("//button[@aria-label='Industry']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", industry_dropdown);
            industry_dropdown.click();
            WebElement industry_option = driver.findElement(By.xpath("//lightning-base-combobox-item//span[text()='" + Industry + "']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", industry_option);
            industry_option.click();

            // Field: Account Phone
            WebElement accountPhone = driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Account.Phone']//input[@name='Phone']"));
            accountPhone.click();
            accountPhone.sendKeys(Phone);

            CustomListener.logStep(("🔗 Additional Information section"));

            // Field: Website
            WebElement website = driver.findElement(By.xpath("//div/input[@name='Website']"));
            website.click();
            website.sendKeys(Website);

            // Field: Description
            WebElement description = driver.findElement(By.xpath("//lightning-textarea/div/textarea"));
            description.click();
            description.sendKeys(Description);

            // Field: Employees
            WebElement employees = driver.findElement(By.xpath("//div/input[@name='NumberOfEmployees']"));
            employees.click();
            employees.sendKeys(Employees);

            CustomListener.logStep(("🔗 Address Information section"));

            System.out.println("Entering Billing details");

            // Field: Billing Address

            WebElement billingAddress = driver.findElement(By.xpath("//flexipage-field[@data-field-id='RecordBillingAddressField']//input[@placeholder='Search Address']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", billingAddress);
            billingAddress.click();
            billingAddress.sendKeys(BillingAddress);

            // Clicking TAB to get rid of the lookup
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.TAB).build().perform();

            // Field: Billing Street
            WebElement billingStreet = driver.findElement(By.xpath("//flexipage-field[@data-field-id='RecordBillingAddressField']//textarea[@name='street']"));
            billingStreet.click();
            billingStreet.sendKeys(BillingStreet);

            // Field: Billing City
            WebElement billingCity = driver.findElement(By.xpath("//flexipage-field[@data-field-id='RecordBillingAddressField']//input[@name='city']"));
            billingCity.click();
            billingCity.sendKeys(BillingCity);

            // Field: Billing PostalCode
            WebElement billingPostalCode = driver.findElement(By.xpath("//flexipage-field[@data-field-id='RecordBillingAddressField']//input[@name='postalCode']"));
            billingPostalCode.click();
            billingPostalCode.sendKeys(BillingPostalCode);

            // Field: Province
            WebElement billingProvince = driver.findElement(By.xpath("//flexipage-field[@data-field-id='RecordBillingAddressField']//input[@name='province']"));
            billingProvince.click();
            billingProvince.sendKeys(BillingState);

            // Field: Country
            WebElement billingCountry = driver.findElement(By.xpath("//flexipage-field[@data-field-id='RecordBillingAddressField']//input[@name='country']"));
            billingCountry.click();
            billingCountry.sendKeys(BillingCountry);

            System.out.println("Entering Shipping details");

            // Field: Shipping Address

            WebElement shippingAddress = driver.findElement(By.xpath("//flexipage-field[@data-field-id='RecordShippingAddressField']//input[@placeholder='Search Address']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", shippingAddress);
            shippingAddress.click();
            shippingAddress.sendKeys(ShippingAddress);

            // Clicking TAB to get rid of the lookup
            actions.sendKeys(Keys.TAB).build().perform();

            // Field: Shipping Street
            WebElement shippingStreet = driver.findElement(By.xpath("//flexipage-field[@data-field-id='RecordShippingAddressField']//textarea[@name='street']"));
            shippingStreet.click();
            shippingStreet.sendKeys(ShippingStreet);

            // Field: Shipping City
            WebElement shippingCity = driver.findElement(By.xpath("//flexipage-field[@data-field-id='RecordShippingAddressField']//input[@name='city']"));
            shippingCity.click();
            shippingCity.sendKeys(ShippingCity);

            // Field: Billing PostalCode
            WebElement shippingPostalCode = driver.findElement(By.xpath("//flexipage-field[@data-field-id='RecordShippingAddressField']//input[@name='postalCode']"));
            shippingPostalCode.click();
            shippingPostalCode.sendKeys(ShippingPostalCode);

            // Field: Province
            WebElement shippingProvince = driver.findElement(By.xpath("//flexipage-field[@data-field-id='RecordShippingAddressField']//input[@name='province']"));
            shippingProvince.click();
            shippingProvince.sendKeys(ShippingState);

            // Field: Country
            WebElement shippingCountry = driver.findElement(By.xpath("//flexipage-field[@data-field-id='RecordShippingAddressField']//input[@name='country']"));
            shippingCountry.click();
            shippingCountry.sendKeys(ShippingCountry);
            CustomListener.logStep(("🔗 Entered all the details..."));

            // If duplicate, refrain from creating the account
            Duplicate_Accounts();

            // Submit
            CustomListener.logStep(("🔗 Submitting the account"));
            driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
            CustomListener.logStep(("✅ Account submitted successfully"));
        } else {
            CustomListener.logStep(("⚠️ Test Skipped"));
            throw new SkipException("Skipping test based on Execute column");
        }
    }

    @DataProvider(name = "account_data")
    public String[][] getData() throws IOException {
        String path = "C:\\Users\\josep\\IdeaProjects\\SeleniumJava\\SeleniumJava\\src\\test\\data\\Account_Data.xlsx";

        ExcelReader exceldata = new ExcelReader(path);

        int totalrows = exceldata.getRowCount("Sheet1");
        int totalcols = exceldata.getCellCount("Sheet1", 1);

        String AccountData[][] = new String[totalrows][totalcols];

        for (int i = 1; i <= totalrows; i++) {
            for (int j = 0; j < totalcols; j++) {
                String cellValue = exceldata.getCellData("Sheet1", i, j);
                AccountData[i - 1][j] = (cellValue == null || cellValue.trim().isEmpty()) ? null : cellValue;
            }
        }
        return AccountData;
    }

    //Close all tabs, if open
    public void closeAllTabs() {
        CommonFunctions closetabs = new CommonFunctions(driver);
        closetabs.closeAllTabs();
    }

    //Toast Message for Duplicate Account
    public void ToastMessagePopUp() {
        CommonFunctions toasthandler = new CommonFunctions(driver);
        toasthandler.ToastMessagePopUp();
    }

    public void Duplicate_Accounts() {
        try {
            List<WebElement> duplicateWarnings = driver.findElements(By.xpath("//button[@id='window']/lightning-icon[@icon-name='utility:warning' or @title='Error']"));

            if (!duplicateWarnings.isEmpty()) {
                System.out.println("⚠️ Duplicate Account detected. Account creation not allowed.");
                CustomListener.logStep("⚠️ Duplicate Account Detected. Account creation not allowed.");
                WebElement cancel_button = driver.findElement(By.xpath("//lightning-button/button[@name='CancelEdit']"));
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
                cancel_button.click();
                Assert.fail("❌ Duplicate account detected. Account creation is not allowed.");
            } else {
                System.out.println("✅ No duplicate account. Proceeding to save.");
            }
        } catch (Exception e) {
            System.out.println("❌ Error while checking duplicate account: " + e.getMessage());
        }
    }
}
