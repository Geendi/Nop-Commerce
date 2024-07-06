package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderDetailsPage extends PageBase{

    WebDriver driver;

    public OrderDetailsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    //ToDo adding locators
    //--------------------------------------------------------------------------------
    private final By print = By.linkText("Print");
    private final By PDF = By.linkText("PDF Invoice");
    //--------------------------------------------------------------------------------
    //ToDo adding methods
    public void downloadOrder(){
        driver.findElement(PDF).click();
    }

    public void printOrder(){
        String currentWindowID = driver.getWindowHandle();
        driver.findElement(print).click();

        for (String windowID : driver.getWindowHandles()){
            String url = driver.switchTo().window(windowID).getCurrentUrl();
            if (url.contains("print")){
                driver.close();
                break;
            }
        }
        driver.switchTo().window(currentWindowID);
    }


}
