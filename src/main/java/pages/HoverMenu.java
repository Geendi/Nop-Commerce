package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HoverMenu extends PageBase {
    WebDriver driver;
    WebDriverWait wait;

    public HoverMenu(WebDriver driver) {
        super(driver);
        this.driver = driver;
        action = new Actions(driver);
    }

    //ToDo adding locators
    //--------------------------------------------------------------------------------
    private final By computers = By.linkText("Computers");
    private final By desktops = By.linkText("Desktops");
    private final By desktopsAssertion = By.tagName("h1");

    private final By electronics = By.linkText("Electronics");
    private final By cell_phones = By.partialLinkText("Cell");
    private final By cellPhonesAssertion = By.tagName("h1");

    private final By apparel = By.linkText("Apparel");
    private final By shoes = By.linkText("Shoes");
    private final By shoesAssertion = By.tagName("h1");
    //--------------------------------------------------------------------------------
    //ToDo adding methods

    public void hoverComputers(){
        action.moveToElement(driver.findElement(computers)).perform();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(desktops));
        action.moveToElement(driver.findElement(desktops)).click().build().perform();
    }

    public String desktopPageAssertion(){
        return driver.findElement(desktopsAssertion).getText();
    }

    public void hoverElectronics(){
        action.moveToElement(driver.findElement(electronics)).perform();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(cell_phones));
        action.moveToElement(driver.findElement(cell_phones)).click().build().perform();
    }

    public String electronicsPageAssertion(){
        return driver.findElement(cellPhonesAssertion).getText();
    }

    public void hoverApparel(){
        action.moveToElement(driver.findElement(apparel)).perform();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(shoes));
        action.moveToElement(driver.findElement(shoes)).click().build().perform();
    }

    public String apparelPageAssertion(){
        return driver.findElement(shoesAssertion).getText();
    }


}

