package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TextInputPage {
    WebDriver driver;

    public TextInputPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "newButtonName")
    private WebElement myButtonValue;

    @FindBy(id = "updatingButton")
    private WebElement button;

    public void setNewButtonName(String newName) {
        myButtonValue.clear();
        myButtonValue.sendKeys(newName);
    }

    public void clickOnButton() {
        button.click();
    }


    public String getButtonText() {
        return button.getText();
    }



}
