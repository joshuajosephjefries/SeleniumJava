package Listener;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.testng.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class CustomListener implements ITestListener, ISuiteListener {
    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private WebDriver driver;
    private static ExtentTest beforeAfterTestLog;
    private String reportPath;
    private static final Logger logger = LoggerFactory.getLogger(CustomListener.class);

    @Override
    public void onStart(ISuite suite) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        reportPath = "test-output/ExtentReport_" + timestamp + ".html";

        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(reportPath);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Advanced Test Execution Report");
        htmlReporter.config().setEncoding("utf-8");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Tester", "QA Automation Engineer");

        beforeAfterTestLog = extent.createTest("BeforeTest & AfterTest Logs");
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getName());
        test.set(extentTest);
        test.get().info("🔹 Test Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("✅ Test Passed");
        test.get().info("Execution Time: " + (result.getEndMillis() - result.getStartMillis()) + " ms");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().fail("❌ Test Failed");
        test.get().fail(result.getThrowable());

        String screenshotPath = captureScreenshot(result.getName());
        if (screenshotPath != null) {
            test.get().addScreenCaptureFromPath(screenshotPath);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("⚠️ Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        openReport(reportPath);
    }

    @BeforeTest
    public void beforeTestLog() {
        beforeAfterTestLog.info("🚀 @BeforeTest executed before test methods.");
    }

    @AfterTest
    public void afterTestLog() {
        beforeAfterTestLog.info("🛑 @AfterTest executed after test methods.");
    }
    private String captureScreenshot(String testName) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenshotPath = "test-output/screenshots/" + testName + ".png";
            FileUtils.copyFile(screenshot, new File(screenshotPath));
            return screenshotPath;
        } catch (Exception e) {
            System.out.println("⚠ Screenshot failed: " + e.getMessage());
            return null;
        }
    }

    private void openReport(String filePath) {
        try {
            Desktop.getDesktop().browse(new File(filePath).toURI());
            System.out.println("🌐 Report opened in browser: " + filePath);
        } catch (IOException e) {
            logger.error("❌ Exception occurred: ", e);
        }
    }
}
