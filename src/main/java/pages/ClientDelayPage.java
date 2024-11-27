package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ClientDelayPage {
    WebDriver driver;

    public ClientDelayPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "ajaxButton")
    private WebElement button;

    @FindBy(css = "#content>p.bg-success")
    private WebElement labelMessage;


    public void clickOnButton() {
        button.click();
    }

    public void waitForLabelToBeDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.attributeContains(By.id("spinner"), "style", "display: none;"));
    }

    public boolean isLabelDisplayed() {
        return labelMessage.isDisplayed();
    }

    public String getLabelMessage() {
        return labelMessage.getText();
    }


}
