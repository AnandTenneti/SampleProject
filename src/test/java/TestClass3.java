import elements.HomePage;
import elements.ProgressBarPage;
import elements.ScrollbarsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.awt.Toolkit;
import java.util.concurrent.TimeUnit;

public class TestClass3 extends BaseTest {
    ArrayList<String> links = new ArrayList<String>(
            Arrays.asList("Scrollbars", "Dynamic Table", "Verify Text", "Progress Bar"));

    @Test(priority = 1)
    public void test_Scrollbar() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(0));
        Assert.assertEquals(driver.getTitle(), links.get(0), "Title is not matching");
        ScrollbarsPage scrollPage = new ScrollbarsPage(driver);
        scrollPage.scrollElementIntoView();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        scrollPage.clickOnButton();

        //Launch the application
//        driver.get("http://uitestingplayground.com/scrollbars");
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//
//        //Locating element by id and store in variable "Element
//        WebElement Element = driver.findElement(By.id("hidingButton"));
//
//        // Scrolling down the page till the element is found
//
////            js.executeScript("document.querySelector(\"div[style='height:150px;overflow-y: scroll;" +
////                    "width:300px;overflow-x:scroll']\").scrollTop=100\n");
////            Thread.sleep(2000);
////            js.executeScript("document.querySelector(\"div[style='height:150px;overflow-y: scroll;" +
////                    "width:300px;overflow-x:scroll']\").scrollLeft=100\n");
//
//        js.executeScript("document.querySelector(\"button[id='hidingButton']\").scrollIntoView(true)");
//        Element.click();
//          Thread.sleep(5000);

    }

    @Test(priority = 4)
    public void test_progressBar() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(3));
        Assert.assertEquals(driver.getTitle(), links.get(3), "Page title is not matching");
        ProgressBarPage pbPage = new ProgressBarPage(driver);
        String resultDetails = pbPage.getResultDetails();
        Assert.assertEquals(resultDetails, "Result: n/a", "Result details is not matching");
        int progressBarValue = Integer.parseInt(pbPage.getProgressBarValue());
        pbPage.clickOnStartButton();
        do {
            progressBarValue = Integer.parseInt(pbPage.getProgressBarValue());
        } while (progressBarValue != 75);
        pbPage.clickOnStopButton();
        resultDetails = pbPage.getResultDetails();
        Assert.assertTrue(resultDetails.contains("Result: 0"));
    }

    @Test(priority = 1)
    public void test_DynamicTable() throws InterruptedException {
        driver.get("http://uitestingplayground.com/dynamictable");
        driver.manage().window().maximize();
        Thread.sleep(5000);
        List<WebElement> rowElements = driver.findElements(By.xpath
                ("//div[@aria-label='Tasks']/div[3]/div"));
        int rowsCount =
                driver.findElements(By.xpath("//div[@aria-label='Tasks']/div[@role='rowgroup']/div")).size();
        System.out.println("The row count is " + rowsCount);
        List<WebElement> columnElements = driver.findElements(By.xpath
                ("//div[@aria-label='Tasks']/div[@role='rowgroup']//span[@role='columnheader']"));
        int columnCount =
                driver.findElements(By.xpath("//div[@aria-label='Tasks']/div[@role='rowgroup']//span[@role='columnheader']")).size();
        System.out.println("The column   count is " + columnCount);

        for (int i = 1; i <= rowsCount - 1; i++) {
            // System.out.println(driver.findElement(By.xpath
            // ("//div[@aria-label='Tasks']/div[@role='rowgroup']/div[\" + i +\n" +
            //          "                                                \"]")).getText());

            for (int j = 1; j <= columnCount; j++) {
                String value =
                        driver.findElement(
                                By.xpath
                                        ("//div[@aria-label='Tasks']/div[@role='rowgroup']/div[" + i + "]//span[@role='cell'][" + j + "]")).getText();

                String cpuValue = driver.findElement(By.xpath("//div[@aria-label='Tasks']/div" +
                        "[@role='rowgroup']/div[\" + i + \"]//span[@role='cell'][\" + j + " +
                        "\"]//following-sibling::span[contains(text(),'%')]")).getText();
                if (value.equalsIgnoreCase("Chrome")) {
                    System.out.print(value + "\t" + cpuValue + "\t");
                    break;
                }
            }
            System.out.println();
        }
    }

    @Test(priority = 2)
    public void test_shadowDOM() throws Exception {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        driver.get("http://uitestingplayground.com/shadowdom");
        Thread.sleep(3000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement inputField = (WebElement) js.executeScript("return document.querySelector" +
                "('guid-generator')" +
                ".shadowRoot" +
                ".querySelector('input')");
        System.out.println("Before clicking button" + inputField.getText());
        WebElement buttonField1 = (WebElement) js.executeScript("return document.querySelector" +
                "('guid-generator').shadowRoot.querySelector('button#buttonGenerate')");
        js.executeScript("arguments[0].click();", buttonField1);
        Thread.sleep(3000);
        String inputFieldValue =
                (String) js.executeScript("return document.querySelector('guid-generator').shadowRoot.querySelector('input').value");
        System.out.println("After clicking button" + inputFieldValue);
        WebElement buttonField2 = (WebElement) js.executeScript("return document.querySelector" +
                "('guid-generator').shadowRoot.querySelector('button#buttonCopy')");
        js.executeScript("arguments[0].click();", buttonField2);
        inputField.sendKeys(Keys.COMMAND + "a" + Keys.COMMAND + Keys.COMMAND + "c" + Keys.COMMAND);
        Thread.sleep(5000);
        String actualCopedText = (String) Toolkit.getDefaultToolkit()
                .getSystemClipboard().getData(DataFlavor.stringFlavor);
        Assert.assertEquals(inputFieldValue, actualCopedText, "The UIds ade not matching");
        System.out.println(actualCopedText);
        Thread.sleep(5000);
    }

    @Test(priority = 3)
    public void test_overlappedElement() throws InterruptedException {
        driver.get("http://uitestingplayground.com/overlapped");
        Thread.sleep(3000);
        driver.findElement(By.id
                ("id")).sendKeys("123a");
        Thread.sleep(3000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("document.querySelector(\"div[style='overflow-y: scroll; height:100px;" +
        //      "']\").scrollBy(0,200);");
        js.executeScript("document.querySelector(\"input[id='name']\").scrollIntoView" +
                "(true)");
        js.executeScript("arguments[0].value='AnandKiranTenneti';",
                driver.findElement(By.id("name")));
        // String inputFieldValue =
        //       (String) js.executeScript("return document.querySelector('input').shadowRoot" +
        //             ".querySelector('div').value");
        //System.out.println(inputFieldValue);
        String name =
                driver.findElement(By.cssSelector("div[style='display: block !important;'][0]")).getText();
        System.out.println("Your name entered is " + name);


        //Assert.assertEquals(driver.findElement(By.id("name")).getText(), "AnandKiranTenneti",
        //    "Provided name is " +
        //  "not matching");
        Thread.sleep(5000);

    }


    @Test(priority = 5)
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

        File uploadFile = new File("/Users/tennetikiran/Downloads/WhatsApp Image 2024-09-29 at 9.22.24 PM.jpeg");

        WebElement fileInput = driver.findElement(By.cssSelector("input[type=file]"));
        fileInput.sendKeys(uploadFile.getAbsolutePath());
        //File uploadFile = new File("src/test/resources/test1.txt");
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

    @Test(priority = 6)
    public void test_animatedButton() throws InterruptedException {

        //Launch the application
        driver.get("http://uitestingplayground.com/animation");


        //Locating element by id and store in variable "Element
        WebElement startAnimationButton = driver.findElement(By.id("animationButton"));
        startAnimationButton.click();
        String animationStatus = driver.findElement(By.id("opstatus")).getText();
        Assert.assertEquals(animationStatus,
                "Animating the button...", "Messages are not matching");
        Thread.sleep(5000);
        animationStatus = driver.findElement(By.id("opstatus")).getText();
        Assert.assertEquals(animationStatus,
                "Animation done", "Messages are not matching");


        Thread.sleep(3000);

    }

    @Test
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
