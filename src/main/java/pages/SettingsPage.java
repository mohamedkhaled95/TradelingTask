package pages;

import base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class SettingsPage extends PageBase {


By currencyDropDownList = By.id("icp-sc-dropdown");
By saveChangesButton = By.xpath("//span[@id='icp-btn-save']//input");
    private Properties properties;


    public SettingsPage(WebDriver driver, Properties properties) {
        super(driver);
        this.properties = properties;
    }

    public void selectCurrency(String currency)
    {
        selectElementDropdownByValue(currencyDropDownList,currency);
        clickElement(saveChangesButton);
    }


    public SettingsPage(WebDriver driver) {
        super(driver);
    }


}
