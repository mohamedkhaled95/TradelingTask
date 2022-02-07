package pages;

import base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TodayDealsPage extends PageBase {


    By televisionsSection = By.xpath("//a[@aria-label='Smart Televisions']");


    public TodayDealsPage(WebDriver driver) {
        super(driver);
    }

    public boolean ValidateSelectedDepartment(String department) {
        clickElement(By.xpath("//span[contains(text(),'"+department+"')]"));
       return driver.findElement(By.xpath("//span[contains(text(),'"+department+"')]//preceding::input[1]")).isSelected();
    }



}
