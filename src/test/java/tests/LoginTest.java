package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.RegistrationPage;
import utilities.ExcelReader;

public class LoginTest extends TestBase{

    HomePage homePage;
    LoginPage loginPage;
    RegistrationPage registrationPage;

    @Test(dataProviderClass = ExcelReader.class, dataProvider = "ExcelData")
    public void loginWithValidData(String email, String password) {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        homePage.openLoginPage();
        Assert.assertTrue(loginPage.loginAssertionMessage().contains("Please Sign In!"));
        loginPage.userLogin(email, password);
        Assert.assertEquals(loginPage.LoginCompleteMessage(), "My account");
        registrationPage.logOut();
    }
}
