package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class VisibilityPage {
    WebDriver driver;
    public VisibilityPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(id="hideButton")
    private WebElement hideButton;

    @FindBy(id="removedButton")
    private List<WebElement> removedButton;

    @FindBy(id="zeroWidthButton")
    private WebElement zeroWidthButton;

    @FindBy(id="overlappedButton")
    private WebElement overlappedButton;

    @FindBy(id="transparentButton")
    private WebElement opacityButton;

    @FindBy(id="invisibleButton")
    private WebElement visibilityHiddenButton;

    @FindBy(id="offscreenButton")
    private WebElement offscreenButton;

    @FindBy(css="table>tbody>tr>td>button")
    private List<WebElement> buttons;

    @FindBy(id="notdisplayedButton")
    private WebElement notDisplayedButton;




    public void clickOnHideButton() {
        hideButton.click();
    }
    public int areButtonsDisplayed() {
        return buttons.size();
    }

    public boolean verifyButtonNotDisplayed() {
        boolean displayStatus = notDisplayedButton.getAttribute("style").equalsIgnoreCase(
                "display: none;");
        //System.out.println(displayStatus);
        boolean opacityStatus= opacityButton.getAttribute("style")
                .equalsIgnoreCase("opacity: 0;");
        boolean invisibilityStatus =
                visibilityHiddenButton.getAttribute("style").equalsIgnoreCase("visibility: " +
                        "hidden;");
        //System.out.println(opacityStatus);
        boolean zeroWidthStatus = zeroWidthButton.getAttribute("class").contains("zerowidth");
        boolean removedButtonStatus = removedButton.size()<1;
        boolean overlappedButtonStatus =
                driver.findElement(By.id("hidingLayer")).getAttribute("style").length()>0;
        System.out.println(overlappedButtonStatus);
        System.out.println(driver.findElement(By.id("hidingLayer")).getAttribute("style").length());
        boolean status = displayStatus || opacityStatus ||
                invisibilityStatus||zeroWidthStatus||opacityStatus||
                removedButtonStatus||overlappedButtonStatus;
        return status;
    }

}
