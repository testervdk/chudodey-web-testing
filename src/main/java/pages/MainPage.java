package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;

public class MainPage extends AbstractPage {
    private static final String CHUDODEY_MAIN_PAGE_URL = "https://chudodey.com/";

    @FindBy(css = ".modal.fade.show")
    private static WebElement modalFadeShowEffect;
    @FindBy(css = ".change-city-btn.btn.btn-outline-primary.d-none")
    private static WebElement townSelectionAgreementButton;

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void navigateToMainPage() {
        super.navigateToPage(CHUDODEY_MAIN_PAGE_URL);
    }

    public void waitTownModalWindow() {
        waitForElement(modalFadeShowEffect, Duration.ofSeconds(10));
    }

    public void closeTownModalWindow() {
        LOGGER.info("Жду появление кнопки согласия выбора города");
        waitForElement(townSelectionAgreementButton, Duration.ofSeconds(10));

        LOGGER.info("Кликаю на кнопку");
        townSelectionAgreementButton.click();
    }

    public boolean isModalFadeShowEffectDisplayed() {
        return !modalFadeShowEffect.isDisplayed();
    }

}