package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WishlistPage extends PageBase{

    WebDriver driver;

    public WishlistPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    //ToDo adding locators
    //--------------------------------------------------------------------------------
    private final By addToWishListButton = By.id("add-to-wishlist-button-4");
    private final By addedToWishListMessage = By.cssSelector("p.content");
    private final By wishListPopUp = By.linkText("wishlist");
    private final By wishListPage = By.partialLinkText("Wishlist");
    private final By dismissButton = By.cssSelector("span.close");
    private final By assertWishListPage = By.tagName("h1");
    private final By updateButton = By.id("updatecart");
    private final By removeButton = By.cssSelector("td.remove-from-cart");
    private final By removeMessage = By.cssSelector("div.no-data");
    private final By product_Name = By.partialLinkText("Apple MacBook");
    private final By productQuantity = By.cssSelector("input.qty-input");
    //--------------------------------------------------------------------------------
    //ToDo adding methods
    public String assertAddedMessage(){
        return driver.findElement(addedToWishListMessage).getText();
    }

    public String assertWishListPage(){
        return driver.findElement(assertWishListPage).getText();
    }

    public String assertProductName(){
        return driver.findElement(product_Name).getText();
    }

    public String assertRemoveMessage(){
        return driver.findElement(removeMessage).getText();
    }

    public void addItemToWishList(){
        driver.findElement(addToWishListButton).click();
    }

    public void removeItemFromWishList(){
        driver.findElement(removeButton).click();
    }

    public void updateWishList(){
        driver.findElement(productQuantity).clear();
        driver.findElement(productQuantity).sendKeys("3");
        driver.findElement(updateButton).click();
    }

    public void navigateToWishListPageViaPopUp(){
        driver.findElement(wishListPopUp).click();
    }

    public void navigateToWishListPageViaSectionButton(){
        driver.findElement(dismissButton).click();
        driver.findElement(wishListPage).click();
    }





}
