package tests.ui;

import driver.WebDriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.MainPage;

public class SelectTownWindowTest {
    private MainPage mainPage;
    private final String CURRENT_TOWN_NANE = "ВЛАДИВОСТОК";

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        WebDriver driver = WebDriverSingleton.initDriver();

        mainPage = new MainPage(driver);

        mainPage.navigateToMainPage();

        mainPage.waitTownModalWindow();

        mainPage.clickTownConfirmationButton();
    }

    @Test
    public void selectTownWindow() {
        Assert.assertTrue(mainPage.isTownModalWindowDisplayed());

        Assert.assertEquals(mainPage.getCurrentTownName(), CURRENT_TOWN_NANE);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriverSingleton.closeDriver();
    }
}