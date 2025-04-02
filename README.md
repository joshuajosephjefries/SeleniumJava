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

┣ 📂 src/main/java

┃ 📂 src/main/java/Listener       # Custom TestNG Listener

┃ ┣ 📜 src/main/java/Google/GoogleSearch          # Sample Test Case

┣ 📂 reports                      # 📊 Generated Extent Reports

┣ 📂 screenshots                   # 📸 Captured Screenshots

┗ 📜 pom.xml                       # Maven Dependencies

## 🚀 How to Run the Tests

📢 Add the Drivers to C://Drivers dir location and change the location in the System.setProperty of Java class files

### Clean and Run Tests
<i> mvn clean test </i>

 📢 After execution, the Extent Report will open automatically in your default browser!

📢 For SalesForce Login, enter your username and password in Credentials.java