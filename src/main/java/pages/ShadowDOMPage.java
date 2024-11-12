package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.Duration;

public class ShadowDOMPage {
    WebDriver driver;
    JavascriptExecutor js;

    public ShadowDOMPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "alertButton")
    private WebElement alertButton;

    @FindBy(id = "confirmButton")
    private WebElement confirmButton;

    @FindBy(id = "promptButton")
    private WebElement promptButton;

    public void copyTextIntoClipboard() {
        js = (JavascriptExecutor) driver;
        WebElement inputField = (WebElement) js.executeScript("return document.querySelector" +
                "('guid-generator')" +
                ".shadowRoot" +
                ".querySelector('input')");
        inputField.sendKeys(Keys.COMMAND + "a" + Keys.COMMAND + Keys.COMMAND + "c" +
                Keys.COMMAND);
    }

    public String getCopiedTextFromClipboars() throws IOException, UnsupportedFlavorException {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        String copiedValue = (String) Toolkit.getDefaultToolkit()
                .getSystemClipboard().getData(DataFlavor.stringFlavor);
        return copiedValue;

    }

    public String getInputFieldText() {
        js = (JavascriptExecutor) driver;
        WebElement inputField = (WebElement) js.executeScript("return document.querySelector" +
                "('guid-generator')" +
                ".shadowRoot" +
                ".querySelector('input')");
        return inputField.getText();
    }

    public void clickOnButtonField() {
        js = (JavascriptExecutor) driver;
        WebElement buttonField1 = (WebElement) js.executeScript("return document.querySelector" +
                "('guid-generator').shadowRoot.querySelector('button#buttonGenerate')");
        js.executeScript("arguments[0].click();", buttonField1);
    }

    public String getValueInInputField() {
        js = (JavascriptExecutor) driver;
        String inputFieldValue =
                (String) js
                        .executeScript("return document.querySelector('guid-generator')" +
                                ".shadowRoot.querySelector('input').value");
        return inputFieldValue;
    }
}
