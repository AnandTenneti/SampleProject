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

    @Test(priority = 1)//Done
    public void test_ajaxData() throws InterruptedException {
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

    @Test(priority = 2)//Done
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

    @Test(priority = 3)
    public void test_Click() {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(2));
        Assert.assertEquals(driver.getTitle(), links.get(2), "Page Title is not matching");
        // TODO

    }

    @Test(priority = 4)//Done
    public void test_TextInput() {
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
    }


    @Test(priority = 5, enabled = false)
    public void test_fileUpload() throws InterruptedException {
//        driver.get("http://uitestingplayground.com/upload");
//        Thread.sleep(5000);
//        File uploadFile = new File("/Users/tennetikiran/Downloads/MI_Policy.pdf");
//
//        WebElement fileInput = driver.findElement(By.id("browse"));
//        fileInput.click();
//        Thread.sleep(5000);

        // driver.get("https://the-internet.herokuapp.com/upload");
        driver.get("http://uitestingplayground.com/upload");
        Thread.sleep(5000);
        File uploadFile = new File("src/test/resources/test1.txt");
        //  JavascriptExecutor js = (JavascriptExecutor) driver;
        //  js.executeScript("document.getElementById('browse').style.visibility='visible'");

        driver.findElement(By.cssSelector("label.browse-btn")).click();
        //fileInput.sendKeys(uploadFile.getAbsolutePath());
        Thread.sleep(10000);
//        driver.findElement(By.id("file-submit")).click();
//
//        WebElement fileName = driver.findElement(By.id("uploaded-files"));
//        Assert.assertEquals("test1.txt", fileName.getText());
    }


    @Test(enabled = false)
    public void ByVisibleElement() throws InterruptedException {

        //Launch the application
        driver.get("http://uitestingplayground.com/scrollbars");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        //Locating element by id and store in variable "Element
        WebElement Element = driver.findElement(By.id("hidingButton"));

        // Scrolling down the page till the element is found

//            js.executeScript("document.querySelector(\"div[style='height:150px;overflow-y: scroll;" +
//                    "width:300px;overflow-x:scroll']\").scrollTop=100\n");
//            Thread.sleep(2000);
//            js.executeScript("document.querySelector(\"div[style='height:150px;overflow-y: scroll;" +
//                    "width:300px;overflow-x:scroll']\").scrollLeft=100\n");

        js.executeScript("document.querySelector(\"button[id='hidingButton']\").scrollIntoView(true)");
        Element.click();
        Thread.sleep(5000);
    }

}
