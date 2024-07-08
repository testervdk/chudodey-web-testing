package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
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

    protected void waitForElement(WebElement element, Duration timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            LOGGER.error("Элемент не был найден за отведенное время");
        }
    }

    protected String waitForUrlChanged(Duration timeout) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String currentUrl = (String) js.executeScript("return window.location.href;");

        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentUrl)));

            String updatedUrl = driver.getCurrentUrl();

            LOGGER.info("URL обновился");

            return updatedUrl;
        } catch (TimeoutException e) {
            LOGGER.error(e.getMessage());
        }

        return currentUrl;
    }

    public void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation = driver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState").toString().equals("complete");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(expectation);
    }

    public void waitTownModalWindow() {
        try {
            LOGGER.info("Жду появление модального окна");
            waitForElement(modalFadeShowEffect, Duration.ofSeconds(5));
        } catch (Exception e) {
            LOGGER.warn("Модальное окно не появилось");
        }
    }

    public void confirmTownModalWindow() {
        LOGGER.info("Жду появление кнопки согласия выбора города");
        waitForElement(townSelectionAgreementButton, Duration.ofSeconds(10));

        LOGGER.info("Кликаю на кнопку");
        townSelectionAgreementButton.click();
    }

    public boolean isModalFadeShowEffectDisplayed() {
        return !modalFadeShowEffect.isDisplayed();
    }




}