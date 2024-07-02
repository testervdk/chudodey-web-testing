package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverSingleton {
    private static WebDriver driver;

    private WebDriverSingleton() {}

    public static WebDriver initDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "/Users/margarita/Downloads/chromedriver-mac-arm64/chromedriver");
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--start-maximized");
            driver = new ChromeDriver(chromeOptions);
        }

        return driver;
    }

    public static void closeDriver() {
        if(driver != null) {
            driver.quit();
        }
    }

}
