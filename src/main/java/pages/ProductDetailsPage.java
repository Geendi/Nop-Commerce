package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailsPage extends PageBase{

    WebDriver driver;

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    //ToDo adding locators
    //--------------------------------------------------------------------------------
    @FindBy(tagName = "h1")
    public WebElement productNameResult;

    @FindBy(id = "price-value-4")
    public WebElement productPriceLabel;

    @FindBy(partialLinkText = "Apple MacBook")
    public WebElement product_Name;
    //--------------------------------------------------------------------------------
    //ToDo adding methods


}
