package pages;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.PageBase;

public class HomeEntertainmentPage extends PageBase {


    By televisionsSection = By.xpath("//img[@alt='Smart Televisions']");


    public HomeEntertainmentPage(WebDriver driver) {
        super(driver);
    }

    public void goToTelevisionsSection() {

      jsClick(televisionsSection);
    }

    public int getNumberOfDisplayedProducts()
    {


        List<WebElement> displayedProducts = driver.findElements(By.xpath("//div[@class='_octopus-search-result-card_style_apbSearchResultItem__2-mx4']"));
        int totalNumberOfDisplayedProducts = displayedProducts.size();
        return totalNumberOfDisplayedProducts;
    }
    public int getResultOfProducts()
    {
        String str =   driver.findElement(By.xpath("//span[@class='a-size-base a-color-base a-text-normal']")).getText();
        String[] arrOfStr = str.split("-", 2);
       String[] arrOfStr2=arrOfStr[1].split(" ",2);
       int result = Integer.parseInt(arrOfStr2[0]);
        return result;
    }
}
