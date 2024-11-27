package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MouseOverPage {
    WebDriver driver;
    Actions actions;

    public MouseOverPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Click me")
    private WebElement clickMeLink;

    @FindBy(linkText = "Link Button")
    private WebElement linkButtonLink;

    @FindBy(css = "span[id='clickCount']")
    private WebElement clickCount;

    public void hoverOverLink(String choice) {
        actions = new Actions(driver);
        switch (choice) {
            case "1":
                actions.moveToElement(clickMeLink).perform();
                break;
            case "2":
                actions.moveToElement(linkButtonLink).perform();
                break;
        }
    }

    public String getToolTipTitleOfClickMe() {
        return clickMeLink.getAttribute("title");
    }

    public String getToolTipTitleOfLinkButton() {
        return linkButtonLink.getAttribute("title");
    }


    public void clickOnClickMeLink() {
        clickMeLink.click();
    }

    public void clickOnLinkButtonLink() {
        linkButtonLink.click();
    }

    public int getClickCount() {
        return Integer.parseInt(clickCount.getText());
    }

    public WebElement getElement() {
        return clickMeLink;
    }

    public WebElement getElement1() {
        return linkButtonLink;
    }

    public void clickOnLink(WebElement element) {
        for (int i = 0; i < 2; i++) {
            element.click();
        }
    }

}
