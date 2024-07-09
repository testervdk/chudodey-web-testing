package tests.ui;

import driver.WebDriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.BrandPage;
import pages.MainPage;
import pages.ProductPage;

public class AddProductShoppingCartTest {
    private MainPage mainPage;
    private BrandPage brandPage;
    private ProductPage productPage;

    @BeforeTest(alwaysRun = true)
    public void setUp() {
        WebDriver driver = WebDriverSingleton.initDriver();

        mainPage = new MainPage(driver);

        mainPage.navigateToMainPage();

        mainPage.waitModalFadeShowEffectClose();

        brandPage = new BrandPage(driver);

        brandPage.navigateToBrandPage();

        brandPage.waitForPageLoaded();

        brandPage.clickBrandLink();

        productPage = new ProductPage(driver);

        productPage.clickProductButton();

        productPage.waitProductAddedShoppingCart();
    }

    @Test
    public void test() {
        Assert.assertTrue(productPage.isProductAddedShoppingCart());
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        WebDriverSingleton.closeDriver();
    }
}