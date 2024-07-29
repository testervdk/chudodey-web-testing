package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Log;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;

public abstract class AbstractPage {
    protected WebDriver driver;
    protected static final Logger LOGGER = Log.getLogger();

    @FindBy(css = ".modal.fade.show")
    private static WebElement modalWindowSelectingCurrentTown;
    @FindBy(css = ".change-city-btn.btn.btn-outline-primary.d-none")
    private static WebElement townConfirmationButton;
    @FindBy(css = ".top-menu__icon-city.choose-city-btn.text-uppercase")
    private static WebElement currentTownButton;

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

    protected void waitForElementToDisplay(WebElement element, Duration timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            LOGGER.error("Элемент не был найден");
        }
    }

    protected void waitForElementToBeClickable(WebElement element, Duration timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            LOGGER.error("Элемент не доступен для клика");
        }
    }

    public void waitTownModalWindow() {
        try {
            LOGGER.info("Жду появление модального окна с выбором города");
            waitForElementToDisplay(modalWindowSelectingCurrentTown, Duration.ofSeconds(5));
        } catch (Exception e) {
            LOGGER.warn("Модальное окно не появилось");
        }
    }

    public void clickTownConfirmationButton() {
        try {
            LOGGER.info("Жду появление кнопки 'Да, верно'");
            waitForElementToDisplay(townConfirmationButton, Duration.ofSeconds(5));

            LOGGER.info("Кликаю на кнопку 'Да, верно'");
            townConfirmationButton.click();
        } catch (Exception e) {
            LOGGER.warn("Кнопка 'Да, верно' не появилась");
        }
    }

    public void waitTownModalWindowHide() {
        try {
            waitForElementToDisplay(modalWindowSelectingCurrentTown, Duration.ofSeconds(5));

            hideTownModalWindow(modalWindowSelectingCurrentTown);
        } catch (Exception e) {
            LOGGER.info("Модальное окно не появилось");
        }
    }

    public void hideTownModalWindow(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.display = 'none';", element);

        js.executeScript("document.querySelector('.modal-backdrop').remove();");
    }

    public boolean isTownModalWindowDisplayed() {
        return modalWindowSelectingCurrentTown.isDisplayed();
    }

    public String getCurrentTownName() {
        waitForElementToDisplay(currentTownButton, Duration.ofSeconds(3));
        
        return currentTownButton.getText();
    }
}