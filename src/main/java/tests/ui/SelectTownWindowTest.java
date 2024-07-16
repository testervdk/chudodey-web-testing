package tests.ui;

import driver.WebDriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.MainPage;

public class SelectTownWindowTest {
    private MainPage mainPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        WebDriver driver = WebDriverSingleton.initDriver();

        mainPage = new MainPage(driver);

        mainPage.navigateToMainPage();

        mainPage.waitTownModalWindow();

        mainPage.confirmTownModalWindow();
    }

    @Test
    public void selectTownWindow() {
        Assert.assertFalse(mainPage.isModalFadeShowEffectDisplayed());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriverSingleton.closeDriver();
    }
}