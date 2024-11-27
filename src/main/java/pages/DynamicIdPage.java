package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DynamicIdPage {
    WebDriver driver;

    public DynamicIdPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "button[id][class='btn btn-primary']")
    private WebElement button;

  public void clickOnButtonWithoutUsingDynamicId() {
      button.click();
  }




}
