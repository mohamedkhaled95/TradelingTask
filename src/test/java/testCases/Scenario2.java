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
import pages.TodayDealsPage;
import testData.TestDataReader;

import java.io.IOException;
import java.lang.reflect.Method;

public class Scenario2 extends TestBase {

    HomePage homepageObj;
 TodayDealsPage todayDealsPage;


    @BeforeMethod
    public void beforeMethod(Method method) {
        openBrowser();
        homepageObj = new HomePage(driver);
        todayDealsPage = new TodayDealsPage(driver);
    }

   	@DataProvider(name = "ExcelData")
        public Object[][] UserRegistrationData() throws IOException {
            TestDataReader TD = new TestDataReader();
            Object data[][] = null;
            data = TD.fetchData("Scenario2");
            return data;

        }

    @Test(dataProvider = "ExcelData")
    public void validateChoosingCorrectSelectedDepartment(String department)  {
        homepageObj.goToTodayDeals();
        Assert.assertTrue(todayDealsPage.ValidateSelectedDepartment(department));
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        tearDown(result);
    }

}
