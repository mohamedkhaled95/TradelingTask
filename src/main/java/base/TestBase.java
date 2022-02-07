package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase {

	public static WebDriver driver;


	
	public static final String PROPERTY_WEB_URL = "web.url";
	public static final String PROPERTY_BROWSER_TYPE = "browser.type";

	public static Properties properties;

	public static ExtentTest test;
	String screenshotpath;
	String screenshotfilename;


	@BeforeTest
	public void beforeSuite() throws IOException {

		properties = ReadProperties(
				System.getProperty("user.dir") + "\\src\\main\\resources\\config\\configuration.properties");
	}

	// Read Properity file
	public static Properties ReadProperties(String filePath) {

		try {
			FileInputStream testProperties = new FileInputStream(filePath);
			properties = new Properties();
			properties.load(testProperties);
			return properties;
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}
		return properties;
	}

	// open browser with dimension
	public void openBrowser() {

		String url = properties.getProperty(PROPERTY_WEB_URL);
		String browserType = properties.getProperty(PROPERTY_BROWSER_TYPE);
		if (browserType.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\main\\resources\\Drivers\\chromedriver.exe");

			final ChromeOptions options = new ChromeOptions();
			HashMap<String, Object> chromePrefs = new HashMap<>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("safebrowsing.enabled", "true");
			options.addArguments("--start-maximized");
			options.setExperimentalOption("prefs", chromePrefs);
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			cap.setCapability(ChromeOptions.CAPABILITY, options);
			cap.setCapability("resolution", "1024x768");

			driver = new ChromeDriver(options);

		} else {
			throw new IllegalArgumentException("Browser " + browserType + " not supported.");
		}
		driver.navigate().to(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public static void getScreenShot(WebDriver driver, String fileToSave) throws IOException {
		//capture screen shoot with a .png format.
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(scrFile, new File(fileToSave));
	}

	public void tearDown(ITestResult result) {
		String test = result.getName();

		String datenow = new SimpleDateFormat("dd-M-yyyy-hh-mm").format(new Date());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (result.isSuccess()) {
			screenshotpath = "./screenshots/Passed/";
			screenshotfilename = test + "_" + datenow + ".png";
			try {
				getScreenShot(TestBase.driver, "./screenshots/Passed/"+ test + "_" + datenow + ".png");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			System.out.println("-----------------Test case finished with status Passed" + "------------------");
		} else {
			screenshotpath = "./screenshots/Failed/";
			try {
				getScreenShot(TestBase.driver, "./screenshots/Failed/"+ test + "_" + datenow + ".png");
			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println("-----------------Test case finished with status Failed" + "------------------");
		}
		// Close browser
		driver.quit();
	}

}
