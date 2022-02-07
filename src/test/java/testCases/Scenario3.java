package testCases;

import base.TestBase;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductDetailsPage;
import pages.SettingsPage;
import pages.TodayDealsPage;
import testData.TestDataReader;

import java.io.IOException;
import java.lang.reflect.Method;

public class Scenario3 extends TestBase {

    HomePage homepageObj;
    SettingsPage settingsPage;
    ProductDetailsPage productDetailsPage;

    @BeforeMethod
    public void beforeMethod(Method method) {
        openBrowser();
        homepageObj = new HomePage(driver);
        settingsPage = new SettingsPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
    }

    	@DataProvider(name = "ExcelData")
        public Object[][] UserRegistrationData() throws IOException {
            TestDataReader TD = new TestDataReader();
            Object data[][] = null;
            data = TD.fetchData("Scenario3");
            return data;

        }

    @Test(dataProvider = "ExcelData")
    public void validateChangingCurrency(String currency,String department,String searchDescription) {
        homepageObj.goToChangeSettings();
        settingsPage.selectCurrency(currency);
        homepageObj.searchInDepartment(department ,searchDescription);
        productDetailsPage.selectRandomItem();
        productDetailsPage.validateSelectedCurrency(currency);
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        tearDown(result);
    }

}
