package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class PageBase {

    //ToDo add driver
    public Select select;
    public Actions action;

    //ToDo create constructor
    public PageBase(WebDriver driver){
        PageFactory.initElements(driver, this);
    }


}
