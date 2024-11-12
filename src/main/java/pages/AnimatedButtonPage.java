package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AnimatedButtonPage {
    WebDriver driver;

    public AnimatedButtonPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "animationButton")
    private WebElement startAnimationButton;

    @FindBy(id = "movingTarget")
    private WebElement movingTargetButton;

    @FindBy(id = "opstatus")
    private WebElement opstatus;

    public void clickOnStartAnimationButton() {
        startAnimationButton.click();
    }

    public void clickOnMovingTargetButton() {
        movingTargetButton.click();
    }

    public String getMessageDetails() {
        return opstatus.getText();
    }

    public void waitForSpinElementNotToBeDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(movingTargetButton
                , "class", "spin")));
    }

    public String getClassNameOfMovingTargetButton() {
        return movingTargetButton.getAttribute("class");
    }



}
