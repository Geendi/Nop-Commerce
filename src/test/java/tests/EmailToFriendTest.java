package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utilities.ExcelReader;
import utilities.LoadProperties;

public class EmailToFriendTest extends TestBase{
    String macProduct = LoadProperties.userData.getProperty("macProduct");
    String pageMessage = LoadProperties.userData.getProperty("pageMessage");
    String friendEmail = LoadProperties.userData.getProperty("friendEmail");
    String personalMessage = LoadProperties.userData.getProperty("personalMessage");
    String submittedMessage = LoadProperties.userData.getProperty("submittedMessage");

    HomePage homePage;
    RegistrationPage registrationPage;
    SearchPage searchPage;
    EmailToFriendPage emailToFriendPage;
    ProductDetailsPage detailsPage;


    @Test
    public void navigateToRegisterPage() {
        homePage = new HomePage(driver);
        registrationPage = new RegistrationPage(driver);
        homePage.openRegistrationPage();
        Assert.assertEquals(registrationPage.registrationPageAssertion(), "Register");
    }

    @Test(priority = 1,dataProviderClass = ExcelReader.class, dataProvider = "ExcelData")
    public void registerNewUserForEmailFriend(String firstName, String lastName,String day, String month,String year, String email, String company, String password){
        registrationPage = new RegistrationPage(driver);
        registrationPage.enterUserData(firstName, lastName, day, month, year,
                email, company, password, password);
        Assert.assertEquals(registrationPage.registrationCompleteAssertion(), "Your registration completed");
        registrationPage.clickContinue();
    }

    @Test(priority = 2)
    public void searchForProductUsingAutoSuggest() {
        searchPage = new SearchPage(driver);
        searchPage.productSearchUsingAutoSuggest("MacBook");
        detailsPage = new ProductDetailsPage(driver);
        Assert.assertEquals(detailsPage.productNameResult.getText(), macProduct);
    }

    @Test(priority = 3)
    public void suggestProductToFriend() {
        homePage.openEmailToFriendPage();
        emailToFriendPage = new EmailToFriendPage(driver);
        Assert.assertEquals(emailToFriendPage.verifyEmailToFriendPage(), pageMessage);
        emailToFriendPage.enterDataForEmail(friendEmail, personalMessage);
        Assert.assertEquals(emailToFriendPage.verifySubmittedMessage(), submittedMessage);
        registrationPage.logOut();
    }
}
