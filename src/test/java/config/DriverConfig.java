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

    public void insertFormData(String paramName, String filename) {
        Map<String, String> param = YmlLoader.getParamFromFile(paramName, filename);
        for (Map.Entry<String, String> row : param.entrySet()) {
            WebElement element = findById(row.getKey());
            Assert.assertTrue(element.isEnabled());
            if (row.getValue() == null || row.getValue().trim().isEmpty()) {
                element.click();
            } else {
                element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE), row.getValue());
            }
            wait(1);
        }
    }

    public void accessHomePage() {
        driver.navigate().to(SERVER_URL);
    }

    public void switchToFrame(WebElement element) {
        driver.switchTo().frame(element);
    }

    public void switchToDefault() {
        driver.switchTo().defaultContent();
    }

    public WebElement findById(String name) {
        return driver.findElement(By.id(name));
    }

    public WebElement findByName(String name) {
        return driver.findElement(By.name(name));
    }

    public WebElement findByClass(String name) {
        return driver.findElement(By.className(name));
    }

    public WebElement findByXpath(String expression) {
        return driver.findElement(By.xpath(expression));
    }
    public void wait(int seconds) {
       this.wait(seconds * 1.0);
    }
    public void wait(double seconds) {
        try {
            Thread.sleep((long) seconds * 1000L);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    public void waitUntil(ExpectedCondition<WebElement> condition) {
        wait.until(condition);
    }
}
