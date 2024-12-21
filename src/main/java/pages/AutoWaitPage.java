package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AutoWaitPage {
    WebDriver driver;

    public AutoWaitPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "applyButton3")
    private WebElement applyButton3s;

    @FindBy(id = "applyButton5")
    private WebElement applyButton5s;

    @FindBy(id = "applyButton10")
    private WebElement applyButton10s;

    @FindBy(id = "element-type")
    private WebElement elementType;

    @FindBy(id = "target")
    private WebElement targetElement;

    @FindBy(xpath = "//ul/li[@class='form-check']/input")
    private List<WebElement> checkBoxList;

    @FindBy(id = "opstatus")
    private WebElement status;


    public void selectElementType(String elementText) {
        Select select = new Select(elementType);
        select.selectByVisibleText(elementText);
    }

    public String getTagName() {
        return targetElement.getTagName();
    }

    public boolean isElementDisplayed(String tagName) {
        System.out.println(tagName + "#target");
        return driver.findElement(By.cssSelector("#target")).isDisplayed();
    }

    public void actionOnElement() {
        driver.findElement(By.cssSelector("#target")).sendKeys("Hello , input box");
    }

    public void actionOnElement(String elementType) {
        switch (elementType) {
            case "input":
                driver.findElement(By.cssSelector("#target")).sendKeys("Hello");
                break;
            case "select":
                Select select = new Select(driver.findElement(By.cssSelector("#target")));
                select.selectByIndex(1);
                break;
            case "button":
                driver.findElement(By.cssSelector("#target")).click();
                break;
        }
    }

    public void clickOnApplyButton(int choice) {
        switch (choice) {
            case 1:
                applyButton3s.click();
                break;
            case 2:
                applyButton5s.click();
                break;
            case 3:
                applyButton10s.click();
                break;
        }
    }

    public void deselectElementProperty(String elementAttribute) {
        for (WebElement elementProperty : checkBoxList) {
            if (elementProperty.getAttribute("id").equalsIgnoreCase(elementAttribute)) {
                System.out.println("element property is " + elementProperty.getAttribute("id"));
                elementProperty.click();
                break;
            }
        }
    }

    public void deselectAllElementProperties() {
        for (WebElement elementProperty : checkBoxList) {
            elementProperty.click();

        }
    }

    public String getStatusMessage() {
        return status.getText();
    }


}
