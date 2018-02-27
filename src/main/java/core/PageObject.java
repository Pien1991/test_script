package core;



import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import util.helpers.NavigationHelper;
import config.PagesEnum;

/**
 * Created by ShepardPin on 12/2/2018.
 */
public abstract class PageObject {


    protected WebDriver driver ;
    protected PagesEnum page;


    protected static final String XMLRootPath = "src/main/resources/locators";


    public PageObject(WebDriver dr, PagesEnum page){
        this.driver = dr;
        this.page = page;
        navigateTo();
    }

    public PageObject(WebDriver dr, String url){
        this.driver = dr;
        this.page = PagesEnum.FRONT;
        this.driver.get(url);
        waitUntilLoaded();
    }


    protected void navigateTo(){
        System.out.println("Before navigation");
        NavigationHelper.navigate(this.driver,this.page);
        System.out.println("Finish navigation");
        pageValidation(this.driver);
        waitUntilLoaded();
    }

    protected void pageValidation( WebDriver dr) {
        try {
            new WebDriverWait(dr, 1).until(ExpectedConditions.urlContains(this.page.toString()));
        } catch (TimeoutException e) {
            throw new WebDriverException("The browser does not navigate to page : " + this.page.toString());
        }
    }



    protected abstract  void waitUntilLoaded();
}
