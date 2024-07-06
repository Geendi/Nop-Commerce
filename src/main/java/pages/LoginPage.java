package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends PageBase{
    //ToDo add driver
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    //ToDo adding locators
    //--------------------------------------------------------------------------------
    private final By assertionPageMessage = By.tagName("h1");
    private final By eMail = By.id("Email");
    private final By passWord = By.id("Password");
    private final By loginButton = By.cssSelector("button.button-1.login-button");
    private final By verifyMyAccount = By.linkText("My account");
    //--------------------------------------------------------------------------------
    //ToDo adding methods
    public String  loginAssertionMessage(){
        return driver.findElement(assertionPageMessage).getText();
    }

    public void userLogin(String email, String password){
        driver.findElement(eMail).sendKeys(email);
        driver.findElement(passWord).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public String LoginCompleteMessage(){
        return driver.findElement(verifyMyAccount).getText();
    }



}
