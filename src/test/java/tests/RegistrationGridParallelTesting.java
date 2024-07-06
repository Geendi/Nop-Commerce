package tests;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.RegistrationPage;

public class RegistrationGridParallelTesting extends TestBase2{

    HomePage homePage;
    RegistrationPage registrationPage;
    LoginPage loginPage;
    Faker fakeData = new Faker();
    String firstName = fakeData.name().firstName();
    String lastName = fakeData.name().lastName();
    String email = fakeData.internet().emailAddress();
    String password = fakeData.number().digits(8).toString();

    @Test(priority=1,alwaysRun=true)
    public void UserCanRegisterSuccssfully(){
        homePage = new HomePage(getDriver());
        homePage.openRegistrationPage();
        registrationPage = new RegistrationPage(getDriver());
        registrationPage.enterUserDataFaker(firstName, lastName,email,password, password);
        Assert.assertTrue(registrationPage.registrationCompleteAssertion().contains("Your registration completed"));
    }

    @Test(dependsOnMethods= {"UserCanRegisterSuccssfully"})
    public void RegisteredUserCanLogout(){
        registrationPage.logOut();
    }

    @Test(dependsOnMethods= {"RegisteredUserCanLogout"})
    public void RegisteredUserCanLogin(){
        homePage.openLoginPage();
        loginPage = new LoginPage(getDriver());
        loginPage.userLogin(email,password);
        Assert.assertEquals(loginPage.LoginCompleteMessage(), "My account");
    }
}
