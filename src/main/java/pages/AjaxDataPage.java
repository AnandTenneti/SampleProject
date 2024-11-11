package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AjaxDataPage {
    WebDriver driver;

    public AjaxDataPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "ajaxButton")
    private WebElement ajaxButton;

    @FindBy(css = "div#content>p.bg-success")
    private WebElement loadedData;

    public void clickOnAjaxDataButton() {
        ajaxButton.click();
    }

    public String getAjaxLoadedData() {
        return loadedData.getText();
    }

    public void waitForAjaxDataToBeDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#content>p" +
                ".bg-success")));
    }


}
