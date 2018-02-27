package core;

import config.templates.WebElementConfig;
import org.dom4j.Attribute;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.File;
import java.util.HashMap;

import util.parsers.XMLParser;


/**
 * Created by ShepardPin on 12/2/2018.
 */
public  class Component extends AbstractComponent {



    private int waitingTime = 2;
    private HashMap<String,WebElementConfig> locatorMap;
    private WebElementConfig rootWebElementConfig ;


    public Component(int time , File file)  {
        this.waitingTime = time;
        try {
            this.locatorMap = XMLParser.getXMLMap(file);
            rootWebElementConfig = extractRoot(locatorMap.values().iterator().next());
        } catch (DocumentException e) {
            throw new WebDriverException("Loading Component error , the xml is missing or broken");
        }
    }

    public Component(File file)  {
        this(0,file);
    }

    public void setWaitingTime(int time){
        this.waitingTime = time ;
    }

    public boolean isRootElement(String el_key){
        return this.locatorMap.get(el_key).isComponentRoot();
    }

    public String getRootElementName(){
        return rootWebElementConfig.getName();
    }

    public WebElementConfig getRootWebElementConfig() {
        return rootWebElementConfig;
    }

    public void isComponentExist(WebDriver driver){

        try {
            super.getWebElement(driver,rootWebElementConfig.getName());
        }catch (WebDriverException e){
            // If the driver cannot find the root element , it will throw a WebDriverException error.
            // For notification purpose,
            throw new WebDriverException("The component does NOT exist : "+ rootWebElementConfig );
        }
    }

    private WebElementConfig extractRoot(WebElementConfig webElementConfig){


        WebElementConfig parentWebElement = webElementConfig.getParentWebElementConfig();

        if (parentWebElement!=null){
            extractRoot(parentWebElement);
        }

        return parentWebElement;
    }


    @Override
    protected WebDriverWait configDriverWait(WebDriver driver, WebElementConfig targetElement) {

        int elementWaitTime = targetElement.getWaitTime();

        if (elementWaitTime<=this.waitingTime){
            elementWaitTime = this.waitingTime;
        }

        return new WebDriverWait(driver,elementWaitTime);

    }

    @Override
    protected WebElementConfig getXMLElement(String el_key) {
        return locatorMap.get(el_key);
    }


    @Override
    protected By extractLocator(String el_key) {
        WebElementConfig webElementConfig = locatorMap.get(el_key);
        return super.extractLocator(webElementConfig);
    }



}
