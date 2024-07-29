package tests.ui;

import driver.WebDriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.MainPage;

public class AuthorizationTest {
    private MainPage mainPage;
    private final String USER_PHONE = "+7 000 000 00 00";
    private final String USER_PASSWORD = "123456";
    private final String EXPECTED_ERROR_MESSAGE = "Неверная комбинация логина и пароля";

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        WebDriver driver = WebDriverSingleton.initDriver();

        mainPage = new MainPage(driver);

        mainPage.navigateToMainPage();

        mainPage.waitTownModalWindowHide();

        mainPage.clickLoginButton();

        mainPage.inputPhone(USER_PHONE);

        mainPage.inputPassword(USER_PASSWORD);

        mainPage.clickAuthorizationButton();
    }

    @Test
    public void invalidAuthorization() {
        Assert.assertTrue(mainPage.isUserDataWrong(EXPECTED_ERROR_MESSAGE));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriverSingleton.closeDriver();
    }
}