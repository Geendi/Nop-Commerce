package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddToCartPage;
import pages.ProductDetailsPage;
import pages.SearchPage;
import utilities.LoadProperties;

import java.time.Duration;

public class AddToCartTest extends TestBase{
    SearchPage searchPage;
    ProductDetailsPage detailsPage;
    AddToCartPage addToCartPage;

    String macProduct = LoadProperties.userData.getProperty("macProduct");

    @Test
    public void searchForProductUsingAutoSuggest() {
        searchPage = new SearchPage(driver);
        detailsPage = new ProductDetailsPage(driver);
        searchPage.productSearchUsingAutoSuggest("MacBook");
        Assert.assertEquals(detailsPage.productNameResult.getText(), macProduct);
    }

    @Test(priority = 1)
    public void addProductToCart() {
        addToCartPage = new AddToCartPage(driver);
        addToCartPage.addToCart();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.bar-notification.success")));
        Assert.assertTrue(addToCartPage.assertAddedToCartMessage().contains("The product has been added to your "));
        addToCartPage.navigateToShoppingCartUsingPopUp();
        Assert.assertEquals(addToCartPage.assertProductName(), macProduct);
        addToCartPage.increaseQuantity();
        addToCartPage.increaseQuantity();
        addToCartPage.decreaseQuantity();
        Assert.assertEquals(addToCartPage.assertShoppingCartPage(),"Shopping cart");
        addToCartPage.continueShopping();
        Assert.assertEquals(addToCartPage.assertContinueShoppingMessage(), "Featured products");
        addToCartPage.navigateToShoppingCartUsingSectionButton();
    }

    @Test(priority = 2)
    public void removeProductsFromCart() {
        addToCartPage.removeFromCart();
        Assert.assertEquals(addToCartPage.assertRemoveItemMessage(), "Your Shopping Cart is empty!");
    }
}
