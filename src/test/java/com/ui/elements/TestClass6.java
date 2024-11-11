package com.ui.elements;

import com.ui.elements.BaseTest;
import pages.AnimatedButtonPage;
import pages.DisabledInputPage;
import pages.HomePage;
import pages.VisibilityPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;

public class TestClass6 extends BaseTest {

    ArrayList<String> links = new ArrayList<String>(
            Arrays.asList("Animated Button", "Disabled Input", "Auto Wait"));


    @Test(priority = 1)
    public void test_animatedButton() throws InterruptedException {
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
    }

    @Test(priority = 2)//Done
    public void test_disabledInput() throws InterruptedException {
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
    }


    @Test(priority = 3)
    public void test_autoWait() throws InterruptedException {
        ArrayList<String> links = new ArrayList<String>(
                Arrays.asList("Button", "Input", "Textarea", "Select", "Label"));
        driver.get("http://uitestingplayground.com/");
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(2));
        Assert.assertEquals(driver.getTitle(), links.get(2), "Title is not matching");
        Thread.sleep(2000);
        Select select = new Select(driver.findElement(By.id("element-type")));
        select.selectByVisibleText("Select");
        Thread.sleep(5000);
        Assert.assertTrue(driver.findElement(By.cssSelector("select#target")).isDisplayed());
        driver.findElement(By.id("applyButton3")).click();
        String status = "Target element settings applied for 3 seconds.";
        Assert.assertEquals(driver.findElement(By.id("opstatus")).getText(), status, "Messages are not same");
        Thread.sleep(3000);

        Assert.assertTrue(driver.findElement(By.id("opstatus")).getText().equalsIgnoreCase(
                "Target element state restored."), "Messages are " +
                "not same");

//        String resultText = driver.findElement(By.id("result")).getText();
//        System.out.println(resultText);
//        Assert.assertEquals(resultText, "Result: n/a");
//        driver.findElement(By.id("startButton")).click();
//        String progressBarValue = driver.findElement(By.id("progressBar")).getAttribute("aria" +
//                "-valuenow");
//        int progressBarVal = Integer.parseInt(progressBarValue);
//        do {
//            progressBarValue = driver.findElement(By.id("progressBar")).getAttribute("aria" +
//                    "-valuenow");
//            progressBarVal = Integer.parseInt(progressBarValue);
//
//        } while (progressBarVal != 75);
//        if (progressBarVal == 75) {
//            driver.findElement(By.id("stopButton")).click();
//        }
//        resultText = driver.findElement(By.id("result")).getText();
//        Thread.sleep(5000);
//        Assert.assertTrue(resultText.contains("Result: 0"));
    }
}
