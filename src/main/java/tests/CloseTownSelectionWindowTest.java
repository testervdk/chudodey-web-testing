package tests;

import driver.WebDriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.MainPage;

public class CloseTownSelectionWindowTest {
    private MainPage mainPage;

    @BeforeTest(alwaysRun = true)
    public void setUp() {
        WebDriver driver = WebDriverSingleton.initDriver();

        mainPage = new MainPage(driver);

        mainPage.navigateToMainPage();

        mainPage.waitTownModalWindow();

        mainPage.confirmTownModalWindow();
    }

    @Test
    public void test() {
        Assert.assertFalse(mainPage.isModalFadeShowEffectDisplayed());
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        WebDriverSingleton.closeDriver();
    }
}