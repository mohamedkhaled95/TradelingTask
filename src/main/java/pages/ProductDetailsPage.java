package pages;

import base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class ProductDetailsPage extends PageBase {


    By firstItemField = By.xpath("(//div[@class='a-section a-spacing-base'])[2]");
    By itemAvailabilityInStockField = By.id("availability");
    By priceField = By.xpath("//div[@class='a-section a-spacing-micro']/span");
    By ratingField = By.id("acrCustomerReviewText");
    By shippingDetailsField= By.id("amazonGlobal_feature_div");
    By addToCartButton = By.id("add-to-cart-button");
    By successMessageField = By.xpath("//span[@class='a-size-medium-plus a-color-base sw-atc-text a-text-bold']");
    private Properties properties;


    public ProductDetailsPage(WebDriver driver, Properties properties) {
        super(driver);
        this.properties = properties;
    }

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public void selectRandomItem() {
        clickElement(firstItemField);

    }

    public Boolean validateAvailabilityOfItemInStock()
    {
        return getTextOfElement(itemAvailabilityInStockField).equals("In Stock.");
    }
    public Boolean verifyPriceOfSelectedItemAppeared()
    {

      System.out.println("The price of selected product : "+getTextOfElement(priceField));
    return elementISDisplayed(priceField);
    }
    public Boolean verifyRatingOfSelectedItemAppeared()
    {

        System.out.println("The number of ratings of selected product : "+getTextOfElement(ratingField));
        return elementISDisplayed(priceField);
    }
    public Boolean verifyShippingDetailsExisted()
    {
        System.out.println(getTextOfElement(shippingDetailsField));
        return elementISDisplayed(shippingDetailsField);
    }
    public Boolean verifyAddItemToCart()
    {
        clickElement(addToCartButton);
        System.out.println(getTextOfElement(successMessageField));
        return elementISDisplayed(successMessageField);
    }
    public Boolean validateSelectedCurrency(String currency)
    {

        System.out.println(getTextOfElement(priceField));
        return getTextOfElement(priceField).contains(currency);
    }


}
