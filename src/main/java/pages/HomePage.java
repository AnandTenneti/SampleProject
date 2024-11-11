package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = "a[href='/dynamicid']")
    private WebElement dynamicIdLink;

    @FindBy(css = "a[href='/classattr']")
    private WebElement classAttribute;

    @FindBy(css = "a[href='/hiddenlayers']")
    private WebElement hiddenLayers;

    @FindBy(css = "a[href='/loaddelay']")
    private WebElement loadDelay;

    @FindBy(css = "a[href='/ajax']")
    private WebElement ajaxData;

    @FindBy(css = "a[href='/clientdelay']")
    private WebElement clientDelay;
    @FindBy(css = "a[href='/click']")
    private WebElement Click;
    @FindBy(css = "a[href='/textinput']")
    private WebElement textInput;
    @FindBy(css = "a[href='/scrollbars']")
    private WebElement scrollBars;
    @FindBy(css = "a[href='/dynamictable']")
    private WebElement dynamicTableLink;
    @FindBy(css = "a[href='/verifytext']")
    private WebElement verifyText;
    @FindBy(css = "a[href='/progressbar']")
    private WebElement progressBar;
    @FindBy(css = "a[href='/visibility']")
    private WebElement Visibility;

    @FindBy(css = "a[href='/sampleapp']")
    private WebElement sampleApp;

    @FindBy(css = "a[href='/mouseover']")
    private WebElement mouseOver;

    @FindBy(css = "a[href='/nbsp']")
    private WebElement nonBreakingSpace;

    @FindBy(css = "a[href='/overlapped']")
    private WebElement overlapped;

    @FindBy(css = "a[href='/shadowdom']")
    private WebElement shadowDom;

    @FindBy(css = "a[href='/alerts']")
    private WebElement Alerts;

    @FindBy(css = "a[href='/upload']")
    private WebElement fileUpload;

    @FindBy(css = "a[href='/animation']")
    private WebElement animatedButton;

    @FindBy(css = "a[href='/disabledinput']")
    private WebElement disabledInput;

    @FindBy(css = "a[href='/autowait']")
    private WebElement autoWait;


    public void clickOnDynamicId() {
        dynamicIdLink.click();
    }

    public void clickOnClassAttributeLink() {
        classAttribute.click();
    }

    public void clickOnNonBreakingSpaceLink() {
        nonBreakingSpace.click();
    }

    public void clickOnVisibilityLink() {
        Visibility.click();
    }

    public void clickOnHiddenLayersLink() {
        hiddenLayers.click();
    }

    public void clickOnAutoWaitLink() {
        autoWait.click();
    }

    public void clickOnDynamicTableLink() {
        dynamicTableLink.click();
    }

    public void clickOnLink(String selectedLinkText) {
        List<WebElement> links = driver.findElements(By.xpath("//h3//a[text()]"));
        for(WebElement link: links) {
            if(link.getText().equalsIgnoreCase(selectedLinkText)) {
                link.click();
                break;
            }
        }



    }

}
