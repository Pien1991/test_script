package util;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
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
    private final WebElement menuElement;
    private final boolean isMulti;

    public MdSelect(WebElement select , WebElement menu) {

        String selectElementTagName = select.getTagName();
        String menuElementTagName= menu.getTagName();

        if(null != selectElementTagName && "md-select".equals(selectElementTagName.toLowerCase())) {
            this.selectElement = select;
        } else {
            throw new UnexpectedTagNameException("md-select", selectElementTagName);
        }

        if(null != menuElementTagName && "md-select-menu".equals(menuElementTagName.toLowerCase())) {
            this.menuElement = menu;
            this.isMulti = this.getOptions().size()>1? true: false;
        } else {
            throw new UnexpectedTagNameException("md-select-menu", menuElementTagName);
        }
    }


    public boolean isMultiple() {
        return this.isMulti;
    }

    public List<WebElement> getOptions() {
        By by = By.xpath(".//md-option");
        List<WebElement> webElements = this.menuElement.findElements(by);
        return webElements;
    }

    public List<WebElement> getAllSelectedOptions() {
        By by = By.xpath(".//md-option[@aria-selected='true']");
        List<WebElement> webElements = this.menuElement.findElements(by);
        return webElements;
    }

    public WebElement getFirstSelectedOption() {
        return getAllSelectedOptions().get(0);
    }

    public void selectByVisibleText(String value) {

        boolean matched = false;


        if(!this.isMultiple()) {
            return;
        }


        Iterator var4 = getOptions().iterator();

        while (var4.hasNext()) {
            WebElement next = (WebElement) var4.next();
            System.out.println(next.getText());
            if (next.getText().equals(value)){
                clickSelectElement();
                next.click();
                matched=true;
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
        clickSelectElement();
        webElements.get(index).click();
    }

    public void selectByValue(String value) {

        List options = this.menuElement.findElements(By.xpath(".//md-option[@value = " + Quotes.escape(value) + "]"));

        boolean matched = false;
        if (!this.isMultiple()){
            return;
        }

        Iterator var4 = options.iterator();
        while (var4.hasNext()) {
            WebElement next = (WebElement) var4.next();
            clickSelectElement();
            next.click();
            matched =true;
        }


        if(!matched) {
            throw new NoSuchElementException("Cannot locate option with value: " + value);
        }
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


    private void clickSelectElement(){
        try {
            this.selectElement.click();
            //Need to wait 0.5 second to avoid other element broking the select element.
            Thread.sleep(500);
        }catch (WebDriverException e){
            // Sometime the menu is already pop up
        } catch (InterruptedException e) {
        }
    }



}
