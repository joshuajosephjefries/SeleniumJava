package Listener;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomListener implements ITestListener {
    private static ExtentReports extent;
    private static ExtentTest test;
    private static WebDriver driver;
    private static String reportPath;
    private static String screenshotDir;

    @Override
    public void onStart(ITestContext context) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        reportPath = System.getProperty("user.dir") + "/reports/ExtentReport_" + timestamp + ".html";
        screenshotDir = System.getProperty("user.dir") + "/screenshots/" + timestamp + "/";

        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(reportPath);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle("Advanced Test Report");
        htmlReporter.config().setReportName("Automation Test Results");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        System.out.println("📌 Extent Report Created: " + reportPath);
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest("🚀 Test Started: " + result.getMethod().getMethodName());
        test.log(Status.INFO, "🟢 Test Execution Started");
        System.out.println("✅ Test Started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "✅ Test Passed Successfully");
        attachScreenshot("PASS");
        System.out.println("✔ Test Passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "❌ Test Failed: " + result.getThrowable());
        attachScreenshot("FAIL");
        System.out.println("❌ Test Failed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "⚠️ Test Skipped");
        System.out.println("⚠️ Test Skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        System.out.println("📄 Extent Report Generated: " + reportPath);
        openReport();
    }

    private static void attachScreenshot(String stepName) {
        if (driver != null) {
            try {
                // 🛠 Ensure the screenshot directory exists
                File screenshotFolder = new File(screenshotDir);
                if (!screenshotFolder.exists()) {
                    screenshotFolder.mkdirs();
                }

                // 📸 Capture Screenshot
                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String sanitizedStepName = stepName.replaceAll("[^a-zA-Z0-9]", "_");
                String screenshotPath = screenshotDir + sanitizedStepName + "_" + System.currentTimeMillis() + ".png";

                File dest = new File(screenshotPath);
                Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);

                // ✅ Fix: Use relative path for Extent Reports
                String relativeScreenshotPath = "./" + screenshotPath.replace(System.getProperty("user.dir"), "");

                // 📄 Add Screenshot to Extent Report
                test.addScreenCaptureFromPath(relativeScreenshotPath);
                test.log(Status.INFO, "📸 Screenshot Captured: " + stepName);
                System.out.println("📸 Screenshot Saved: " + dest.getAbsolutePath());
            } catch (IOException e) {
                System.out.println("❌ Screenshot Error: " + e.getMessage());
            }
        } else {
            System.out.println("⚠️ WebDriver is NULL! Cannot take screenshot.");
        }
    }

    private void openReport() {
        try {
            File reportFile = new File(reportPath);
            java.awt.Desktop.getDesktop().browse(reportFile.toURI());
            System.out.println("🌍 Opening Report in Browser: " + reportPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }

    // Utility Method to Log Detailed Steps
    public static void logStep(String stepDescription) {
        if (test != null) {
            test.log(Status.INFO, "📝 " + stepDescription);
            System.out.println("📝 Step Logged: " + stepDescription);

            // 📸 Capture Screenshot for Every Step
            attachScreenshot(stepDescription.replaceAll("[^a-zA-Z0-9]", "_"));
        }
    }
}
