package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertsPage {
    WebDriver driver;
    Alert alert;

    public AlertsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "alertButton")
    private WebElement alertButton;

    @FindBy(id = "confirmButton")
    private WebElement confirmButton;

    @FindBy(id = "promptButton")
    private WebElement promptButton;


    public void clickOnAlertButton() {
        alertButton.click();
    }

    public String verifyAlertTextMessage() {
        alert = driver.switchTo().alert();
        return alert.getText();
    }

    public void clickOnOkButton() {
        alert.accept();
    }

    public void clickOnConfirmAlertButton() {
        confirmButton.click();
    }

    public void clickOnPromptAlertButton() {
        promptButton.click();
    }

    public void enterTextIntoAlert(String inputText) {
        alert.sendKeys(inputText);
    }
    public void waitUntilAlertDialogIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public void acceptOrdismiss(String choice) {
        switch (choice) {
            case "Yes":
                alert.accept();
                break;
            case "No":
                alert.dismiss();
                break;
        }

    }


}
