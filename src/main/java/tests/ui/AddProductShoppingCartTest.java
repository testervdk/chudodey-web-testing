package tests.ui;

import driver.WebDriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.BrandPage;
import pages.ProductPage;

public class AddProductShoppingCartTest {
    private BrandPage brandPage;
    private ProductPage productPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        WebDriver driver = WebDriverSingleton.initDriver();

        brandPage = new BrandPage(driver);

        brandPage.navigateToBrandPage();

        brandPage.waitTownModalWindowHide();

        productPage = brandPage.clickBrandLink();

        productPage.clickProductButton();

        productPage.waitProductAddedShoppingCart();
    }

    @Test
    public void addProductShoppingCart() {
        Assert.assertTrue(productPage.isProductAddedShoppingCart());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriverSingleton.closeDriver();
    }
}