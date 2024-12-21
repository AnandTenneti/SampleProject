package com.ui.elements;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.internal.invokers.InvokedMethod;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import static com.dataprovider.TestData.*;
import static java.lang.System.getProperty;

public class BaseTest {
    public static WebDriver driver;
    public static String screenshotsSubFolderName;
    public static ExtentReports extentReports;

    public static ExtentTest extentTest;

    Properties properties = new Properties();


    @BeforeSuite(alwaysRun = true)
    public void initialiseExtentReports() throws IOException {
        String browserName = getProperty("browser") != null ? getProperty("browser") : "chrome";
        driver = Browser.getDriver(browserName);
        Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
        String browser = capabilities.getBrowserName();
        String browserVersion = capabilities.getBrowserVersion();
        System.out.println(capabilities.getCapabilityNames().toString());
        ExtentSparkReporter sparkReporter_all = new ExtentSparkReporter("reports/index.html");
        sparkReporter_all.config().setReportName("WebDriver Automation Report");
        sparkReporter_all.config().setDocumentTitle("Ui Automation Playground ");
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter_all);
        extentReports.setSystemInfo("Application URL", BASE_URL);
        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
        extentReports.setSystemInfo("Browser", browser.toUpperCase());
        extentReports.setSystemInfo("Version", browserVersion);
    }

    @AfterSuite(alwaysRun = true)
    public void generateReports() {
        driver.quit();
        extentReports.flush();
    }

    @BeforeTest(alwaysRun = true)
    public void createExtentTest(ITestContext context) throws IOException {
        extentTest = extentReports.createTest(context.getCurrentXmlTest().getName());
        System.out.println(context.getName());
    }

    @BeforeMethod(alwaysRun = true)
    public void launchURL() {
        driver.get(BASE_URL);
    }

    @AfterMethod(alwaysRun = true)
    public void screenShotCapture(Method m, ITestResult result) {
        //Verifying the result status and perform the further actions needed
            if (result.getStatus() == ITestResult.FAILURE) {
                String base64Code = CaptureScreenshot();
                extentTest.addScreenCaptureFromBase64String(base64Code, result.getName());
                extentTest.fail(result.getThrowable());
                //extentTest.fail(MediaEntityBuilder.createScreenCaptureFromBase64String
                // (base64Code).build());

                // code to capture screenshot from filePath
                /**extentTest.addScreenCaptureFromPath(screenshotPath);
                 String path = CaptureScreenshot("dyamicTable.jpg");
                 extentTest.addScreenCaptureFromPath(path, "Dynamic Table Page");**/
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                extentTest.pass(m.getName() + " is passed");
            } else if (result.getStatus() == ITestResult.SKIP) {
                extentTest.skip(m.getName() + " is skipped");
            } else {
                extentTest.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIPPED ",
                        ExtentColor.ORANGE));
                extentTest.skip(result.getThrowable());
            }
            extentTest.assignCategory(result.getMethod().getGroups());
        }


    public static String CaptureScreenshot() {
        TakesScreenshot ts = (TakesScreenshot) driver;
        String base64Code = ts.getScreenshotAs(OutputType.BASE64);
        System.out.println("Screenshot Saved successfully");
        return base64Code;
    }

    public static String CaptureScreenshot(String fileName) {
        if (screenshotsSubFolderName == null) {
            LocalDateTime dateObj = LocalDateTime.now();
            DateTimeFormatter formatObj = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
            screenshotsSubFolderName = dateObj.format(formatObj);
            System.out.println(screenshotsSubFolderName);
        }
        TakesScreenshot ts = (TakesScreenshot) driver;
        File file = ts.getScreenshotAs(OutputType.FILE);
        File destinationFile =
                new File(USER_DIR + "/Screenshots/" + screenshotsSubFolderName + "/" + fileName);
        try {
            FileUtils.copyFile(file, destinationFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Screenshot Saved successfully");
        return destinationFile.getAbsolutePath();
    }
}
