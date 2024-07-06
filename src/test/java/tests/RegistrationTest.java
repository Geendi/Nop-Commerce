package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.RegistrationPage;
import utilities.ExcelReader;

public class RegistrationTest extends TestBase{

    HomePage homePage;
    RegistrationPage registrationPage;
    LoginPage loginPage;


    @Test
    public void navigateToRegisterPage() {
        homePage = new HomePage(driver);
        registrationPage = new RegistrationPage(driver);
        homePage.openRegistrationPage();
        Assert.assertEquals(registrationPage.registrationPageAssertion(), "Register");
    }

    @Test(priority = 1,dataProviderClass = ExcelReader.class, dataProvider = "ExcelData")
    public void registerNewUser(String firstName, String lastName,String day, String month,String year, String email, String company, String password){
        registrationPage.enterUserData(firstName, lastName,day, month,year ,email,company,password,password);
        Assert.assertEquals(registrationPage.registrationCompleteAssertion(), "Your registration completed");
        registrationPage.clickContinue();
        registrationPage.logOut();
    }

    @Test(priority = 2,dataProviderClass = ExcelReader.class, dataProvider = "ExcelData")
    public void registeredUserCanLogin(String email, String password) {
        homePage.openLoginPage();
        loginPage = new LoginPage(driver);
        loginPage.userLogin(email, password);
        Assert.assertEquals(loginPage.LoginCompleteMessage(), "My account");
        registrationPage.logOut();
    }

}
