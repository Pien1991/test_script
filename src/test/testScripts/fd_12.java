package testScripts;

import config.PagesEnum;
import core.DriverTestScript;
import org.testng.Assert;
import org.testng.annotations.Test;
import remote.pages.RegisterPage;
import util.helpers.NavigationHelper;

/**
 * Created by ShepardPin on 27/2/2018.
 */
public class fd_12 extends DriverTestScript{

    @Test
    public void test1()  {
        NavigationHelper.navigate(driver, PagesEnum.REGISTER);
        RegisterPage.Components.REGISTRATION_PANEL.getWebElement(driver,"Email_Input").sendKeys("1234.com");
        RegisterPage.Components.REGISTRATION_PANEL.getWebSelect(driver, "Locations_Select").selectByVisibleText("China");
        RegisterPage.Components.REGISTRATION_PANEL.getWebElement(driver,"Submit_Button").click();
        Assert.assertEquals(RegisterPage.Components.REGISTRATION_PANEL.getWebElement(driver,"Error_Span").getText(),"Wrong email format，please type as 123456@gmail.com");

    }
}
