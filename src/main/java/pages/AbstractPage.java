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
    private static WebElement modalFadeShowEffect;
    @FindBy(css = ".change-city-btn.btn.btn-outline-primary.d-none")
    private static WebElement townSelectionAgreementButton;

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
            LOGGER.info("Жду появление модального окна");
            waitForElementToDisplay(modalFadeShowEffect, Duration.ofSeconds(5));
        } catch (Exception e) {
            LOGGER.warn("Модальное окно не появилось");
        }
    }

    public void confirmTownModalWindow() {
        try {
            LOGGER.info("Жду появление кнопки согласия выбора города");
            waitForElementToDisplay(townSelectionAgreementButton, Duration.ofSeconds(5));

            LOGGER.info("Кликаю на кнопку");
            townSelectionAgreementButton.click();
        } catch (Exception e) {
            LOGGER.warn("Кнопка согласия выбора города не появилась");
        }
    }

    public void waitModalFadeShowEffectClose() {
        try {
            waitForElementToDisplay(modalFadeShowEffect, Duration.ofSeconds(5));

            hideModalWindow(modalFadeShowEffect);
        } catch (Exception e) {
            LOGGER.info("Модальное окно не появилось");
        }
    }

    public void hideModalWindow(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.display = 'none';", element);

        js.executeScript("document.querySelector('.modal-backdrop').remove();");
    }

    public boolean isModalFadeShowEffectDisplayed() {
        return !modalFadeShowEffect.isDisplayed();
    }
}