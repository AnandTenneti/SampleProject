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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import static com.dataprovider.TestData.USER_DIR;

public class BaseTest {
    public static WebDriver driver;
    public static String screenshotsSubFolderName;

    Properties properties = new Properties();


    @BeforeTest
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);

        FileInputStream fis = new FileInputStream(new File(USER_DIR + "/src/main/resources/config" +
                ".properties"));
        properties.load(fis);

    }

    @BeforeMethod
    public void launchURL() {
        driver.get(properties.getProperty("baseURL"));
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
