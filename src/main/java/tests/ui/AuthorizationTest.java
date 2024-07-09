package tests.ui;

import driver.WebDriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.MainPage;

public class AuthorizationTest {
    private MainPage mainPage;
    String userPhoneNumber = "+7 000 000 00 00";
    String userPassword = "123456";
    String expectedErrorMessage = "Неверная комбинация логина и пароля";

    @BeforeTest(alwaysRun = true)
    public void setUp() {
        WebDriver driver = WebDriverSingleton.initDriver();

        mainPage = new MainPage(driver);

        mainPage.navigateToMainPage();

        mainPage.waitModalFadeShowEffectClose();

        mainPage.clickLoginButton();

        mainPage.inputPhone(userPhoneNumber);

        mainPage.inputPassword(userPassword);

        mainPage.clickAuthorizationButton();
    }

    @Test
    public void invalidAuthorization() {
        Assert.assertTrue(mainPage.isUserDataWrong(expectedErrorMessage));
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        WebDriverSingleton.closeDriver();
    }
}