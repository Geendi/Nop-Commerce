package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchPage extends PageBase{
    //ToDo add driver
    WebDriver driver;

    public SearchPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    //ToDo adding locators
    //--------------------------------------------------------------------------------
    private final By searchBox = By.id("small-searchterms");
    private final By searchButton = By.cssSelector("button.button-1.search-box-button");
    @FindBy(id = "ui-id-1")
    List<WebElement> productList;
    private final By productTitle = By.linkText("Apple MacBook Pro 13-inch");
    //--------------------------------------------------------------------------------
    //ToDo adding methods
    public void productSearch(String productName){
        driver.findElement(searchBox).sendKeys(productName);
        driver.findElement(searchButton).click();
    }

    public void openProductDetails(){
        driver.findElement(productTitle).click();
    }

    public void productSearchUsingAutoSuggest(String searchText){
        driver.findElement(searchBox).sendKeys(searchText);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ui-id-1")));
        productList.getFirst().click();
    }


}
