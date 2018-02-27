package config;

import org.dom4j.Element;
import org.openqa.selenium.By;

/**
 * Created by ShepardPin on 12/2/2018.
 */
public enum LocatorEnum {
    Id,
    Name,
    CssSelector,
    LinkText,
    PartialLinkText,
    TagName,
    Xpath;

    public static By getLocator(LocatorEnum type , String locatorValue){


        switch (type){
            case Id                 :   return By.id(locatorValue);
            case Name               :   return By.name(locatorValue);
            case CssSelector        :   return By.cssSelector(locatorValue);
            case LinkText           :   return By.linkText(locatorValue);
            case PartialLinkText    :   return By.partialLinkText(locatorValue);
            case TagName            :   return By.tagName(locatorValue);
            case Xpath              :   return By.xpath(locatorValue);
            default                 :   throw new IllegalArgumentException
                    ("No such method :  "+type+ " - > to locate element : "+locatorValue );
        }
    }

}
