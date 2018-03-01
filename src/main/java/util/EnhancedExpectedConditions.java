package util;


import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.util.List;

/**
 * Created by ShepardPin on 12/2/2018.
 *
 * The reasons for EnhancedExpectedConditions is that
 *
 * The original ExpectedConditions provided from selenium does NOT have some functions that we want .
 * Therefore , the class is to store some tailor-made functions for testing purpose.
 * Those functions test element mainly for 3 purposes :
 * 1. Clickable  - whether the element is clickable .
 * 2. Presence - whether the element is presented .
 *
 *
 */
public class EnhancedExpectedConditions {

    private EnhancedExpectedConditions() {
    }

    public static ExpectedCondition<Boolean> clickElementUntilSuccess(final WebElement element){
        return new ExpectedCondition<Boolean>() {

            public Boolean apply(WebDriver driver) {
                try {
                    element.click();
                    return true;
                }catch (WebDriverException e){
                    return false;
                }
            }

            @Override
            public String toString(){
                return "Click element "+ element.toString()+" until success";
            }
        };
    }


    public static ExpectedCondition<WebElement> clickableForNestedElementsLocatedBy(final WebElement element , final By childLocator ){
        return new ExpectedCondition<WebElement>() {
            private ExpectedCondition<List<WebElement>> visibilityOfNestedElementsLocatedBy = ExpectedConditions.visibilityOfNestedElementsLocatedBy(element,childLocator);
            public WebElement apply(WebDriver driver) {
                List<WebElement> elements = this.visibilityOfNestedElementsLocatedBy.apply(driver);
                try {
                    return elements != null && elements.get(0).isEnabled()?elements.get(0):null;
                } catch (StaleElementReferenceException var4) {
                    return null;
                }
            }

            @Override
            public String toString(){
                return "Until the child (Nested element) locator"+childLocator+" can be clickable inside parent element "+element.toString();
            }
        };

    }

    public static EnhancedExpectedCondition<WebElement> presenceOfElementLocated(){
        return new EnhancedExpectedCondition<WebElement>() {

            By by =null ;

            public Function<WebDriver, WebElement> injectConditionFactors(By by, SearchContext... var1) {
                this.by = by;
                if (var1==null){
                    return ExpectedConditions.presenceOfElementLocated(by);
                }else {
                    return ExpectedConditions.presenceOfNestedElementLocatedBy((WebElement) var1[0],by);
                }
            }

            public WebElement apply(SearchContext searchContext) {
                return null;
            }


            @Override
            public String toString(){
                return "Until the locator"+this.by+" is presented ";
            }
        };
    }


    public static ExpectedCondition<Boolean> textContains(final WebElement element, final String expected){
        return new ExpectedCondition() {
            private String currentValue = null;

            public Object apply(Object o) {
                try {
                    this.currentValue = element.getText();
                    return Boolean.valueOf(this.currentValue.contains(expected));
                } catch (Exception var3) {
                    return Boolean.valueOf(false);
                }
            }
            public String toString() {
                return String.format("text to contains  \"%s\". Current text: \"%s\"", new Object[]{expected, this.currentValue});
            }
        };
    }



}
