import pages.HomePage;
import pages.ProgressBarPage;
import pages.ScrollbarsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;

public class TestClass3 extends BaseTest {
    ArrayList<String> links = new ArrayList<String>(
            Arrays.asList("Scrollbars", "Dynamic Table", "Verify Text", "Progress Bar"));

    @Test(priority = 1)
    public void test_Scrollbar() {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(0));
        Assert.assertEquals(driver.getTitle(), links.get(0), "Title is not matching");
        ScrollbarsPage scrollPage = new ScrollbarsPage(driver);
        scrollPage.scrollElementIntoView();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        scrollPage.clickOnButton();
    }

    @Test(priority = 2)
    public void test_dynamicTable() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(1));
        Assert.assertEquals(driver.getTitle(), links.get(1), "Page Title is not matching");
        ScrollbarsPage scrollPage = new ScrollbarsPage(driver);
        scrollPage.scrollElementIntoView();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        scrollPage.clickOnButton();
    }

    @Test(priority = 3)
    public void test_verifyText() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLink(links.get(2));
        Assert.assertEquals(driver.getTitle(), links.get(2), "Page Title is not matching");
        ScrollbarsPage scrollPage = new ScrollbarsPage(driver);
        scrollPage.scrollElementIntoView();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        scrollPage.clickOnButton();
    }

    @Test(priority = 4)
    public void test_progressBar() {
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
}
