package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EmailToFriendPage extends PageBase{
    WebDriver driver;

    public EmailToFriendPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    //ToDo adding locators
    //--------------------------------------------------------------------------------
    private final By assertPageMessage = By.tagName("h1");
    private final By friend_email = By.id("FriendEmail");
    private final By personalMessage = By.id("PersonalMessage");
    private final By sendButton = By.cssSelector("button.button-1.send-email-a-friend-button");
    private final By successMessage = By.cssSelector("div.result");
    //--------------------------------------------------------------------------------
    //ToDo adding methods
    public String verifyEmailToFriendPage(){
        return driver.findElement(assertPageMessage).getText();
    }

    public void enterDataForEmail(String friendEmail, String message){
        driver.findElement(friend_email).sendKeys(friendEmail);
        driver.findElement(personalMessage).sendKeys(message);
        driver.findElement(sendButton).click();
    }

    public String verifySubmittedMessage(){
        return driver.findElement(successMessage).getText();
    }

}
