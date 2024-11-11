package com.ui.elements;

import com.ui.elements.BaseTest;
import pages.AlertsPage;
import pages.HomePage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class TestClass5 extends BaseTest {


    ArrayList<String> links = new ArrayList<String>(
            Arrays.asList("Overlapped Element", "Shadow DOM", "Alerts", "File Upload"));

    @Test(priority = 3)
    public void test_progressBar() throws InterruptedException {
        driver.get("http://uitestingplayground.com/progressbar");
        String resultText = driver.findElement(By.id("result")).getText();
        System.out.println(resultText);
        Assert.assertEquals(resultText, "Result: n/a");
        driver.findElement(By.id("startButton")).click();
        String progressBarValue = driver.findElement(By.id("progressBar")).getAttribute("aria" +
                "-valuenow");
        int progressBarVal = Integer.parseInt(progressBarValue);
        do {
            progressBarValue = driver.findElement(By.id("progressBar")).getAttribute("aria" +
                    "-valuenow");
            progressBarVal = Integer.parseInt(progressBarValue);

        } while (progressBarVal != 75);
        if (progressBarVal == 75) {
            driver.findElement(By.id("stopButton")).click();
        }
        resultText = driver.findElement(By.id("result")).getText();
        Thread.sleep(5000);
        Assert.assertTrue(resultText.contains("Result: 0"));

    }


    @Test(priority = 6)
    public void test_Alerts() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(2));
        Assert.assertEquals(driver.getTitle(),"Alerts","Title is not matching");
        AlertsPage alertPage = new AlertsPage(driver);
        alertPage.clickOnAlertButton();
        alertPage.waitUntilAlertDialogIsDisplayed();
        String simpleAlertMessage = alertPage.verifyAlertTextMessage();
        String expectedAlertMessage = "Today is a working day.\nOr less likely a holiday.";
        Assert.assertEquals(simpleAlertMessage,expectedAlertMessage,"Alert Messages are not " +
                "matching");
        alertPage.clickOnOkButton();
        Thread.sleep(2000);
        // Confirm Alert
        alertPage.clickOnConfirmAlertButton();
      alertPage.waitUntilAlertDialogIsDisplayed();
        String confirmAlertMessage = alertPage.verifyAlertTextMessage();
        System.out.println(confirmAlertMessage);
        alertPage.clickOnOkButton();
        alertPage.waitUntilAlertDialogIsDisplayed();
        confirmAlertMessage = alertPage.verifyAlertTextMessage();
        System.out.println(confirmAlertMessage);
        Thread.sleep(2000);
        alertPage.clickOnOkButton();
        Thread.sleep(2000);
        alertPage.clickOnPromptAlertButton();
        alertPage.waitUntilAlertDialogIsDisplayed();
        alertPage.enterTextIntoAlert("dogs");
        alertPage.acceptOrdismiss("No");
        alertPage.waitUntilAlertDialogIsDisplayed();
        String promptAlertMessage = alertPage.verifyAlertTextMessage();
        System.out.println(promptAlertMessage);
        alertPage.acceptOrdismiss("Yes");
    }


}
