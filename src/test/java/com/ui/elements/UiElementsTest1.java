package com.ui.elements;

import com.aventstack.extentreports.Status;
import pages.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;

public class UiElementsTest1 extends BaseTest {
,
    ArrayList<String> links = new ArrayList<String>(
            Arrays.asList("Dynamic ID", "Class Attribute", "Hidden Layers", "Load Delay"));

    @Test(priority = 1, description = "Click button without dynamic ids")
    public void test_dynamicId() {
        extentTest.log(Status.INFO,"Testing dynamic Id test case");
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(0));
        Assert.assertEquals(driver.getTitle(), links.get(0), "Title is not matching");
        DynamicIdPage dynamicPage = new DynamicIdPage(driver);
        dynamicPage.clickOnButtonWithoutUsingDynamicId();
    }

    @Test(priority = 2, description = "Verify class attribute ")
    public void test_classAttribute() throws InterruptedException {
        extentTest.log(Status.INFO,"Testing class Attribute test case");
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(1));
        Assert.assertEquals(driver.getTitle(), links.get(1), "Page Title is not matching");
        ClassAttributePage classAttribute = new ClassAttributePage(driver);
        classAttribute.clickOnButton();
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();
    }

    @Test(priority = 3, description = "test does not interact with elements because of z -order",
            dependsOnMethods = {"test_classAttribute"})
    public void test_hiddenLayers() {
        extentTest.log(Status.INFO,"Testing hidden Layers test case");
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(2));
        Assert.assertEquals(driver.getTitle(), links.get(2), "Title is not matching");
        HiddenLayersPage hlPage = new HiddenLayersPage(driver);
        String greenButtonCssValue = hlPage.getCssValueOfElement();
        System.out.println(greenButtonCssValue);
        hlPage.clickOnGreenButton();
        greenButtonCssValue = hlPage.getCssValueOfElement();
        System.out.println(greenButtonCssValue);
        //Assert.assertEquals(hlPage.isGreenButtonDisplayed(), false, "Green button is displayed");
    }

    @Test(priority = 4, description = "waiting for a page to load")
    public void test_loadDelay() {
        extentTest.log(Status.INFO,"Testing load Delay test case");
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(3));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.urlContains("loaddelay"));
        Assert.assertEquals(driver.getTitle(), links.get(3) + "s", "Title is not matching");
        LoadDelayPage loadDelayPage = new LoadDelayPage(driver);
        Assert.assertTrue(loadDelayPage.isButtonDisplayed());
        loadDelayPage.clickOnButton();
    }
}
