package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ClassAttributePage {
    WebDriver driver;

    public ClassAttributePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[contains(concat(' ',normalize-space(@class),' '),'btn-primary')]")
    private WebElement button;

    public void clickOnButton() {
        button.click();
    }
}
