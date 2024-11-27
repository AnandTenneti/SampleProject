package com.ui.elements;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseTest {
    public static WebDriver driver;
    public static String screenshotsSubFolderName;

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @BeforeMethod
    public void launchURL() {
        driver.get("http://uitestingplayground.com/");
    }

    @AfterMethod
    public void screenShotCapture(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            CaptureScreenshot(result.getTestContext().getName() + "_" + result.getName());
        }
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    public static void CaptureScreenshot(String fileName) {
        if (screenshotsSubFolderName == null) {
            LocalDateTime dateObj = LocalDateTime.now();
            DateTimeFormatter formatObj = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
            screenshotsSubFolderName = dateObj.format(formatObj);
            System.out.println(screenshotsSubFolderName);
        }
        TakesScreenshot ts = (TakesScreenshot) driver;
        File file = ts.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file,
                    new File("./Screenshots/" + screenshotsSubFolderName + "/" + fileName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Screenshot Saved successfully");
    }
}
