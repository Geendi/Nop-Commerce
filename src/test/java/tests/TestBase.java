package tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.Helper;

import java.util.HashMap;

public class TestBase extends AbstractTestNGCucumberTests {
    public static WebDriver driver;

    public static String downPath = System.getProperty("user.dir") + "\\Downloads";
    public static FirefoxOptions firefoxOptions(){
        FirefoxOptions option = new FirefoxOptions();
        option.addPreference("browser.download.folderList", 2);
        option.addPreference("browser.download.dir", downPath);
        option.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
        option.addPreference("browser.download.manager.showWhenStarting", false);
        return option;
    }

    public static ChromeOptions chromeOptions(){
        ChromeOptions option = new ChromeOptions();
        HashMap<String, Object> chromePref = new HashMap<String, Object>();
        chromePref.put("profile.default.content_settings.popups", 0);
        chromePref.put("download.default_directory", downPath);
        option.setExperimentalOption("prefs", chromePref);
        option.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        return option;
    }

    @BeforeTest
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browserName){
        if (browserName.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
                    + "\\Drivers\\chromedriver.exe");
            driver = new ChromeDriver(chromeOptions());
        } else if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")
                    + "\\Drivers\\geckodriver.exe");
            driver = new FirefoxDriver(firefoxOptions());
        } else if (browserName.equalsIgnoreCase("chrome-headless")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
                    + "\\Drivers\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--window-size=1920,1080");
            driver = new ChromeDriver(options);
        }
        driver.get("https://demo.nopcommerce.com/");
        driver.manage().window().maximize();
    }

    @AfterTest
    public void cleanUp(){
        driver.quit();
    }

    @AfterMethod
    public void recordFailure(ITestResult result){
        if (ITestResult.FAILURE == result.getStatus()){
            System.out.println("Failed, Taking screenshot..");
            Helper.captureScreenshot(driver, result.getName());
        }
    }
}
