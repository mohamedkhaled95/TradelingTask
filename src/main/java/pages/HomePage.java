package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import base.PageBase;

public class HomePage extends PageBase {


    By sideMenu = By.id("nav-hamburger-menu");
    By todayDealsButton = By.xpath("//a[@href='/gp/goldbox?ref_=nav_cs_gb']");
    By changeSettingsButton = By.id("icp-nav-flyout");
    By searchDropDownList = By.id("searchDropdownBox");
    By searchDescriptionInput = By.id("twotabsearchtextbox");
    By searchButton= By.id("nav-search-submit-button");

    public HomePage(WebDriver driver) {
        super(driver);

    }

    public void selectCategory(String Category, String subCategory) throws InterruptedException {

        clickElement(sideMenu);
        clickElement(By.xpath("//div[contains(text(),'" + Category + "')]"));
       
        clickElement(By.xpath("//a[contains(text(),'" + subCategory + "')]"));
    }

    public void goToTodayDeals() {

        jsClick(todayDealsButton);
    }

    public void goToChangeSettings() {
        clickElement(changeSettingsButton);
    }

    public void searchInDepartment(String department, String searchDescription) {
        selectElementDropdownByVisibleText(searchDropDownList,department);
        typeText(searchDescriptionInput,searchDescription);
        clickElement(searchButton);
    }

}
