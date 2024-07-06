package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.MyAccountPage;
import pages.RegistrationPage;
import utilities.ExcelReader;
import utilities.LoadProperties;

import java.time.Duration;

public class MyAccountTest extends TestBase{

    String newPassword = LoadProperties.userData.getProperty("newPassword");

    HomePage homePage;
    RegistrationPage registrationPage;
    MyAccountPage myAccountPage;
    LoginPage loginPage;

    @Test
    public void navigateToRegisterPage() {
        homePage = new HomePage(driver);
        registrationPage = new RegistrationPage(driver);
        homePage.openRegistrationPage();
        Assert.assertEquals(registrationPage.registrationPageAssertion(), "Register");
    }

    @Test(priority = 1,dataProviderClass = ExcelReader.class, dataProvider = "ExcelData")
    public void registerNewUserForMyAccount(String firstName, String lastName, String day, String month, String year, String email, String company, String password){
        registrationPage = new RegistrationPage(driver);
        registrationPage.enterUserData(firstName, lastName, day, month, year,
                email, company, password, password);
        Assert.assertEquals(registrationPage.registrationCompleteAssertion(), "Your registration completed");
        registrationPage.clickContinue();
    }

    @Test(priority = 2,dataProviderClass = ExcelReader.class, dataProvider = "ExcelData")
    public void registeredUserCanChangePw(String password) {
        myAccountPage = new MyAccountPage(driver);
        myAccountPage.openMyAccountPage();
        myAccountPage.openChangePasswordPage();
        Assert.assertTrue(myAccountPage.changePwAssertionMessage().contains("Change password"));
        myAccountPage.changePassword(password, newPassword, newPassword);
        Assert.assertEquals(myAccountPage.changePwCompleteAssertion(), "Password was changed");
        myAccountPage.dismissCompleteMessage();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.tagName("p")));
        registrationPage.logOut();
    }

    @Test(priority = 3, dataProviderClass = ExcelReader.class, dataProvider = "ExcelData")
    public void loginWithNewPassword(String email) {
        homePage.openLoginPage();
        loginPage = new LoginPage(driver);
        loginPage.userLogin(email, newPassword);
        Assert.assertEquals(loginPage.LoginCompleteMessage(), "My account");
        registrationPage.logOut();
    }
}
