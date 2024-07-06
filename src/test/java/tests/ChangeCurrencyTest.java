package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductDetailsPage;
import pages.SearchPage;
import utilities.LoadProperties;

public class ChangeCurrencyTest extends TestBase{
    HomePage homePage;
    ProductDetailsPage detailsPage;
    SearchPage searchPage;

    String macProduct = LoadProperties.userData.getProperty("macProduct");

    @Test
    public void changeCurrency() {
        homePage = new HomePage(driver);
        detailsPage = new ProductDetailsPage(driver);
        homePage.changeCurrencyFunction("Euro");
    }

    //   -$-  -€-
    @Test(priority = 1)
    public void searchForProductUsingAutoSuggest() {
        searchPage = new SearchPage(driver);
        searchPage.productSearchUsingAutoSuggest("MacBook");
        Assert.assertEquals(detailsPage.productNameResult.getText(), macProduct);
        Assert.assertTrue(detailsPage.productPriceLabel.getText().contains("€"));
        System.out.println(detailsPage.productPriceLabel.getText());
    }
}
