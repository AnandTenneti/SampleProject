import elements.HomePage;
import elements.ProgressBarPage;
import elements.ScrollbarsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.awt.Toolkit;
import java.util.concurrent.TimeUnit;

public class TestClass3 extends BaseTest {
    ArrayList<String> links = new ArrayList<String>(
            Arrays.asList("Scrollbars", "Dynamic Table", "Verify Text", "Progress Bar"));

    @Test(priority = 1)
    public void test_Scrollbar() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(0));
        Assert.assertEquals(driver.getTitle(), links.get(0), "Title is not matching");
        ScrollbarsPage scrollPage = new ScrollbarsPage(driver);
        scrollPage.scrollElementIntoView();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        scrollPage.clickOnButton();
    }

    @Test(priority = 4)
    public void test_progressBar() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(3));
        Assert.assertEquals(driver.getTitle(), links.get(3), "Page title is not matching");
        ProgressBarPage pbPage = new ProgressBarPage(driver);
        String resultDetails = pbPage.getResultDetails();
        Assert.assertEquals(resultDetails, "Result: n/a", "Result details is not matching");
        int progressBarValue = Integer.parseInt(pbPage.getProgressBarValue());
        pbPage.clickOnStartButton();
        do {
            progressBarValue = Integer.parseInt(pbPage.getProgressBarValue());
        } while (progressBarValue != 75);
        pbPage.clickOnStopButton();
        resultDetails = pbPage.getResultDetails();
        Assert.assertTrue(resultDetails.contains("Result: 0"));
    }
}
