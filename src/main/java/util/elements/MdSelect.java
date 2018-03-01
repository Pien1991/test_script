package util.elements;

import core.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ISelect;
import org.openqa.selenium.support.ui.Quotes;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;

import java.util.Iterator;
import java.util.List;

/**
 * Created by workstation47 on 12/2/2018.
 *
 * The MdSelect will exist html .
 * How to let it exist is another issue which will NOT handle in this class !
 *
 */
public class MdSelect implements ISelect {

    private final WebElement selectElement;
    private final List<WebElement> menuElements;
    private final boolean isMulti;

    public MdSelect(WebElement select ,List<WebElement> menuElement) {


        if(null != select && select.getAttribute("class").toLowerCase().contains("md-select")) {
            this.selectElement = select;
        } else {
            throw new UnexpectedTagNameException("md-select", select.getAttribute("class"));
        }

        this.menuElements = menuElement;
        this.isMulti = this.getOptions().size()>1? true: false;

    }


    public boolean isMultiple() {
        return this.isMulti;
    }

    public List<WebElement> getOptions() {
        return this.menuElements;
    }

    public List<WebElement> getAllSelectedOptions() {
        //TODO
        return null;
    }

    public WebElement getFirstSelectedOption() {
        return null;
    }

    public void selectByVisibleText(String value) {

        boolean matched = false;


        if(!this.isMultiple()) {
            return;
        }


        Iterator var4 = getOptions().iterator();

        while (var4.hasNext()) {
            WebElement next = (WebElement) var4.next();

            if (next.getText().trim().equals(value)){
                next.click();
                //For safari , the element is invisible. After trying to click the button , browser
                // will scroll down to it and make it visible .Then we can click .
                matched=true;
                break;
            }


        }

        if(!matched) {
            throw new NoSuchElementException("Cannot locate option with value: " + value);
        }

    }

    public void selectByIndex(int index) {
        /*It selects by the index of the location of options*/
        int sizeEx  = index+1;
        if (!this.isMultiple()){
            return;
        }
        List<WebElement> webElements = getOptions();
        if ( webElements.size()<sizeEx){
            return;
        }
        webElements.get(index).click();
    }

    public void selectByValue(String value) {
        //TODO
        return;
    }

    public void deselectAll() {
        //TODO
        return;
    }

    public void deselectByValue(String s) {
        //TODO
        return;
    }

    public void deselectByIndex(int i)  {
        //TODO
        return;
    }

    public void deselectByVisibleText(String s) {
        //TODO
        return;
    }


//    private WebElement waitUntilVisible(WebElement element){
//
//        while (!element.isDisplayed()){
//            try {
//                //Need to wait 0.5 second to wait the browser to scroll down to the tested element and make it visible.
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//            }
//        }
//
//        return element;
//
//
//    }



}
