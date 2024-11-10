package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ScrollbarsPage {
    WebDriver driver;
    JavascriptExecutor js;

    public ScrollbarsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "button[id='hidingButton']")
    private WebElement hidingButton;


    public void scrollElementIntoView() {
        js = (JavascriptExecutor) driver;
        js.executeScript
                ("document.querySelector(\"button[id='hidingButton']\").scrollIntoView(true)");
    }

    public void clickOnButton() {
        hidingButton.click();
    }
}
