package testCases;

import base.TestBase;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductDetailsPage;
import pages.SettingsPage;
import testData.TestDataReader;

import java.io.IOException;
import java.lang.reflect.Method;

public class Scenario4 extends TestBase {

    HomePage homepageObj;
    ProductDetailsPage productDetailsPage;


    @BeforeMethod
    public void beforeMethod(Method method) {
        openBrowser();
        homepageObj = new HomePage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
    }

   	@DataProvider(name = "ExcelData")
        public Object[][] UserRegistrationData() throws IOException {
            TestDataReader TD = new TestDataReader();
            Object data[][] = null;
            data = TD.fetchData("Scenario4");
            return data;

        }

    @Test(dataProvider = "ExcelData")
    public void verifyProductDetails(String department,String searchDescription)  {
        homepageObj.searchInDepartment(department, searchDescription);
        productDetailsPage.selectRandomItem();
        Assert.assertTrue(productDetailsPage.validateAvailabilityOfItemInStock());
        Assert.assertTrue(productDetailsPage.verifyPriceOfSelectedItemAppeared());
        Assert.assertTrue(productDetailsPage.verifyRatingOfSelectedItemAppeared());
        Assert.assertTrue(productDetailsPage.verifyShippingDetailsExisted());
        Assert.assertTrue(productDetailsPage.verifyAddItemToCart());
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        tearDown(result);
    }

}
