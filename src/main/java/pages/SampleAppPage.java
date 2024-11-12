package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SampleAppPage {
    WebDriver driver;

    public SampleAppPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "UserName")
    private WebElement usernameField;

    @FindBy(name = "Password")
    private WebElement passwordField;

    @FindBy(id = "login")
    private WebElement loginButton;

    @FindBy(id = "loginstatus")
    private WebElement loginStatus;

    public void setUsername(String username) {

        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void setPassword() {
        passwordField.sendKeys("pwd");
    }

    public void clickOnLoginButton() {
        loginButton.click();
    }


    public String getUsernameText() {
        return usernameField.getText();
    }

    public String getLoginButtonText() {
        return loginButton.getText();
    }

    public String getLoginStatusText() {
        return loginStatus.getText();
    }


}
