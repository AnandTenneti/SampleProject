package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProgressBarPage {
    WebDriver driver;


    public ProgressBarPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "startButton")
    private WebElement startButton;

    @FindBy(id = "stopButton")
    private WebElement stopButton;

    @FindBy(id = "progressBar")
    private WebElement progressBar;

    @FindBy(id = "result")
    private WebElement result;

    public void clickOnStartButton() {
        startButton.click();
    }
    public void clickOnStopButton() {
        stopButton.click();
    }

    public String getResultDetails() {
        return result.getText();
    }
    public String getProgressBarValue() {
        return progressBar.getAttribute("aria-valuenow");
    }
}
