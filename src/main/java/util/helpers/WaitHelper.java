package util.helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by ShepardPin on 13/2/2018.
 */
public class WaitHelper {


    public static void waitUntilSplashLoaded(WebDriver driver){
        By loadingPanelLocator = By.id("splash");
        try {
            new WebDriverWait(driver,2).until(ExpectedConditions.presenceOfElementLocated(loadingPanelLocator));
        }catch (TimeoutException e){
            System.out.println("During loading ,Splash element do not exist");
            //ExtentManager.log(Status.DEBUG,"During loading ,Splash element do not exist");
        }
        try {
            WebElement loadingPanel = driver.findElement(loadingPanelLocator);
            new WebDriverWait(driver,60).until(ExpectedConditions.stalenessOf(loadingPanel));
        }catch (NoSuchElementException e){
            System.out.println("During loading ,Splash element do not exist");

            //ExtentManager.log(Status.DEBUG,"During loading ,Splash element do not exist");
        }

    }

    public static void waitUntilLocalLoadingPanelLoaded(WebDriver driver){
        By loadingPanelLocator = By.xpath("//div[@ng-show='isLoading']");
        try{
            new WebDriverWait(driver,3).until(ExpectedConditions.presenceOfElementLocated(loadingPanelLocator));
            new WebDriverWait(driver,15).until(ExpectedConditions.attributeToBe(loadingPanelLocator,"aria-hidden","true"));
        }catch (WebDriverException e){

        }
    }
}
