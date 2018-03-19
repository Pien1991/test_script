package testScripts.userAccountSystem;

import config.PagesEnum;
import core.DriverTestScript;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import remote.pages.RegisterPage;
import util.helpers.NavigationHelper;

/**
 * Created by ShepardPin on 27/2/2018.
 */
public class fd_19 extends DriverTestScript{

    @Test
    public void test1()  {
        NavigationHelper.navigate(driver, PagesEnum.REGISTER);
        RegisterPage.Components.REGISTRATION_PANEL.getWebElement(driver,"Email_Input").sendKeys("1234@ff@com");
        RegisterPage.Components.REGISTRATION_PANEL.getWebSelect(driver, "Locations_Select").selectByVisibleText("China");
        WebElement UserTerm = RegisterPage.Components.REGISTRATION_PANEL.getWebElement(driver,"UserTerm_Box");
        new Actions(driver).sendKeys(UserTerm,Keys.END).perform();
        //Assert Accept_Box should be clickable;
        RegisterPage.Components.REGISTRATION_PANEL.getWebElement(driver, "Accept_Box");
        RegisterPage.Components.REGISTRATION_PANEL.getWebElement(driver,"Submit_Button").click();
        Assert.assertEquals(RegisterPage.Components.REGISTRATION_PANEL.getWebElement(driver,"Error_Span").getText(),"Wrong email format，please type as 123456@gmail.com");

    }
}
