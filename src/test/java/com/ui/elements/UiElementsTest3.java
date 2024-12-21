package com.ui.elements;

import com.aventstack.extentreports.Status;
import pages.DynamicTablePage;
import pages.HomePage;
import pages.ProgressBarPage;
import pages.ScrollbarsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;

public class UiElementsTest3 extends BaseTest {
    ArrayList<String> links = new ArrayList<String>(
            Arrays.asList("Scrollbars", "Dynamic Table", "Verify Text", "Progress Bar"));

    @Test(testName = "test_Scrollbar", priority = 1, description = "Scrolling an element into view")
    public void test_Scrollbar() {
        extentTest.log(Status.INFO, "Testing Scrollbar test case");
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(0));
        Assert.assertEquals(driver.getTitle(), links.get(0), "Title is not matching");
        ScrollbarsPage scrollPage = new ScrollbarsPage(driver);
        scrollPage.scrollElementIntoView();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        scrollPage.clickOnButton();
        extentTest.log(Status.INFO, "Completed testing Scrollbar functionality");
    }

    @Test(testName = "test_dynamicTable", priority = 2, description = "Verifying cell value in a " +
            "dynamic table")
    public void test_dynamicTable() throws InterruptedException {
        extentTest.log(Status.INFO, "Testing Dynamic Table test case");
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(1));
        Assert.assertEquals(driver.getTitle(), links.get(1), "Page Title is not matching");
        DynamicTablePage page = new DynamicTablePage(driver);
        String cpuValue = page.getValues("Chrome");
        System.out.println(page.getLabelInMessage());
        Assert.assertTrue(page.getLabelInMessage().contains(cpuValue), "The values are not " +
                "matching");
        extentTest.log(Status.INFO,"Completed testing Dynamic table functionality");
    }

    @Test(testName = "test_verifyText", priority = 3, description = "Finding an element by " +
            "displayed text", enabled = false)
    public void test_verifyText() {
        extentTest.log(Status.INFO, "Testing VerifyText test case");
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(2));
        Assert.assertEquals(driver.getTitle(), links.get(2), "Page Title is not matching");
        //TODO
        extentTest.log(Status.INFO,"Completed testing VerifyText test case");
    }

    @Test(testName = "test_progressBar", priority = 4, description = "Verify progress bar " +
            "functionality")
    public void test_progressBar() {
        extentTest.log(Status.INFO, "Testing Progress Bar test case");
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
        extentTest.log(Status.INFO, "Completed testing Progress Bar functionality");
    }
}
