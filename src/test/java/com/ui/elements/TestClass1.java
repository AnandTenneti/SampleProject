package com.ui.elements;

import com.ui.elements.BaseTest;
import pages.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;

public class TestClass1 extends BaseTest {

    ArrayList<String> links = new ArrayList<String>(
            Arrays.asList("Dynamic ID", "Class Attribute", "Hidden Layers", "Load Delay"));

    @Test(priority = 1)
    public void test_dynamicId() {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(0));
        Assert.assertEquals(driver.getTitle(), links.get(0), "Title is not matching");
        DynamicIdPage dynamicPage = new DynamicIdPage(driver);
        dynamicPage.clickOnButtonWithoutUsingDynamicId();
    }

    @Test(priority = 2)
    public void test_classAttribute() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(1));
        Assert.assertEquals(driver.getTitle(), links.get(1), "Page Title is not matching");
        ClassAttributePage classAttribute = new ClassAttributePage(driver);
        classAttribute.clickOnButton();
//        driver.findElement(By.xpath
//                        ("//button[contains(concat(' ',normalize-space(@class),' '),'btn-primary')]"))
//                .click();
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        Thread.sleep(5000);
        alert.accept();
        Thread.sleep(3000);
    }

    @Test(priority = 3)
    public void test_hiddenLayers() {
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

    @Test(priority = 4)
    public void test_loadDelay() {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(3));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.urlContains("loaddelay"));
        Assert.assertEquals(driver.getTitle(), links.get(3) + "s", "Title is not matching");
        LoadDelayPage loadDelayPage = new LoadDelayPage(driver);
        Assert.assertTrue(loadDelayPage.isButtonDisplayed());
        loadDelayPage.clickOnButton();
    }

    //@Test(priority = 5, enabled = false)
    public void test_disableInput() throws InterruptedException {
        driver.get("http://uitestingplayground.com/disabledinput");
        String elementStatus = driver.findElement(By.id("opstatus")).getText();
//        String buttonText = driver.findElement(By.id("updatingButton")).getText();
        System.out.println(elementStatus);
        driver.findElement(By.id("enableButton")).click();
        elementStatus = driver.findElement(By.id("opstatus")).getText();

        System.out.println(elementStatus);
        Thread.sleep(5000);
        elementStatus = driver.findElement(By.id("opstatus")).getText();

        System.out.println(elementStatus);
        //driver.findElement(By.id("inputField")).sendKeys("Anand");
//        buttonText = driver.findElement(By.id("updatingButton")).getText();
//        System.out.println(buttonText);
//        Assert.assertEquals(buttonText, "Hello");
    }

    //@Test(priority = 6, enabled = false)
    public void test_Alerts() throws InterruptedException {
        driver.get("http://uitestingplayground.com/alerts");
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(2));
        driver.findElement(By.id("alertButton")).click();
        Alert simpleAlert = driver.switchTo().alert();
        System.out.println(simpleAlert.getText());
        simpleAlert.accept();
        Thread.sleep(2000);
        driver.findElement(By.id("confirmButton")).click();
        Alert confirmAlert = driver.switchTo().alert();
        System.out.println(confirmAlert.getText());
        confirmAlert.accept();
        Thread.sleep(2000);
        Alert confirmAgainAlert = driver.switchTo().alert();
        System.out.println(confirmAgainAlert.getText());
        confirmAgainAlert.accept();
        Thread.sleep(2000);
        driver.findElement(By.id("promptButton")).click();
        Alert promptAlert = driver.switchTo().alert();
        promptAlert.sendKeys("dogs");
        promptAlert.accept();
        Thread.sleep(2000);
        Alert promptAgainAlert = driver.switchTo().alert();
        System.out.println(confirmAgainAlert.getText());
        promptAgainAlert.accept();
        Thread.sleep(2000);
//        Alert simpleAlert = driver.switchTo().alert();
//        System.out.println(simpleAlert.getText());
//        simpleAlert.accept();
//        Thread.sleep(2000);

    }


}
