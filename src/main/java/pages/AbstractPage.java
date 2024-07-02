package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Log;
import java.time.Duration;

public abstract class AbstractPage {
    protected WebDriver driver;
    protected static final Logger LOGGER = Log.getLogger();

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToPage(String pageUrl) {
        driver.get(pageUrl);
    }

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    protected void waitForElement(WebElement element, Duration timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            LOGGER.error("Элемент не был найден за отведенное время");
        }
    }


}