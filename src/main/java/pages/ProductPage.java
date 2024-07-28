package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;
import java.util.List;

public class ProductPage extends AbstractPage {
    @FindBy(css = ".bt-add-prod.btn.btn-secondary.btn-sm.product__buy")
    private List<WebElement> purchasingProductsButtons;
    @FindBy(css = ".product-in-cart-block-amount")
    private WebElement addedProductsButton;

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickProductButton() {
        waitForElementToDisplay(purchasingProductsButtons.getFirst(), Duration.ofSeconds(5));
        purchasingProductsButtons.getFirst().click();
    }

    public void waitProductAddedShoppingCart() {
        waitForElementToDisplay(addedProductsButton, Duration.ofSeconds(5));
    }

    public boolean isProductAddedShoppingCart() {
        return addedProductsButton.isDisplayed();
    }
}