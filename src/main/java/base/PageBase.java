package base;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageBase {

  public  WebDriver driver;
    private WebDriverWait wait;


    private static Logger log = LogManager.getLogger(PageBase.class.getName());

	public PageBase(WebDriver driver) {
		this.driver = driver;
	}


    public void clickElement(By byElement ) {

        waitElementToBeVisible(byElement);
        driver.findElement(byElement).click();
        
    }

    public void waitElementToBeVisible(By element) {

        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void hoverElement(WebElement element ) {
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
 

    }

    public void clickHoverElement(WebElement element ) {
        Actions action = new Actions(driver);
        hoverElement(element);
        action.moveToElement(element).click().build().perform();


    }

    public void typeText(By byElement, String text ) {
        waitElementToBeVisible(byElement);
        WebElement element = driver.findElement(byElement);
        element.clear();
        element.sendKeys(text);


    }

    public void selectElementDropdownByValue(By byElement, String value ) {

        Select dropdown = new Select(driver.findElement(byElement));
        dropdown.selectByValue(value);
    }
    public void selectElementDropdownByVisibleText(By byElement, String text ) {

        Select dropdown = new Select(driver.findElement(byElement));
        dropdown.selectByVisibleText(text);
    }

    public boolean elementISDisplayed(By byElement ) {
		waitElementToBeVisible(byElement);
        boolean displayed = driver.findElement(byElement).isDisplayed();

        return displayed;
    }

    public void jsClick(By byElement ) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", driver.findElement(byElement));
        
    }

    public String getTextOfElement(By byElement ) {

        String actualTxt;

        waitElementToBeVisible(byElement);
        if (elementISDisplayed(byElement)) {

            actualTxt = driver.findElement(byElement).getText();
            return actualTxt;
        } else {
            return "";
        }

    }
}
