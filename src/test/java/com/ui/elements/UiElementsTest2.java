package com.ui.elements;

import com.aventstack.extentreports.Status;
import pages.AjaxDataPage;
import pages.ClientDelayPage;
import pages.HomePage;
import pages.TextInputPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class UiElementsTest2 extends BaseTest {

    ArrayList<String> links = new ArrayList<String>(
            Arrays.asList("AJAX Data", "Client Side Delay", "Click", "Text Input"));

    @Test(testName = "test_ajaxData", priority = 1, description = "Ajax request test")
    public void test_ajaxData() {
        extentTest.log(Status.INFO,"Testing Ajax data test case");
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(0));
        Assert.assertEquals(driver.getTitle(), links.get(0), "Title is not matching");
        AjaxDataPage ajaxData = new AjaxDataPage(driver);
        ajaxData.clickOnAjaxDataButton();
        ajaxData.waitForAjaxDataToBeDisplayed();
        String ajaxLoadedData = ajaxData.getAjaxLoadedData();
        Assert.assertEquals(ajaxLoadedData, "Data loaded with AJAX get request.", "Message not " +
                "matching");
        extentTest.log(Status.INFO, "Completed testing Ajax data functionality");
    }

    @Test(testName = "test_clientDelay", priority = 2, description = "Event based click on an " +
            "element")
    public void test_clientDelay() {
        extentTest.log(Status.INFO,"Testing Client Delay test case");
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
        extentTest.log(Status.INFO, "Completed testing clientDelay functionality");
    }

    @Test(testName = "test_Click", priority = 3, enabled=false)
    public void test_Click() {
        extentTest.log(Status.INFO,"Testing Click test case");
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(2));
        Assert.assertEquals(driver.getTitle(), links.get(2), "Page Title is not matching");
        // TODO
        extentTest.log(Status.INFO, "Completed testing Click functionality");
    }

    @Test(testName = "test_textInput", priority = 4, description="Entering text into an edit " +
            "field  may not have affect")
    public void test_TextInput() {
        extentTest.log(Status.INFO,"Testing Text Input test case");
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(3));
        Assert.assertEquals(driver.getTitle(), links.get(3), "Page Title is not matching");
        TextInputPage tiPage = new TextInputPage(driver);
        String buttonText = tiPage.getButtonText();
        String newButtonName = "My New Button";
        tiPage.setNewButtonName(newButtonName);
        tiPage.clickOnButton();
        buttonText = tiPage.getButtonText();
        Assert.assertEquals(buttonText, newButtonName, "Button Name is not matching");
        extentTest.log(Status.INFO, "Completed testing textInput functionality");
    }
}
