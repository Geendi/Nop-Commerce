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

public class AddProductReviewTest extends TestBase{

    String macProduct = LoadProperties.userData.getProperty("macProduct");
    String title = LoadProperties.userData.getProperty("title");
    String text = LoadProperties.userData.getProperty("text");
    String completeMessage = LoadProperties.userData.getProperty("completeMessage");

    SearchPage searchPage;
    ProductDetailsPage detailsPage;
    HomePage homePage;
    RegistrationPage registrationPage;
    ProductReviewPage productReviewPage;

    @Test
    public void navigateToRegisterPage() {
        homePage = new HomePage(driver);
        registrationPage = new RegistrationPage(driver);
        homePage.openRegistrationPage();
        Assert.assertEquals(registrationPage.registrationPageAssertion(), "Register");
    }

    @Test(priority = 1,dataProviderClass = ExcelReader.class, dataProvider = "ExcelData")
    public void registerNewUserForReview(String firstName, String lastName,String day, String month,String year, String email, String company, String password){
        registrationPage.enterUserData(firstName, lastName, day, month, year,
                email, company, password, password);
        Assert.assertEquals(registrationPage.registrationCompleteAssertion(), "Your registration completed");
        registrationPage.clickContinue();
    }

    @Test(priority = 2)
    public void searchForProductUsingAutoSuggest() {
        detailsPage = new ProductDetailsPage(driver);
        searchPage = new SearchPage(driver);
        searchPage.productSearchUsingAutoSuggest("MacBook");
        Assert.assertEquals(detailsPage.productNameResult.getText(), macProduct);
    }

    @Test(priority = 3)
    public void addReview() {
        productReviewPage = new ProductReviewPage(driver);
        productReviewPage.addProductReview(title, text);
        Assert.assertEquals(productReviewPage.verifyreviewCompleteMessage(), completeMessage);
        productReviewPage.closeMessage();
    }

    @Test(priority = 4)
    public void signOut() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("p.content")));
        registrationPage.logOut();
    }
}
