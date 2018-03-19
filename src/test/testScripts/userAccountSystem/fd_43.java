package testScripts.userAccountSystem;

import config.PagesEnum;
import core.DriverTestScript;
import org.testng.Assert;
import org.testng.annotations.Test;
import remote.pages.SigninPage;
import util.helpers.NavigationHelper;

/**
 * Created by ShepardPin on 2/3/2018.
 */
public class fd_43 extends DriverTestScript{

    @Test
    public void test1() throws Exception {
        NavigationHelper.navigate(driver, PagesEnum.SIGNIN);
        SigninPage.Components.SIGNIN_PANEL.getWebElement(driver,"Email_Input").sendKeys("1234.com");
        SigninPage.Components.SIGNIN_PANEL.getWebElement(driver,"Password_Input").sendKeys("1234.com");
        SigninPage.Components.SIGNIN_PANEL.getWebElement(driver,"Submit_Input").click();
        Assert.assertEquals
                (SigninPage.Components.SIGNIN_PANEL.getWebElement(driver,"ErrorMessage_Text").getText().trim()
                        ,"Incorrect username format");



    }
}
