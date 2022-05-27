package config;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Paths;
import java.util.Map;

public class DriverConfig {
    public static final String SERVER_URL = "http://automationpractice.com/";
    private static WebDriver driver;
    private static WebDriverWait wait;

    public void initDriver() {
        System.setProperty(
                "webdriver.chrome.driver",
                Paths.get("src/test/resources/chromedriver.exe").toString()
        );
        if (driver == null) {
            driver = new ChromeDriver();
            //driver.manage().window().maximize();
            wait = new WebDriverWait(driver, 20);
        }
    }

    public void closeDriver() {
        if (driver != null) {
            driver.close();
            driver.quit();
            driver = null;
        }
    }

}
