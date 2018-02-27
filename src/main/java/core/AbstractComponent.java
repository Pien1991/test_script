package core;

import config.templates.WebElementConfig;
import org.dom4j.Attribute;
import org.dom4j.Element;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.ISelect;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Stack;

import util.EnhancedExpectedCondition;
import util.EnhancedExpectedConditions;
import config.LocatorEnum;
import util.MdSelect;

/**
 * Created by ShepardPin on 12/2/2018.
 */
public abstract class AbstractComponent {

    protected abstract WebDriverWait configDriverWait(WebDriver driver, WebElementConfig targetElement);
    protected abstract WebElementConfig getXMLElement(String el_key);
    protected abstract By extractLocator(String el_key );

    public  final WebElement getWebElement(WebDriver dr, String el_key, EnhancedExpectedCondition expectedCondition){
        //Get element from XML .
        WebElementConfig element = getXMLElement(el_key);
        if (element==null){
            throw new IllegalArgumentException("Cannot match the key from XML map , Please check the key : "+el_key);
        }
        //Put into stack
        Stack<WebElementConfig> stack = loadStack(element);
        //search the element
        WebElement webElement = searchWebElement(stack,dr,expectedCondition);
        if (webElement==null){
            throw  new WebDriverException("No WebElement can be found , please check the element key : "+el_key);
        }

        return webElement;
    }

    public  final WebElement getWebElement(WebDriver dr, String el_key){
        return this.getWebElement(dr,el_key,null);
    }

    private  Stack loadStack(WebElementConfig element){
        Stack stack = new Stack();
        while (!element.isComponentRoot()){
            stack.push(element);
            element=element.getParentWebElementConfig();
        }
        if (element.isComponentRoot()){
            stack.push(element);
        }
        if (stack.size()==0){
            throw  new IllegalStateException("No element can be found , please check the element key : "+element.getName());
        }

        return stack;
    }

    private  WebElement searchWebElement(Stack<WebElementConfig> stack,WebDriver dr,EnhancedExpectedCondition expectedCondition){
        WebDriverWait wait = null;

        WebElement webElement = null;

        while (!stack.empty()) {
            //Pop the element from the top of stack
            WebElementConfig targetElement = stack.pop();
            //Get the By locator from element
            By by = extractLocator(targetElement.getName());
            // Set up the driver wait object
            wait = configDriverWait(dr,targetElement);

            //Wait the element until the condition achieved
            if (targetElement.isComponentRoot()){
                waitExpectedConditions(wait,by,expectedCondition);
                webElement = dr.findElement(by);
            }else {
                waitExpectedConditions(wait,webElement,by,expectedCondition);
                webElement = webElement.findElement(by);
            }
        }

        return webElement;
    }


    /**
     *
     *  If the EnhancedExpectedCondition is null , the default condition to determine the existence of the element is to check
     *  whether it is clickable based on itself or on it's parent element.
     *
     *  If user want another definition or condition on existence of a element , user can create an EnhancedExpectedCondition
     *  for his/her own purpose . Eg, Only when the element is visible then we say it exists .
     */
    protected  void waitExpectedConditions(WebDriverWait wait,WebElement parentElement, By by,EnhancedExpectedCondition enhancedExpectedCondition){

        //TODO : Need refactoring
        if(enhancedExpectedCondition==null ){
            // If there is NOT specified expected condition , we will use default one which is whether the element is clickable.
            if (parentElement == null){
                // If there is NOT specified parent element , the testing element assume to be the one described by 'By'
                wait.until(ExpectedConditions.elementToBeClickable(by));
            }else {
                // If there IS specified parent element , it will test whether the element can be clickable inside parent element .
                // However , the original ExpectedConditions provided from selenium does NOT have such function .
                // Therefore , we use the EnhancedExpectedConditions to store tailor-made functions .
                wait.until(EnhancedExpectedConditions.clickableForNestedElementsLocatedBy(parentElement,by));
            }
        }else {

            // There is another problem on  original ExpectedConditions is that we cannot dynamically form a ExpectedConditions during runtime.
            // To make it more dynamical , we can use injectConditionFactors function in EnhancedExpectedCondition .
            // It will inject required factors to EnhancedExpectedCondition during runtime .

            if (parentElement == null){
                wait.until(enhancedExpectedCondition.injectConditionFactors(by , null));
            }else {
                wait.until(enhancedExpectedCondition.injectConditionFactors(by , parentElement));

            }
        }

    }

    protected   void waitExpectedConditions(WebDriverWait wait, By by,EnhancedExpectedCondition enhancedExpectedCondition){
        waitExpectedConditions(wait,null,by,enhancedExpectedCondition);
    }



    protected   By extractLocator(WebElementConfig webElementConfig ){
        String method = webElementConfig.getMethod();
        if (method==null){
            throw new IllegalArgumentException("The element do not have Method attribute in xml "+webElementConfig.getName());
        }
        LocatorEnum type = LocatorEnum.valueOf(method);

        String path = webElementConfig.getPath();

        if (path==null){
            throw new IllegalArgumentException("The element do not have Path attribute in xml "+webElementConfig.getName());
        }

        return LocatorEnum.getLocator(type,path);
    }

    public  final ISelect getSelectElement(WebDriver dr, String el_key){
        return this.getSelectElement(dr,el_key,null);
    }

    public  final ISelect getSelectElement(WebDriver dr, String el_key, EnhancedExpectedCondition expectedCondition){

        WebElement selectElement = this.getWebElement(dr,el_key,expectedCondition);
        if (selectElement.getTagName().equals("select")){
            return new Select(selectElement);
        }else if (selectElement.getTagName().equals("md-select")){

            // A special process on card selection
            WebElement menuElements =null;
            try {
                //Click the select element and pop up the menu , which the html will show.
                selectElement.click();
                /*Assume that one page only will exist one md-select-menu!!*/
                By by = By.xpath("//md-select-menu[parent::div[@aria-hidden='false']]");
                new WebDriverWait(dr,3).until(ExpectedConditions.presenceOfElementLocated(by));
                menuElements = dr.findElement(by);
            } finally {
                /*Also assume that the menuElement will not disappear after pressing ESCAPE but only being hidden*/
                new Actions(dr).sendKeys(Keys.ESCAPE).perform();
            }
            return new MdSelect(selectElement,menuElements);
        }else {
            throw new IllegalArgumentException("The element is not a select , please check the key : " +el_key);
        }
    }


}
