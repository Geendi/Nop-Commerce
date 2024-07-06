package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddToCartPage extends PageBase{

    WebDriver driver;

    public AddToCartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    //ToDo adding locators
    //--------------------------------------------------------------------------------
    private final By addToCartButton = By.id("add-to-cart-button-4");
    private final By addedToCartMessage = By.cssSelector("p.content");
    private final By addToCartPopUp = By.linkText("shopping cart");
    private final By shoppingCartButton = By.linkText("Shopping cart");
    private final By addToCartPage = By.tagName("h1");
    private final By removeButton = By.xpath("//button[@name='updatecart']");
    private final By removeMessage = By.cssSelector("div.no-data");
    private final By quantityUpButton = By.cssSelector("div.quantity.up");
    private final By quantityDownButton = By.cssSelector("div.quantity.down");
    private final By continueShoppingButton = By.cssSelector("button.button-2.continue-shopping-button");
    private final By continueShoppingMessage = By.cssSelector("div.title");
    private final By product_Name = By.partialLinkText("Apple MacBook");
    //--------------------------------------------------------------------------------
    //ToDo adding methods
    public void navigateToShoppingCartUsingPopUp(){
        driver.findElement(addToCartPopUp).click();
    }

    public void navigateToShoppingCartUsingSectionButton(){
        driver.findElement(shoppingCartButton).click();
    }

    public void addToCart(){
        driver.findElement(addToCartButton).click();
    }

    public void increaseQuantity(){
        driver.findElement(quantityUpButton).click();
    }

    public void decreaseQuantity(){
        driver.findElement(quantityDownButton).click();
    }

    public void continueShopping(){
        driver.findElement(continueShoppingButton).click();
    }

    public void removeFromCart(){
        driver.findElement(removeButton).click();
    }

    public String assertAddedToCartMessage(){
        return driver.findElement(addedToCartMessage).getText();
    }

    public String assertShoppingCartPage(){
        return driver.findElement(addToCartPage).getText();
    }

    public String assertRemoveItemMessage(){
        return driver.findElement(removeMessage).getText();
    }

    public String assertContinueShoppingMessage(){
        return driver.findElement(continueShoppingMessage).getText();
    }

    public String assertProductName(){
        return driver.findElement(product_Name).getText();
    }
}
