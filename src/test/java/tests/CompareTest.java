package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ComparePage;
import pages.ProductDetailsPage;
import pages.SearchPage;
import utilities.LoadProperties;

import java.time.Duration;

public class CompareTest extends TestBase{
    String macProduct = LoadProperties.userData.getProperty("macProduct");
    String asusProduct = LoadProperties.userData.getProperty("asusProduct");

    SearchPage searchPage;
    ProductDetailsPage detailsPage;
    ComparePage comparePage;


    @Test
    public void searchForFirstCompareProduct() {
        searchPage = new SearchPage(driver);
        detailsPage = new ProductDetailsPage(driver);
        searchPage.productSearchUsingAutoSuggest("MacBook");
        Assert.assertEquals(detailsPage.productNameResult.getText(), macProduct);
    }

    @Test(priority = 1)
    public void addFirstItemToCompareList() {
        comparePage = new ComparePage(driver);
        comparePage.addItemToCompare();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.bar-notification.success")));
        Assert.assertTrue(comparePage.assertAddedToCompareMessage().contains("The product has been added to your "));
        comparePage.dismissNotification();
    }

    @Test(priority = 2)
    public void searchForSecondCompareProduct() {
        searchPage.productSearchUsingAutoSuggest("Asus");
        Assert.assertEquals(detailsPage.productNameResult.getText(), asusProduct);
    }

    @Test(priority = 3)
    public void addSecondItemToCompareList() {
        comparePage.addItemToCompare();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.bar-notification.success")));
        Assert.assertTrue(comparePage.assertAddedToCompareMessage().contains("The product has been added to your "));
        comparePage.navigateToComparePage();
        Assert.assertEquals(comparePage.assertComparePage(), "Compare products");
    }

    @Test(priority = 4)
    public void CompareProducts() {
        comparePage.compareProducts();
    }

    @Test(priority = 5)
    public void clearCompareList() {
        comparePage.removeAllItemFromCompareList();
        Assert.assertTrue(comparePage.assertRemoveItemMessage().contains("You have no items to compare."));
    }
}
