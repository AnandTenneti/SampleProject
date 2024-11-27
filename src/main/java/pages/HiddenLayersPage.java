package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HiddenLayersPage {
    WebDriver driver;

    public HiddenLayersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "greenButton")
    private WebElement greenButton;

    @FindBy(id = "blueButton")
    private WebElement blueButton;

    public void clickOnGreenButton() {
        greenButton.click();
    }

    public void clickOnBlueButton() {
        blueButton.click();
    }


    public String getCssValueOfElement() {
        int size = driver.findElements(By.cssSelector("div[class='spa-view']")).size();
        return driver.findElements(By.cssSelector("div[class='spa-view']")).get(size-1)
                .getCssValue("z-index");
    }

    public boolean isGreenButtonDisplayed() {
        try {
            greenButton.isDisplayed();
        } catch (NoSuchElementException nse) {
            return false;
        }
        return true;
    }
}
