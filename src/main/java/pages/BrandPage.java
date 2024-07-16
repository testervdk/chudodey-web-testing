package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;
import java.util.List;

public class BrandPage extends AbstractPage {
    private static final String BRAND_PAGE_URL = "https://chudodey.com/async_brand";

    @FindBy(xpath = "//a[contains(@class, 'brand-list__item')]")
    private List<WebElement> brandLinks;

    public BrandPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void navigateToBrandPage() {
        super.navigateToPage(BRAND_PAGE_URL);
    }

    public void clickBrandLink() {
        waitForElementToDisplay(brandLinks.getFirst(), Duration.ofSeconds(5));

        brandLinks.getFirst().click();
    }



}