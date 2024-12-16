package com.ui.elements;

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

    @Test(priority = 1, groups = "smoke")
    public void test_visibility() throws InterruptedException {
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
    }

    /***
     * In the sampleApp, we are verifying login success functionality
     * @param username can use any of the alphanumeric values
     *                 and the password value is constant
     */
    @Test(priority = 2, dataProvider = "loginTestData", dataProviderClass = DataSupplier.class,
            groups = "validation")
    public void test_sampleApp(String username) {
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
    }

    /***
     * In the sampleApp, we are verifying login success functionality
     * @param username can use any of the alphanumeric values
     * @param password value is constant
     */

    @Test(priority = 3, dataProvider = "excelLoginData", dataProviderClass =
            ExcelDataSupplier.class)
    public void test_sampleApp_using_excelData(String username, String password) {
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
    }

    @Test(priority = 4)
    public void test_mouseOver() throws InterruptedException {
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
    }

    @Test(priority = 5)
    public void test_nonBreakingSpace() {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(3));
        Assert.assertEquals(driver.getTitle(), links.get(3), "Title is not matching");

        NonBreakingSpacePage nbPage = new NonBreakingSpacePage(driver);
        nbPage.clickOnButton();
    }
}
