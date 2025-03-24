# 🚀 Selenium Java - Test Automation Framework

### 📌 Practicing Frameworks with Selenium & TestNG

### 1. TESTNG - Custom Listener Implementation

I have used a custom TestNG listener that automatically opens the Extent Report in Chrome once execution is completed.

#### 📌 Features of the Custom Listener

 📊 Used Extent Reports with ExtentSparkReporter

 🟢 Reporting Shows: START ⏳, PASS ✅, FAIL ❌, SKIPPED ⚠️

 🌐 Report Automatically Opens in Browser after test execution

 📸 Screenshots are Saved in /screenshots/ Folder

 🕒 Better Naming for Screenshot Files (Includes Date & Time)

 📝 Detailed Step Execution Logs in Console Output

 📜 Step Logs Are Included in the Extent Report

## 📂 Directory Structure

📂 SeleniumJava

┣ 📂 src/test/java

┃ ┣ 📜 CustomTestListener.java    # Custom TestNG Listener

┃ ┣ 📜 SampleTest.java            # Sample Test Case

┣ 📂 reports                      # 📊 Generated Extent Reports

┣ 📂 screenshots                   # 📸 Captured Screenshots

┗ 📜 pom.xml                       # Maven Dependencies

## 🚀 How to Run the Tests

### Clean and Run Tests
<i> mvn clean test </i>

 📢 After execution, the Extent Report will open automatically in your default browser!