package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactUsPage extends PageBase{
    //ToDo add driver
    WebDriver driver;

    public ContactUsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    //ToDo adding locators
    //--------------------------------------------------------------------------------
        private final By full_Name = By.id("FullName");
        private final By eMail = By.id("Email");
        private final By enquiryText = By.id("Enquiry");
        private final By submitButton = By.cssSelector("button.button-1.contact-us-button");
        private final By successMessage = By.cssSelector("div.result");
    //--------------------------------------------------------------------------------
    //ToDo adding methods
    public void contactUsData(String fullName, String email, String message){
        driver.findElement(full_Name).sendKeys(fullName);
        driver.findElement(eMail).sendKeys(email);
        driver.findElement(enquiryText).sendKeys(message);
        driver.findElement(submitButton).click();
    }

    public String assertSubmittedEnquiry(){
        return driver.findElement(successMessage).getText();
    }


}
