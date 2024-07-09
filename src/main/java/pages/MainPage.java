package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;

public class MainPage extends AbstractPage {
    private static final String CHUDODEY_MAIN_PAGE_URL = "https://chudodey.com/";

    @FindBy(xpath = "//*[@data-target='#popup-auth']")
    private WebElement loginButton;
    @FindBy(name = "telephone")
    private WebElement phoneInput;
    @FindBy(name = "password")
    private WebElement passwordInput;
    @FindBy(css = ".bt-authorization")
    private WebElement authorizationButton;
    @FindBy(css = ".bottom-error")
    private WebElement bottomErrorText;

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void navigateToMainPage() {
        super.navigateToPage(CHUDODEY_MAIN_PAGE_URL);
    }

    public void clickLoginButton() {
        waitForElementToBeClickable(loginButton, Duration.ofSeconds(10));

        loginButton.click();
    }

    public void inputPhone(String phone) {
        waitForElementToDisplay(phoneInput, Duration.ofSeconds(5));
        phoneInput.sendKeys(phone);
    }

    public void inputPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void clickAuthorizationButton() {
        authorizationButton.click();
    }

    public boolean isUserDataWrong(String message) {
        waitForElementToDisplay(bottomErrorText, Duration.ofSeconds(5));

        return bottomErrorText.getText().equals(message);
    }

}