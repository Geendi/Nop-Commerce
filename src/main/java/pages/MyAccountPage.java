package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage extends PageBase{
    //ToDo add driver
    WebDriver driver;

    public MyAccountPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    //ToDo adding locators
    //--------------------------------------------------------------------------------
    private final By assertionPageMessage = By.tagName("h1");
    private final By myAccount = By.cssSelector("a.ico-account");
    private final By changePasswordPage = By.linkText("Change password");
    private final By old_Password = By.id("OldPassword");
    private final By new_Password = By.id("NewPassword");
    private final By confirm_Password = By.id("ConfirmNewPassword");
    private final By changePasswordButton = By.cssSelector("button.button-1.change-password-button");
    private final By changePwCompleteMessage = By.cssSelector("p.content");
    private final By dismissButton = By.cssSelector("span.close");
    //--------------------------------------------------------------------------------
    //ToDo adding methods
    public void openMyAccountPage(){
        driver.findElement(myAccount).click();
    }

    public void openChangePasswordPage(){
        driver.findElement(changePasswordPage).click();
    }

    public String changePwAssertionMessage(){
        return driver.findElement(assertionPageMessage).getText();
    }

    public void changePassword(String oldPassword, String newPassword, String confirmPassword){
        driver.findElement(old_Password).sendKeys(oldPassword);
        driver.findElement(new_Password).sendKeys(newPassword);
        driver.findElement(confirm_Password).sendKeys(confirmPassword);
        driver.findElement(changePasswordButton).click();
    }

    public String changePwCompleteAssertion(){
        return driver.findElement(changePwCompleteMessage).getText();
    }

    public void dismissCompleteMessage(){
        driver.findElement(dismissButton).click();
    }


}
