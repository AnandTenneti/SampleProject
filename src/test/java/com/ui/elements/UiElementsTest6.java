package com.ui.elements;


import com.aventstack.extentreports.Status;
import com.dataprovider.DataSupplier;
import com.dataprovider.TestData.WaitTime;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AnimatedButtonPage;
import pages.AutoWaitPage;
import pages.DisabledInputPage;
import pages.HomePage;
import com.dataprovider.TestData.ElementType;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;

public class UiElementsTest6 extends BaseTest {

    ArrayList<String> links = new ArrayList<String>(
            Arrays.asList("Animated Button", "Disabled Input", "Auto Wait"));


    @Test(testName = "test_animatedButton", priority = 1, description = "wait for the animation to " +
            "stop before clicking a button")
    public void test_animatedButton() throws InterruptedException {
        extentTest.log(Status.INFO, "Testing Animated button test case");
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(0));
        Assert.assertEquals(driver.getTitle(), links.get(0), "Title is not matching");
        AnimatedButtonPage abPage = new AnimatedButtonPage(driver);
        abPage.clickOnStartAnimationButton();
        abPage.waitForSpinElementNotToBeDisplayed();
        String classNameOfMovingTargetButton = abPage.getClassNameOfMovingTargetButton();
        System.out.println(classNameOfMovingTargetButton);
        abPage.clickOnMovingTargetButton();
        String statusMessage = abPage.getMessageDetails();
        System.out.println(statusMessage);
        Assert.assertTrue(statusMessage.contains(classNameOfMovingTargetButton));
        extentTest.log(Status.INFO, "Completed testing animated button functionality");
    }

    @Test(testName = "test_disabledInput", priority = 2, description = "wait for the edit field to " +
            "become enabled")
    public void test_disabledInput() throws InterruptedException {
        extentTest.log(Status.INFO, "Testing disabled Input test case");
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(1));
        Assert.assertEquals(driver.getTitle(), links.get(1), "Title is not matching");
        DisabledInputPage page = new DisabledInputPage(driver);
        page.clickOnEnableButton();
        Assert.assertEquals(page.isInputFieldEnabled(), false, "Not matching");
        String elementStatus = page.getStatus();
        Assert.assertEquals(elementStatus, "Input Disabled...", "Text is not matching");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("inputField")));
        elementStatus = page.getStatus();
        Assert.assertEquals(elementStatus, "Input Enabled...", "Text is not matching");
        page.enterInputValue("Hello");
        extentTest.log(Status.INFO, "Completed testing disabled input functionality");
    }


    @Test(testName = "test_autoWait", priority = 3, dataProvider = "elementTypeTestdata",
            dataProviderClass =
                    DataSupplier.class, description = "wait for the element to become intractable")
    public void test_autoWait(String elementType) {
        extentTest.log(Status.INFO, "Testing Auto Wait test case");
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(2));
        Assert.assertEquals(driver.getTitle(), links.get(2), "Title is not matching");
        AutoWaitPage awPage = new AutoWaitPage(driver);
        awPage.selectElementType(elementType);
        String tagName = awPage.getTagName();
        System.out.println(tagName);
        Assert.assertEquals(elementType.toLowerCase(), awPage.getTagName(),
                "Both are not equal");
        Assert.assertTrue(awPage.isElementDisplayed(tagName));
        awPage.actionOnElement(elementType.toLowerCase());
        awPage.clickOnApplyButton(1);
        String status = "Target element settings applied for 3 seconds.";
        Assert.assertEquals(awPage.getStatusMessage(), status, "Messages are not same");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WaitTime.WAIT_FOR_3S.getWaitTime()));
        wait.until(ExpectedConditions
                .textToBePresentInElementLocated(
                        By.id("opstatus"), "Target element state restored."));
        Assert.assertTrue(awPage.getStatusMessage().equalsIgnoreCase(
                "Target element state restored."), "Messages are " +
                "not same");
        extentTest.log(Status.INFO, "Completed testing autowait functionality");
    }
}
