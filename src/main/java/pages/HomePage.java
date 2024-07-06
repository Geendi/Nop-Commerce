package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class HomePage extends PageBase{

    WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    //ToDo adding locators
    //--------------------------------------------------------------------------------
    private final By registerLink = By.linkText("Register");
    private final By loginLink = By.linkText("Log in");
    private final By contactUsLink = By.linkText("Contact us");
    private final By emailToFriendPage = By.cssSelector("button.button-2.email-a-friend-button");
    private final By currencyDropdownList = By.id("customerCurrency");
    private final By add_Review = By.linkText("Add your review");
    //--------------------------------------------------------------------------------
    //ToDo adding methods

    public void openRegistrationPage(){
        driver.findElement(registerLink).click();
    }

    public void openLoginPage(){
        driver.findElement(loginLink).click();
    }

    public void openContactUsPage(){
        driver.findElement(contactUsLink).click();
    }

    public void openEmailToFriendPage(){
        driver.findElement(emailToFriendPage).click();
    }

    public void changeCurrencyFunction(String currency){
        select = new Select(driver.findElement(currencyDropdownList));
        select.selectByVisibleText(currency);
    }

}
