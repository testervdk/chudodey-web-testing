package tests;

import org.openqa.selenium.WebDriver;
import driver.WebDriverSingleton;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.MainPage;

public class GoToMainPageTest {
    private MainPage mainPage;
    private String expectedMainPageUrl = "https://chudodey.com/";

    @BeforeTest(alwaysRun = true)
    public void setUp() {
        WebDriver driver = WebDriverSingleton.initDriver();

        mainPage = new MainPage(driver);

        mainPage.navigateToMainPage();
    }

    @Test
    public void test() {
        Assert.assertEquals(mainPage.getPageUrl(), expectedMainPageUrl);
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        WebDriverSingleton.closeDriver();
    }

}
