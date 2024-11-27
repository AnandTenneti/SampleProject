package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NonBreakingSpacePage {
    WebDriver driver;

    public NonBreakingSpacePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[text()='My\u00a0Button']")
    private WebElement myButton;

    public void clickOnButton() {
        myButton.click();
    }

    public boolean isButtonDisplayed() {
        return myButton.isDisplayed();
    }
}
