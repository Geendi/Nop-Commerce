package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage extends PageBase{
    //ToDo add driver
    WebDriver driver;

    public RegistrationPage(WebDriver driver){
        super(driver);
        this.driver = driver;

    }


    //ToDo adding locators
    //--------------------------------------------------------------------------------
    private final By assertionPageMessage = By.tagName("h1");
    private final By gender = By.id("gender-male");
    private final By first_Name = By.id("FirstName");
    private final By last_Name = By.id("LastName");
    private final By dateOfBirthDay = By.name("DateOfBirthDay");
    private final By dateOfBirthMonth = By.name("DateOfBirthMonth");
    private final By dateOfBirthYear = By.name("DateOfBirthYear");
    private final By company_Name = By.id("Company");
    private final By newsLetterButton = By.id("Newsletter");
    private final By eMail = By.id("Email");
    private final By pass_Word = By.id("Password");
    private final By confirm_Password = By.id("ConfirmPassword");
    private final By registerButton = By.id("register-button");
    private final By registrationCompleteMessage = By.xpath("//div[@class='result']");
    private final By continueButton = By.xpath("//a[@class='button-1 register-continue-button']");
    private final By signOutButton = By.linkText("Log out");
    //--------------------------------------------------------------------------------
    //ToDo adding methods

    public String registrationPageAssertion(){
        return driver.findElement(assertionPageMessage).getText();
    }

    public void enterUserData(String firstName, String lastName, String day, String month, String year, String email, String companyName, String password, String confirmPassword){
        driver.findElement(gender).click();
        driver.findElement(first_Name).sendKeys(firstName);
        driver.findElement(last_Name).sendKeys(lastName);
        select = new Select(driver.findElement(dateOfBirthDay));
        select.selectByVisibleText(day);
        select = new Select(driver.findElement(dateOfBirthMonth));
        select.selectByVisibleText(month);
        select = new Select(driver.findElement(dateOfBirthYear));
        select.selectByVisibleText(year);
        driver.findElement(eMail).sendKeys(email);
        driver.findElement(company_Name).sendKeys(companyName);
        driver.findElement(newsLetterButton).click();
        driver.findElement(pass_Word).sendKeys(password);
        driver.findElement(confirm_Password).sendKeys(confirmPassword);
        driver.findElement(registerButton).click();
    }

    public void enterUserDataFaker(String firstName, String lastName, String email, String password, String confirmPassword){
        driver.findElement(gender).click();
        driver.findElement(first_Name).sendKeys(firstName);
        driver.findElement(last_Name).sendKeys(lastName);
        driver.findElement(eMail).sendKeys(email);
        driver.findElement(newsLetterButton).click();
        driver.findElement(pass_Word).sendKeys(password);
        driver.findElement(confirm_Password).sendKeys(confirmPassword);
        driver.findElement(registerButton).click();
    }

    public String registrationCompleteAssertion(){
        return driver.findElement(registrationCompleteMessage).getText();
    }

    public void clickContinue(){
        driver.findElement(continueButton).click();
    }

    public void logOut(){
        driver.findElement(signOutButton).click();
    }


}
