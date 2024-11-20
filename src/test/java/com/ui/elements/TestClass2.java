package com.ui.elements;

import com.ui.elements.BaseTest;
import pages.AjaxDataPage;
import pages.ClientDelayPage;
import pages.HomePage;
import pages.TextInputPage;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class TestClass2 extends BaseTest {

    ArrayList<String> links = new ArrayList<String>(
            Arrays.asList("AJAX Data", "Client Side Delay", "Click", "Text Input"));

    @Test(testName = "test_ajaxData", priority = 1)
    public void test_ajaxData() {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(0));
        Assert.assertEquals(driver.getTitle(), links.get(0), "Title is not matching");
        AjaxDataPage ajaxData = new AjaxDataPage(driver);
        ajaxData.clickOnAjaxDataButton();
        ajaxData.waitForAjaxDataToBeDisplayed();
        String ajaxLoadedData = ajaxData.getAjaxLoadedData();
        Assert.assertEquals(ajaxLoadedData, "Data loaded with AJAX get request.", "Message not " +
                "matching");
    }

    @Test(testName = "test_clientDelay", priority = 2)
    public void test_clientDelay() {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(1));
        Assert.assertEquals(driver.getTitle(), links.get(1), "Page Title is not matching");
        ClientDelayPage cdPage = new ClientDelayPage(driver);
        // Click on the button triggering client side logic
        cdPage.clickOnButton();
        cdPage.waitForLabelToBeDisplayed();
        Assert.assertTrue(cdPage.isLabelDisplayed());
        Assert.assertEquals(cdPage.getLabelMessage(), "Data calculated on the client side.", "Label" +
                " Message is not matching");
    }

    @Test(testName = "test_Click", priority = 3)
    public void test_Click() {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(2));
        Assert.assertEquals(driver.getTitle(), links.get(1), "Page Title is not matching");
        // TODO

    }

    @Test(testName = "test_textInput", priority = 4)
    public void test_TextInput() {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(3));
        Assert.assertEquals(driver.getTitle(), links.get(2), "Page Title is not matching");
        TextInputPage tiPage = new TextInputPage(driver);
        String buttonText = tiPage.getButtonText();
        String newButtonName = "My New Button";
        tiPage.setNewButtonName(newButtonName);
        tiPage.clickOnButton();
        buttonText = tiPage.getButtonText();
        Assert.assertEquals(buttonText, newButtonName, "Button Name is not matching");
    }
}
