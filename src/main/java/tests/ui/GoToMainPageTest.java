package tests.ui;

import org.openqa.selenium.WebDriver;
import driver.WebDriverSingleton;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.MainPage;

public class GoToMainPageTest {
    private MainPage mainPage;
    private final String EXPECTED_MAIN_PAGE_URL = "https://chudodey.com/";

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        WebDriver driver = WebDriverSingleton.initDriver();

        mainPage = new MainPage(driver);

        mainPage.navigateToMainPage();
    }

    @Test
    public void goToMainPage() {
        Assert.assertEquals(mainPage.getPageUrl(), EXPECTED_MAIN_PAGE_URL);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriverSingleton.closeDriver();
    }
}