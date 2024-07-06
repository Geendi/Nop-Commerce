package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utilities.ExcelReader;
import utilities.LoadProperties;

import java.time.Duration;

public class CheckOutAsUserTest extends TestBase{

    String macProduct = LoadProperties.userData.getProperty("macProduct");
    String country = LoadProperties.userData.getProperty("country");
    String state = LoadProperties.userData.getProperty("state");
    String city = LoadProperties.userData.getProperty("city");
    String address = LoadProperties.userData.getProperty("address");
    String postal = LoadProperties.userData.getProperty("postal");
    String phone = LoadProperties.userData.getProperty("phone");

    SearchPage searchPage;
    ProductDetailsPage detailsPage;
    HomePage homePage;
    RegistrationPage registrationPage;
    CheckOutPage checkOutPage;
    AddToCartPage addToCartPage;
    OrderDetailsPage orderDetailsPage;

    @Test
    public void navigateToRegisterPage() {
        homePage = new HomePage(driver);
        registrationPage = new RegistrationPage(driver);
        homePage.openRegistrationPage();
        Assert.assertEquals(registrationPage.registrationPageAssertion(), "Register");
    }

    @Test(priority =1,dataProviderClass = ExcelReader.class, dataProvider = "ExcelData")
    public void registerNewUserForCheckOutUser(String firstName, String lastName,String day, String month,String year, String email, String company, String password){
        registrationPage.enterUserData(firstName, lastName, day, month, year,
                email, company, password, password);
        Assert.assertEquals(registrationPage.registrationCompleteAssertion(), "Your registration completed");
        registrationPage.clickContinue();
    }

    @Test(priority = 2)
    public void searchForProduct() {
        searchPage = new SearchPage(driver);
        detailsPage = new ProductDetailsPage(driver);
        searchPage.productSearchUsingAutoSuggest("MacBook");
        Assert.assertEquals(detailsPage.productNameResult.getText(), macProduct);
    }

    @Test(priority = 3)
    public void addProductToCart() {
        addToCartPage = new AddToCartPage(driver);
        addToCartPage.addToCart();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.bar-notification.success")));
        Assert.assertTrue(addToCartPage.assertAddedToCartMessage().contains("The product has been added to your "));
        addToCartPage.navigateToShoppingCartUsingPopUp();
        Assert.assertEquals(addToCartPage.assertShoppingCartPage(),"Shopping cart");
    }

    @Test(priority = 4)
    public void checkOutProcess() {
        checkOutPage = new CheckOutPage(driver);
        checkOutPage.checkTerms();
        checkOutPage.navigateToCheckOutPageAsUser();
        Assert.assertEquals(checkOutPage.assertCheckOutPage(), "Checkout");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        checkOutPage.enterUserBillingAddressData(country, state, city, address, postal, phone);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        checkOutPage.selectShippingMethod();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        checkOutPage.selectPaymentMethod();
        checkOutPage.checkPaymentInfo();
        Assert.assertTrue(checkOutPage.assertTotalPrice().contains("3,600.00"));
        Assert.assertEquals(detailsPage.product_Name.getText(), macProduct);
        checkOutPage.confirmCheckOut();
        checkOutPage.checkOrderDetails();
        Assert.assertTrue(driver.getCurrentUrl().contains("orderdetails"));
    }

    @Test(priority = 5)
    public void downloadOrderDetails() {
        orderDetailsPage = new OrderDetailsPage(driver);
        orderDetailsPage.downloadOrder();
    }

    @Test(priority = 6)
    public void printOrderDetails() {
        orderDetailsPage.printOrder();
    }

    @Test(priority = 7,dependsOnMethods = {"printOrderDetails"})
    public void userLogOut() {
        registrationPage.logOut();
    }
}
