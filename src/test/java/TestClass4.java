import elements.HomePage;
import elements.NonBreakingSpacePage;
import elements.SampleAppPage;
import elements.VisibilityPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class TestClass4 extends BaseTest {


    ArrayList<String> links = new ArrayList<String>(
            Arrays.asList("Visibility", "Sample App", "Mouse Over", "Non-Breaking Space"));

    @Test(priority = 1)//Done
    public void test_visibility() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(0));
        Assert.assertEquals(driver.getTitle(), links.get(0), "Title is not matching");
        VisibilityPage visibilityPage = new VisibilityPage(driver);
        int noOfElementsDisplayed = visibilityPage.areButtonsDisplayed();
        System.out.println(noOfElementsDisplayed);
        Assert.assertEquals(noOfElementsDisplayed > 1, true);
        visibilityPage.clickOnHideButton();
        Assert.assertEquals(visibilityPage.verifyButtonNotDisplayed(),
                true, "Element is displayed");
        int noOfElementsDisplayed_updated = visibilityPage.areButtonsDisplayed();
        System.out.println(noOfElementsDisplayed_updated);
        Assert.assertNotEquals(noOfElementsDisplayed, noOfElementsDisplayed_updated);
    }

    @Test(priority = 2)//Done
    public void test_sampleApp() {
        driver.get("http://uitestingplayground.com/");
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(1));
        Assert.assertEquals(driver.getTitle(), links.get(1), "Title is not matching");
        SampleAppPage samplePage = new SampleAppPage(driver);
        String loginButtonText = samplePage.getLoginButtonText();
        Assert.assertEquals(loginButtonText, "Log In", "Button text is not matching");
        String usernameValue = "Anand";
        samplePage.setUsername(usernameValue);
        samplePage.setPassword();
        samplePage.clickOnLoginButton();
        String loginMessage = samplePage.getLoginStatusText();
        String actualLoginMessage = "Welcome, " + usernameValue + "!";
        Assert.assertEquals(loginMessage, actualLoginMessage, "Both messages are not matching");
        loginButtonText = samplePage.getLoginButtonText();
        Assert.assertEquals(loginButtonText, "Log Out", "Button text is not matching");
        samplePage.clickOnLoginButton();
        String logoutMessage = samplePage.getLoginStatusText();
        Assert.assertEquals(logoutMessage, "User logged out.", "logout message is incorrect");
    }

    @Test(priority = 4)//Done
    public void test_nbsp() {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(3));
        Assert.assertEquals(driver.getTitle(), links.get(3), "Title is not matching");
        NonBreakingSpacePage nbPage = new NonBreakingSpacePage(driver);
        nbPage.clickOnButton();
    }

    @Test(priority = 3)
    public void test_progressBar() throws InterruptedException {
        driver.get("http://uitestingplayground.com/progressbar");
        String resultText = driver.findElement(By.id("result")).getText();
        System.out.println(resultText);
        Assert.assertEquals(resultText, "Result: n/a");
        driver.findElement(By.id("startButton")).click();
        String progressBarValue = driver.findElement(By.id("progressBar")).getAttribute("aria" +
                "-valuenow");
        int progressBarVal = Integer.parseInt(progressBarValue);
        do {
            progressBarValue = driver.findElement(By.id("progressBar")).getAttribute("aria" +
                    "-valuenow");
            progressBarVal = Integer.parseInt(progressBarValue);

        } while (progressBarVal != 75);
        if (progressBarVal == 75) {
            driver.findElement(By.id("stopButton")).click();
        }
        resultText = driver.findElement(By.id("result")).getText();
        Thread.sleep(5000);
        Assert.assertTrue(resultText.contains("Result: 0"));

    }


//    @Test(priority = 6)
//    public void test_Alerts() throws InterruptedException {
//        driver.get("http://uitestingplayground.com/alerts");
//        driver.findElement(By.id("alertButton")).click();
//        Alert simpleAlert = driver.switchTo().alert();
//        System.out.println(simpleAlert.getText());
//        simpleAlert.accept();
//        Thread.sleep(2000);
//        driver.findElement(By.id("confirmButton")).click();
//        Alert confirmAlert = driver.switchTo().alert();
//        System.out.println(confirmAlert.getText());
//        confirmAlert.accept();
//        Thread.sleep(2000);
//        Alert confirmAgainAlert = driver.switchTo().alert();
//        System.out.println(confirmAgainAlert.getText());
//        confirmAgainAlert.accept();
//        Thread.sleep(2000);
//        driver.findElement(By.id("promptButton")).click();
//        Alert promptAlert = driver.switchTo().alert();
//        promptAlert.sendKeys("dogs");
//        promptAlert.accept();
//        Thread.sleep(2000);
//        Alert promptAgainAlert = driver.switchTo().alert();
//        System.out.println(confirmAgainAlert.getText());
//        promptAgainAlert.accept();
//        Thread.sleep(2000);
////        Alert simpleAlert = driver.switchTo().alert();
////        System.out.println(simpleAlert.getText());
////        simpleAlert.accept();
////        Thread.sleep(2000);
//
//    }


}
