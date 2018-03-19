package testScripts.userAccountSystem;

import config.PagesEnum;
import core.DriverTestScript;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;
import remote.pages.RegisterPage;
import util.helpers.NavigationHelper;

/**
 * Created by ShepardPin on 27/2/2018.
 * Will not execute this script as front end is not ready!
 *
 */
public class fd_37 extends DriverTestScript{

    @Test
    public void test1()  {
        NavigationHelper.navigate(driver, PagesEnum.REGISTER);
        RegisterPage.Components.REGISTRATION_PANEL.getWebElement(driver,"Email_Input").sendKeys("12345");
        RegisterPage.Components.REGISTRATION_PANEL.getWebElement(driver, "CancelEmailInput_Button");
    }

    @Test(dependsOnMethods = "test1")
    public void test2() throws InterruptedException {
        RegisterPage.Components.REGISTRATION_PANEL.getWebElement(driver, "CancelEmailInput_Button").click();

        try {
            Thread.sleep(500);
            RegisterPage.Components.REGISTRATION_PANEL.getWebElement(driver, "CancelEmailInput_Button");
            Assert.fail("Should not find 'CancelEmailInput_Button'" ,new TimeoutException());
        }catch (Exception e){

        }


    }


    @Test(dependsOnMethods = "test2")
    public void test3() throws InterruptedException {
        RegisterPage.Components.REGISTRATION_PANEL.getWebElement(driver,"Email_Input").sendKeys("12345");
        RegisterPage.Components.REGISTRATION_PANEL.getWebElement(driver,"Email_Input").sendKeys(Keys.BACK_SPACE);
        RegisterPage.Components.REGISTRATION_PANEL.getWebElement(driver,"Email_Input").sendKeys(Keys.BACK_SPACE);
        RegisterPage.Components.REGISTRATION_PANEL.getWebElement(driver,"Email_Input").sendKeys(Keys.BACK_SPACE);
        RegisterPage.Components.REGISTRATION_PANEL.getWebElement(driver,"Email_Input").sendKeys(Keys.BACK_SPACE);
        RegisterPage.Components.REGISTRATION_PANEL.getWebElement(driver,"Email_Input").sendKeys(Keys.BACK_SPACE);



        try {
            Thread.sleep(500);
            RegisterPage.Components.REGISTRATION_PANEL.getWebElement(driver, "CancelEmailInput_Button");
            Assert.fail("Should not find 'CancelEmailInput_Button'" ,new TimeoutException());
        }catch (Exception e){

        }
    }

}
