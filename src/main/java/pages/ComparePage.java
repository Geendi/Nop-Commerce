package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ComparePage extends PageBase{

    WebDriver driver;

    public ComparePage(WebDriver driver) {
        super(driver    );
        this.driver = driver;
    }

    //ToDo adding locators
    //--------------------------------------------------------------------------------
    private final By addToCompareButton = By.cssSelector("button.button-2.add-to-compare-list-button");
    private final By comparePopUp = By.linkText("product comparison");
    private final By dismissButton = By.cssSelector("span.close");
    private final By addedToCompareMessage = By.cssSelector("p.content");
    private final By assertComparePage = By.tagName("h1");
    private final By removeMessage = By.cssSelector("div.no-data");
    private final By clearListButton = By.cssSelector("a.clear-list");

    @FindBy(tagName = "tr")
    public List<WebElement> allRows;

    @FindBy(tagName = "td")
    public List<WebElement> allCols;
    //--------------------------------------------------------------------------------
    //ToDo adding methods
    public void addItemToCompare(){
        driver.findElement(addToCompareButton).click();
    }

    public void removeAllItemFromCompareList(){
        driver.findElement(clearListButton).click();
    }

    public void navigateToComparePage(){
        driver.findElement(comparePopUp).click();
    }

    public void dismissNotification(){
        driver.findElement(dismissButton).click();
    }

    public String assertComparePage(){
        return driver.findElement(assertComparePage).getText();
    }

    public String assertAddedToCompareMessage(){
        return driver.findElement(addedToCompareMessage).getText();
    }

    public String assertRemoveItemMessage(){
        return driver.findElement(removeMessage).getText();
    }

    public void compareProducts(){
        System.out.println(allRows.size());
        for (WebElement row : allRows){
            System.out.println(row.getText() + "\t");
            for (WebElement col : allCols){
                System.out.println(col.getText() + "\t");
            }
        }
    }




}
