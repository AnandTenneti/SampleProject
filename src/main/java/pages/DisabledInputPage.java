package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DisabledInputPage {
    WebDriver driver;

    public DisabledInputPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "inputField")
    private WebElement inputField;

    @FindBy(id = "enableButton")
    private WebElement enableButton;

    @FindBy(id = "opstatus")
    private WebElement statusField;

    public void enterInputValue(String textValue) {
        inputField.sendKeys(textValue);
    }

    public boolean isInputFieldEnabled() {
        return inputField.isEnabled();
    }
    public void clickOnEnableButton() {
        enableButton.click();
    }

    public String getStatus() {
        return statusField.getText();
    }

    public void waitForPageToBeLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button" +
                ".btn-primary")));
    }


}
