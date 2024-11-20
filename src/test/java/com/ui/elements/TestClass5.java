package com.ui.elements;

import pages.AlertsPage;
import pages.FileUploadPage;
import pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ShadowDOMPage;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class TestClass5 extends BaseTest {


    ArrayList<String> links = new ArrayList<String>(
            Arrays.asList("Overlapped Element", "Shadow DOM", "Alerts", "File Upload"));

    @Test(priority = 1)
    public void test_overlappedElement() {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(0));
        Assert.assertEquals(driver.getTitle(), links.get(0), "Page Title is not matching");
    }

    @Test(priority = 2)//Done
    public void test_shadowDOM() throws InterruptedException, IOException, UnsupportedFlavorException {
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
        Assert.assertEquals(inputFieldValue, actualCopiedText, "The UIds ade not matching");
    }

    @Test(priority = 3)
    public void test_Alerts() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(2));
        Assert.assertEquals(driver.getTitle(), "Alerts", "Title is not matching");
        AlertsPage alertPage = new AlertsPage(driver);
        alertPage.clickOnAlertButton();
        alertPage.waitUntilAlertDialogIsDisplayed();
        String simpleAlertMessage = alertPage.verifyAlertTextMessage();
        String expectedAlertMessage = "Today is a working day.\nOr less likely a holiday.";
        Assert.assertEquals(simpleAlertMessage, expectedAlertMessage, "Alert Messages are not " +
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
        //Prompt Alert
        alertPage.clickOnPromptAlertButton();
        alertPage.waitUntilAlertDialogIsDisplayed();
        alertPage.enterTextIntoAlert("dogs");
        alertPage.acceptOrdismiss("No");
        alertPage.waitUntilAlertDialogIsDisplayed();
        String promptAlertMessage = alertPage.verifyAlertTextMessage();
        System.out.println(promptAlertMessage);
        alertPage.acceptOrdismiss("Yes");
    }

    //Upload a file using sendKeys method
    @Test(priority = 4)
    public void test_fileUpload() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(3));
        Assert.assertEquals(driver.getTitle(), links.get(3), "Page Title is not matching");
        FileUploadPage fpage = new FileUploadPage(driver);
        fpage.switchToCurrentFrame();
        String filePath = System.getProperty("user.dir") + "/src/test/resources/test1.txt";
        System.out.println(filePath);
        fpage.uploadFile(filePath);
    }

    @Test(priority = 4)
    public void test_multiplefileUpload() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(3));
        Assert.assertEquals(driver.getTitle(), links.get(3), "Page Title is not matching");
        FileUploadPage fpage = new FileUploadPage(driver);
        fpage.switchToCurrentFrame();
        String folderPath = System.getProperty("user.dir") + "/src/test/resources/testfiles/";
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
        Thread.sleep(5000);
    }

}
