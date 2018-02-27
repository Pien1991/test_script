package util;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;


/**
 * Created by ShepardPin on 12/2/2018.
 */
//TODO
public class ReportEventHandler implements WebDriverEventListener {
    @Override
    public void beforeAlertAccept(WebDriver webDriver) {

    }

    @Override
    public void afterAlertAccept(WebDriver webDriver) {

    }

    @Override
    public void afterAlertDismiss(WebDriver webDriver) {

    }

    @Override
    public void beforeAlertDismiss(WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateTo(String s, WebDriver webDriver) {
    }

    @Override
    public void afterNavigateTo(String s, WebDriver webDriver) {
//        ExtentManager.log(Status.INFO,"FINISH : Navigation - current url : "+webDriver.getCurrentUrl());
    }

    @Override
    public void beforeNavigateBack(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateBack(WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateForward(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateForward(WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateRefresh(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateRefresh(WebDriver webDriver) {

    }

    @Override
    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {
//        ExtentManager.log(Status.DEBUG,"START : Finding Element  - by locator : "+by.toString());
    }

    @Override
    public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {
//        ExtentManager.log(Status.DEBUG,"FINISH : Element FOUND - by locator : "+by.toString());

    }

    @Override
    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {
//        ExtentManager.log(Status.DEBUG,"START : Click Element  : "+webElement.getTagName());

    }

    @Override
    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
//        try {
//            ExtentManager.log(Status.INFO,"FINISH : Click Element  : "+webElement.getTagName());
//        }catch (StaleElementReferenceException e){
//            ExtentManager.log(Status.WARNING,"The element is not attached to the page document any more after the button clicked");
//        }
    }

    @Override
    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {

    }

    @Override
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
    }

    @Override
    public void beforeScript(String s, WebDriver webDriver) {

    }

    @Override
    public void afterScript(String s, WebDriver webDriver) {

    }

    @Override
    public void onException(Throwable throwable, WebDriver webDriver) {
//        ExtentManager.log(Status.WARNING,"DETECT : "+ ExceptionUtil.getExceptionHeadline(throwable));
    }


}
