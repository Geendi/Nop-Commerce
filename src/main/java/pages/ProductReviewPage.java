package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductReviewPage extends PageBase{

    WebDriver driver;

    public ProductReviewPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    //ToDo adding locators
    //--------------------------------------------------------------------------------
    private final By review_Title = By.id("AddProductReview_Title");
    private final By review_Text = By.id("AddProductReview_ReviewText");
    private final By rating = By.id("addproductrating_4");
    private final By submitButton = By.id("add-review");
    private final By reviewCompleteMessage = By.cssSelector("p.content");
    private final By dismissButton = By.cssSelector("span.close");
    //--------------------------------------------------------------------------------
    //ToDo adding methods
    public void addProductReview(String reviewTitle, String reviewText){
        driver.findElement(review_Title).sendKeys(reviewTitle);
        driver.findElement(review_Text).sendKeys(reviewText);
        driver.findElement(rating).click();
        driver.findElement(submitButton).click();
    }

    public String verifyreviewCompleteMessage(){
        return driver.findElement(reviewCompleteMessage).getText();
    }

    public void closeMessage(){
        driver.findElement(dismissButton).click();
    }



}
