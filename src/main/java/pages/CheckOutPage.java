package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class CheckOutPage extends PageBase{

    WebDriver driver;
    Select select;

    public CheckOutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    //ToDo adding locators
    //--------------------------------------------------------------------------------
    private final By terms = By.id("termsofservice");
    private final By checkOutButton = By.id("checkout");
    private final By checkOutPage = By.tagName("h1");
    private final By city_Text = By.id("BillingNewAddress_City");
    private final By address_Text = By.id("BillingNewAddress_Address1");
    private final By postal_Code = By.id("BillingNewAddress_ZipPostalCode");
    private final By phone_Number = By.id("BillingNewAddress_PhoneNumber");
    private final By countryList = By.id("BillingNewAddress_CountryId");
    private final By stateList = By.id("BillingNewAddress_StateProvinceId");
    private final By continueButton = By.cssSelector("button.button-1.new-address-next-step-button");
    private final By shippingMethod = By.id("shippingoption_1");
    private final By shippingContinueButton = By.cssSelector("button.button-1.shipping-method-next-step-button");
    private final By paymentMethod = By.id("paymentmethod_0");
    private final By paymentContinueButton = By.cssSelector("button.button-1.payment-method-next-step-button");
    private final By paymentInfoContinueButton = By.cssSelector("button.button-1.payment-info-next-step-button");
    private final By total_Price = By.cssSelector("span.value-summary");
    private final By confirmButton = By.cssSelector("button.button-1.confirm-order-next-step-button");
    private final By order_Details = By.linkText("Click here for order details.");
    private final By guestButton = By.cssSelector("button.button-1.checkout-as-guest-button");
    private final By first_Name = By.id("BillingNewAddress_FirstName");
    private final By last_Name = By.id("BillingNewAddress_LastName");
    private final By eMail = By.id("BillingNewAddress_Email");
    //--------------------------------------------------------------------------------
    //ToDo adding methods
    public String assertCheckOutPage(){
        return driver.findElement(checkOutPage).getText();
    }

    public String assertTotalPrice(){
        return driver.findElement(total_Price).getText();
    }

    public void checkTerms(){
        driver.findElement(terms).click();
    }

    public void navigateToCheckOutPageAsUser(){
        driver.findElement(checkOutButton).click();
    }

    public void navigateToCheckOutPageAsGuest(){
        driver.findElement(guestButton).click();
    }

    public void enterUserBillingAddressData(String country, String state, String city, String address, String code, String phone){
        select = new Select(driver.findElement(countryList));
        select.selectByVisibleText(country);
        select = new Select(driver.findElement(stateList));
        select.selectByVisibleText(state);
        driver.findElement(city_Text).sendKeys(city);
        driver.findElement(address_Text).sendKeys(address);
        driver.findElement(postal_Code).sendKeys(code);
        driver.findElement(phone_Number).sendKeys(phone);
        driver.findElement(continueButton).click();
    }

    public void enterGuestBillingAddressData(String firstName, String lastName, String email, String country,
                                             String state, String city, String address, String code, String phone){
        driver.findElement(first_Name).sendKeys(firstName);
        driver.findElement(last_Name).sendKeys(lastName);
        driver.findElement(eMail).sendKeys(email);
        select = new Select(driver.findElement(countryList));
        select.selectByVisibleText(country);
        select = new Select(driver.findElement(stateList));
        select.selectByVisibleText(state);
        driver.findElement(city_Text).sendKeys(city);
        driver.findElement(address_Text).sendKeys(address);
        driver.findElement(postal_Code).sendKeys(code);
        driver.findElement(phone_Number).sendKeys(phone);
        driver.findElement(continueButton).click();
    }

    public void selectShippingMethod(){
        driver.findElement(shippingMethod).click();
        driver.findElement(shippingContinueButton).click();
    }

    public void selectPaymentMethod(){
        driver.findElement(paymentMethod).click();
        driver.findElement(paymentContinueButton).click();
    }

    public void checkPaymentInfo(){
        driver.findElement(paymentInfoContinueButton).click();
    }

    public void confirmCheckOut(){
        driver.findElement(confirmButton).click();
    }

    public void checkOrderDetails(){
        driver.findElement(order_Details).click();
    }

}
