package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends AbstractPage {
    private static final String CHUDODEY_MAIN_PAGE_URL = "https://chudodey.com/";

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void navigateToMainPage() {
        super.navigateToPage(CHUDODEY_MAIN_PAGE_URL);
    }

}