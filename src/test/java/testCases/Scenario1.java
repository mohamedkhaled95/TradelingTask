package testCases;

import base.TestBase;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomeEntertainmentPage;
import pages.HomePage;
import testData.TestDataReader;
import java.io.IOException;
import java.lang.reflect.Method;

public class Scenario1 extends TestBase {

    HomePage homepageObj;
    HomeEntertainmentPage homeEntertainmentPage;



    @BeforeMethod
    public void beforeMethod(Method method) {
        openBrowser();
        homepageObj = new HomePage(driver);
        homeEntertainmentPage = new HomeEntertainmentPage(driver);
    }

    	@DataProvider(name = "ExcelData")
        public Object[][] UserRegistrationData() throws IOException {
            TestDataReader TD = new TestDataReader();
            Object data[][] = null;
            data = TD.fetchData("Scenario1");
            return data;

        }

    @Test(dataProvider = "ExcelData")
    public void validateCountOfDisplayedItemsEqualResult(String category,String subCategory) throws InterruptedException {

        homepageObj.selectCategory(category, subCategory);
        homeEntertainmentPage.goToTelevisionsSection();
        Assert.assertEquals(homeEntertainmentPage.getNumberOfDisplayedProducts(),homeEntertainmentPage.getResultOfProducts());
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        tearDown(result);
    }

}
