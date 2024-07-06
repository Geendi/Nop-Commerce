package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.Helper;

import java.net.MalformedURLException;
import java.net.URL;

public class TestBase2 {

    public static String baseURL = "https://demo.nopcommerce.com/";
    protected ThreadLocal<RemoteWebDriver> driver = null;

    @BeforeTest
    @Parameters(value = {"browser"})
    public void setUp(@Optional("chrome") String browser) throws MalformedURLException {
        driver = new ThreadLocal<>();
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", browser);
        driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),caps));
        getDriver().navigate().to(baseURL);
    }

    public WebDriver getDriver(){
        return driver.get();
    }

    @AfterTest
    public void tearDown(){
        getDriver().quit();
        driver.remove();
    }

    @AfterMethod
    public void recordFailure(ITestResult result){
        if (ITestResult.FAILURE == result.getStatus()){
            System.out.println("Failed, Taking screenshot..");
            Helper.captureScreenshot(getDriver(), result.getName());
        }
    }


}
