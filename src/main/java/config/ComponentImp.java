package config;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.EnhancedExpectedCondition;
import util.helpers.ComponentsHelper;

/**
 * Created by ShepardPin on 13/2/2018.
 */
public  interface ComponentImp {

     String getPath();

     default  WebElement getWebElement(WebDriver driver , String el_key){
          return ComponentsHelper.getComponent(this).getWebElement(driver,el_key);
     }

     default  WebElement getWebElement(WebDriver driver , String el_key, EnhancedExpectedCondition expectedCondition){
          return ComponentsHelper.getComponent(this).getWebElement(driver,el_key,expectedCondition);
     }

}

