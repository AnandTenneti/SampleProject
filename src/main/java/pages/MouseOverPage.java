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
    private WebElement link1;

    @FindBy(linkText = "Link Button")
    private WebElement link2;

    @FindBy(css = "span[id='clickCount']")
    private WebElement clickCount;

    public void hoverOverLink(String choice) {
        actions = new Actions(driver);
        switch (choice) {
            case "1":
                actions.moveToElement(link1).perform();
                break;
            case "2":
                actions.moveToElement(link2).perform();
                break;
        }
    }

    public String getToolTipTitle() {
        return link1.getAttribute("title");
    }

    public String getToolTipTitle1() {
        return link2.getAttribute("title");
    }
    
    public void clickOnClickMeLink() {
        link1.click();
    }

    public void clickOnLinkButtonLink() {
        link2.click();
    }

    public int getClickCount() {
        return Integer.parseInt(clickCount.getText());
    }

}
