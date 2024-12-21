package com.ui.elements;

import com.aventstack.extentreports.Status;
import pages.AlertsPage;
import pages.FileUploadPage;
import pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ShadowDOMPage;

import java.awt.*;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.dataprovider.TestData.PATH_TO_TEST_FILES_DIR;
import static com.dataprovider.TestData.USER_DIR;

public class UiElementsTest5 extends BaseTest {


    ArrayList<String> links = new ArrayList<String>(
            Arrays.asList("Overlapped Element", "Shadow DOM", "Alerts", "File Upload"));

    @Test(priority = 1, description = "overlappedElement", enabled = false)
    public void test_overlappedElement() {
        extentTest.log(Status.INFO,"Testing overlappedElement test case");
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(0));
        Assert.assertEquals(driver.getTitle(), links.get(0), "Page Title is not matching");
        //TODO
        extentTest.log(Status.INFO,"Completed testing overlappedElement functionality");
    }

    @Test(priority = 2, description = "shadowDOM", enabled = false)
    public void test_shadowDOM() throws InterruptedException, IOException, UnsupportedFlavorException {
        extentTest.log(Status.INFO,"Testing Shadow DOM test case");
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(1));
        Assert.assertEquals(driver.getTitle(), links.get(1), "Page Title is not matching");
        ShadowDOMPage sdPage = new ShadowDOMPage(driver);
        String inputFieldTextValue = sdPage.getInputFieldText();
        sdPage.clickOnButtonField();
        String inputFieldValue = sdPage.getValueInInputField();
        System.out.println("After clicking button" + inputFieldValue);
        sdPage.copyTextIntoClipboard();
        String actualCopiedText = sdPage.getCopiedTextFromClipboars();
        System.out.println("Actual copied text is " + actualCopiedText);
        Assert.assertEquals(inputFieldValue, actualCopiedText, "The UIds ade not matching");
        extentTest.log(Status.INFO,"Completed testing Shadow DOM functionality");
    }

    @Test(priority = 3, description = "alerts")
    public void test_Alerts() throws InterruptedException {
        extentTest.log(Status.INFO,"Testing dynamic Id test case");
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(2));
        Assert.assertEquals(driver.getTitle(), "Alerts", "Title is not matching");
        AlertsPage alertPage = new AlertsPage(driver);
        extentTest.log(Status.INFO,"Testing Simple alert functionality");
        alertPage.clickOnAlertButton();
        alertPage.waitUntilAlertDialogIsDisplayed();
        String simpleAlertMessage = alertPage.verifyAlertTextMessage();
        String expectedAlertMessage = "Today is a working day.\nOr less likely a holiday.";
        Assert.assertEquals(simpleAlertMessage, expectedAlertMessage, "Alert Messages are not " +
                "matching");
        alertPage.clickOnOkButton();
        // Confirm Alert
        extentTest.log(Status.INFO,"Testing Confirm alert functionality");
        alertPage.clickOnConfirmAlertButton();
        alertPage.waitUntilAlertDialogIsDisplayed();
        String confirmAlertMessage = alertPage.verifyAlertTextMessage();
        System.out.println(confirmAlertMessage);
        alertPage.clickOnOkButton();
        alertPage.waitUntilAlertDialogIsDisplayed();
        confirmAlertMessage = alertPage.verifyAlertTextMessage();
        System.out.println(confirmAlertMessage);
        alertPage.clickOnOkButton();
        //Prompt Alert
        extentTest.log(Status.INFO,"Testing Prompt alert functionality");
        alertPage.clickOnPromptAlertButton();
        alertPage.waitUntilAlertDialogIsDisplayed();
        alertPage.enterTextIntoAlert("dogs");
        alertPage.acceptOrdismiss("No");
        alertPage.waitUntilAlertDialogIsDisplayed();
        String promptAlertMessage = alertPage.verifyAlertTextMessage();
        System.out.println(promptAlertMessage);
        alertPage.acceptOrdismiss("Yes");
        extentTest.log(Status.INFO,"Completed testing alert functionalities");
    }

    @Test(priority = 4, description = "uploadMultipleFiles")
    public void test_multipleFileUpload() throws InterruptedException {
        extentTest.log(Status.INFO,"Testing file upload test case");
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(3));
        Assert.assertEquals(driver.getTitle(), links.get(3), "Page Title is not matching");
        FileUploadPage fpage = new FileUploadPage(driver);
        fpage.switchToCurrentFrame();
        extentTest.log(Status.INFO,"Providing path to the testfiles folder");
        String folderPath = USER_DIR + PATH_TO_TEST_FILES_DIR;
        File[] listOfFilesInGivenFolder = fpage.getListOfFiles(folderPath);
        if (listOfFilesInGivenFolder != null) {
            for (int i = 0; i < listOfFilesInGivenFolder.length; i++) {
                if (listOfFilesInGivenFolder[i].isFile()) {
                    fpage.uploadFile(listOfFilesInGivenFolder[i].getAbsolutePath());
                } else {
                    continue;
                }
            }
        }
        extentTest.log(Status.INFO,"Completed testing file upload functionality");
    }
}
