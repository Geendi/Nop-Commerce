package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.WishlistPage;
import utilities.LoadProperties;

import java.time.Duration;

public class WishListTest extends TestBase{

    String macProduct = LoadProperties.userData.getProperty("macProduct");

    SearchPage searchPage;
    ProductDetailsPage detailsPage;
    WishlistPage wishlistPage;

    @Test
    public void searchForProductUsingAutoSuggest() {
        searchPage = new SearchPage(driver);
        detailsPage = new ProductDetailsPage(driver);
        searchPage.productSearchUsingAutoSuggest("MacBook");
        Assert.assertEquals(detailsPage.productNameResult.getText(), macProduct);
    }

    @Test(priority = 1)
    public void addToWishListAndNavigateUsingPopup() {
        wishlistPage = new WishlistPage(driver);
        wishlistPage.addItemToWishList();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.bar-notification.success")));
        Assert.assertTrue(wishlistPage.assertAddedMessage().contains("The product has been added to your "));
        wishlistPage.navigateToWishListPageViaPopUp();
        Assert.assertEquals(wishlistPage.assertWishListPage(), "Wishlist");
        Assert.assertEquals(wishlistPage.assertProductName(), macProduct);
        wishlistPage.updateWishList();
        wishlistPage.removeItemFromWishList();
        Assert.assertEquals(wishlistPage.assertRemoveMessage(), "The wishlist is empty!");
    }

    @Test(priority = 3)
    public void addToWishListAndNavigateUsingSectionButton() {
        searchPage.productSearchUsingAutoSuggest("MacBook");
        wishlistPage.addItemToWishList();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.bar-notification.success")));
        Assert.assertTrue(wishlistPage.assertAddedMessage().contains("The product has been added to your "));
        wishlistPage.navigateToWishListPageViaSectionButton();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.bar-notification.success")));
        Assert.assertEquals(wishlistPage.assertWishListPage(), "Wishlist");
        Assert.assertEquals(wishlistPage.assertProductName(), macProduct);
        wishlistPage.updateWishList();
        wishlistPage.removeItemFromWishList();
        Assert.assertEquals(wishlistPage.assertRemoveMessage(), "The wishlist is empty!");
    }
}
