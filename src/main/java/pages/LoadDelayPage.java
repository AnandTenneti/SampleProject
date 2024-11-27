package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoadDelayPage {
    WebDriver driver;

    public LoadDelayPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Load Delay")
    private WebElement ajaxButton;

    @FindBy(css = ".btn.btn-primary")
    private WebElement button;

    public void clickOnButton() {
        button.click();
    }

    public boolean isButtonDisplayed() {
        return button.isDisplayed();
    }

    public void waitForPageToBeLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button" +
                ".btn-primary")));
    }


}
