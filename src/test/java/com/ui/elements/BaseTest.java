package com.ui.elements;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import static com.dataprovider.TestData.PATH_TO_CONFIG_FILE;
import static com.dataprovider.TestData.USER_DIR;

public class BaseTest {
    public static WebDriver driver;
    public static String screenshotsSubFolderName;

    Properties properties = new Properties();


    @BeforeTest(alwaysRun = true)
    public void setUp() throws IOException {git add
        driver = Browser.getDriver("chrome");
        FileInputStream fis = new FileInputStream(new File(USER_DIR + PATH_TO_CONFIG_FILE));
        properties.load(fis);
    }

    @BeforeMethod(alwaysRun = true)
    public void launchURL() {
        driver.get(properties.getProperty("baseURL"));
    }

    @AfterMethod(alwaysRun = true)
    public void screenShotCapture(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            CaptureScreenshot(result.getTestContext().getName() + "_" + result.getName());
        }
    }

    @AfterTest(alwaysRun = true)
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
