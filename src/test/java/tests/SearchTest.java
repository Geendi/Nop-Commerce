package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProductDetailsPage;
import pages.SearchPage;
import utilities.LoadProperties;

public class SearchTest extends TestBase{

    String macProduct = LoadProperties.userData.getProperty("macProduct");

    SearchPage searchPage;
    ProductDetailsPage detailsPage;

    @Test
    public void searchForProduct() {
        searchPage = new SearchPage(driver);
        detailsPage = new ProductDetailsPage(driver);
        searchPage.productSearch(macProduct);
        searchPage.openProductDetails();
        Assert.assertEquals(detailsPage.productNameResult.getText(), macProduct);
    }

    @Test(priority = 1)
    public void searchForProductUsingAutoSuggest() {
        searchPage.productSearchUsingAutoSuggest("MacBook");
        Assert.assertEquals(detailsPage.productNameResult.getText(), macProduct);
    }

}
