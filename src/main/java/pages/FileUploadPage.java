package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class FileUploadPage {
    WebDriver driver;
    JavascriptExecutor js;

    public FileUploadPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "iframe[src='/static/upload.html']")
    private WebElement uploadFrame;

    @FindBy(xpath = "//input[@type='file']")
    private WebElement browseButton;

    @FindBy(css = "div.success-file>p")
    private WebElement selectedFilesMessage;

    @FindBy(css = "div.file-info")
    private List<WebElement> fileName;

    public void switchToCurrentFrame() {
        driver.switchTo().frame(uploadFrame);
    }


    public void waitForAjaxDataToBeDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#content>p" +
                ".bg-success")));
    }

    public void unHideUploadButton() {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.display='block;'", browseButton);
    }

    public File[] getListOfFiles(String filePath) {
        File folder = new File(filePath);
        File[] listOfFiles = folder.listFiles();
        return listOfFiles;
    }

    public void uploadFile(String actualfilePath) {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = '';", browseButton);
        browseButton.sendKeys(actualfilePath);
    }

}
