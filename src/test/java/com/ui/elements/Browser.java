package com.ui.elements;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

public class Browser {
    static WebDriver driver;
    public String browserName;

    public Browser(String browserName) {
        this.browserName = browserName;

        if (browserName.equalsIgnoreCase("chrome"))
            this.driver = new ChromeDriver();

        if (browserName.equalsIgnoreCase("firefox"))
            this.driver = new FirefoxDriver();

        if (browserName.equalsIgnoreCase("edge"))
            this.driver = new EdgeDriver();

    }

    public static WebDriver getDriver(String browserName) {
        switch (browserName) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                driver = new ChromeDriver(options);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options1 = new FirefoxOptions();
                options1.addArguments("--start-maximized");
                driver = new FirefoxDriver(options1);
                break;
            case "safari":
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
                driver.manage().window().maximize();
                break;
            default:
                System.out.println("Incorrect browser selected");
        }
        return driver;
    }
}
