package com.ui.elements;

import com.aventstack.extentreports.Status;
import com.dataprovider.DataSupplier;
import com.dataprovider.ExcelDataSupplier;
import org.openqa.selenium.WebElement;
import pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class UiElementsTest4 extends BaseTest {

    ArrayList<String> links = new ArrayList<String>(
            Arrays.asList("Visibility", "Sample App", "Mouse Over", "Non-Breaking Space"));

    @Test(testName = "test_visibility", priority = 1, description = "test visibility of an element " +
            "on screen")
    public void test_visibility() throws InterruptedException {
        extentTest.log(Status.INFO, "Testing Visibility test case");
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(0));
        Assert.assertEquals(driver.getTitle(), links.get(0), "Title is not matching");
        // Verify the functionality in Visibility page
        VisibilityPage visibilityPage = new VisibilityPage(driver);
        int noOfElementsDisplayed = visibilityPage.areButtonsDisplayed();
        Assert.assertTrue(noOfElementsDisplayed > 1);
        visibilityPage.clickOnHideButton();
        Assert.assertTrue(visibilityPage.verifyButtonNotDisplayed(), "Element is displayed");
        int noOfElementsDisplayed_updated = visibilityPage.areButtonsDisplayed();
        Assert.assertNotEquals(noOfElementsDisplayed, noOfElementsDisplayed_updated);
        extentTest.log(Status.INFO, "Completed testing Visibility functionality");
    }

    /***
     * In the sampleApp, we are verifying login success functionality
     * @param username can use any of the alphanumeric values
     *                 and the password value is constant
     */
    @Test(testName = "test_sampleApp", priority = 2, dataProvider = "loginTestData",
            dataProviderClass =
                    DataSupplier.class,
            description = "test user login with data from data provider class")
    public void test_sampleApp(String username) {
        extentTest.log(Status.INFO, "Testing sampleApp test case");
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(1));
        Assert.assertEquals(driver.getTitle(), links.get(1), "Title is not matching");

        SampleAppPage samplePage = new SampleAppPage(driver);
        String loginButtonText = samplePage.getLoginButtonText();
        Assert.assertEquals(loginButtonText, "Log In", "Button text is not matching");
        samplePage.setUsername(username);
        samplePage.setPassword();
        samplePage.clickOnLoginButton();
        String loginMessage = samplePage.getLoginStatusText();
        String actualLoginMessage = "Welcome, " + username + "!";
        Assert.assertEquals(loginMessage, actualLoginMessage, "Both messages are not matching");
        loginButtonText = samplePage.getLoginButtonText();
        Assert.assertEquals(loginButtonText, "Log Out", "Button text is not matching");
        samplePage.clickOnLoginButton();
        String logoutMessage = samplePage.getLoginStatusText();
        Assert.assertEquals(logoutMessage, "User logged out.", "logout message is incorrect");
        extentTest.log(Status.INFO, "Completed testing sampleApp functionality");
    }

    /***
     * In the sampleApp, we are verifying login success functionality
     * with the details from an Excel file
     * @param username can use any of the alphanumeric values
     * @param password value is constant
     *
     */

    @Test(testName = "test_sampleApp_using_excelData", priority = 3, dataProvider = "excelLoginData", dataProviderClass =
            ExcelDataSupplier.class, description = "test user login with data from excel")
    public void test_sampleApp_using_excelData(String username, String password) {
        extentTest.log(Status.INFO, "Testing sampleApp using excel data test case");
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(1));
        Assert.assertEquals(driver.getTitle(), links.get(1), "Title is not matching");

        SampleAppPage samplePage = new SampleAppPage(driver);
        String loginButtonText = samplePage.getLoginButtonText();
        Assert.assertEquals(loginButtonText, "Log In", "Button text is not matching");
        samplePage.setUsername(username);
        samplePage.setPassword(password);
        samplePage.clickOnLoginButton();
        String loginMessage = samplePage.getLoginStatusText();
        String actualLoginMessage = "Welcome, " + username + "!";
        Assert.assertEquals(loginMessage, actualLoginMessage, "Both messages are not matching");
        loginButtonText = samplePage.getLoginButtonText();
        Assert.assertEquals(loginButtonText, "Log Out", "Button text is not matching");
        samplePage.clickOnLoginButton();
        String logoutMessage = samplePage.getLoginStatusText();
        Assert.assertEquals(logoutMessage, "User logged out.", "logout message is incorrect");
        extentTest.log(Status.INFO, "Completed testing SampleApp with data from Excel file " +
                "functionality");
    }

    @Test(testName = "test_mouseOver", priority = 4, description = "test mouseOver functionality")
    public void test_mouseOver() throws InterruptedException {
        extentTest.log(Status.INFO, "Testing MouseOver test case");
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(2));
        Assert.assertEquals(driver.getTitle(), links.get(2), "Page Title is not matching");

        MouseOverPage moPage = new MouseOverPage(driver);
        String tooltipText = moPage.getToolTipTitleOfClickMe();
        System.out.println("The tooltip text before mouse hover is " + tooltipText);
        Assert.assertEquals(tooltipText, "Click me", "Tooltip title is not matching");
        moPage.hoverOverLink("1");
        tooltipText = moPage.getToolTipTitleOfClickMe();
        System.out.println("The tooltip text after mouse hover is " + tooltipText);

        Assert.assertEquals(tooltipText, "Active Link", "Tooltip title is not matching");
        WebElement clickMe = moPage.getElement();
        moPage.clickOnLink(clickMe);
        int clicksCount = moPage.getClickCount();
        Assert.assertEquals(clicksCount, 2, "Clicks count is not matching");
        tooltipText = moPage.getToolTipTitleOfLinkButton();
        System.out.println("The tooltip text after mouse hover is " + tooltipText);
        Assert.assertEquals(tooltipText, "Link Button", "Tooltip title is not matching");
        moPage.hoverOverLink("2");
        tooltipText = moPage.getToolTipTitleOfLinkButton();
        Assert.assertEquals(tooltipText, "Link Button", "Tooltip title is not matching");
        WebElement linkButton = moPage.getElement1();
        moPage.clickOnLink(linkButton);
        clicksCount = moPage.getClickCount();
        Assert.assertEquals(clicksCount, 2, "Clicks count is not matching");
        extentTest.log(Status.INFO, "Completed testing MouseOver functionality");
    }

    @Test(testName = "test_nonBreakingSpace", priority = 5, description = "Test non-breaking space")
    public void test_nonBreakingSpace() {
        extentTest.log(Status.INFO, "Testing NonBreaking Space test case");
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(3));
        Assert.assertEquals(driver.getTitle(), links.get(3), "Title is not matching");

        NonBreakingSpacePage nbPage = new NonBreakingSpacePage(driver);
        nbPage.clickOnButton();
        extentTest.log(Status.INFO, "Completed testing NonBreaking space functionality");
    }
}
