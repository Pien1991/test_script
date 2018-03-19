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
public class fd_2 extends DriverTestScript{

    @Test
    public void test1() throws Exception {
        SigninPage page = (SigninPage) NavigationHelper.navigate(driver, PagesEnum.SIGNIN);
        page.enterEmail("1234@aa.com");
        page.enterPassword("12345678");
        page.clickSubmitButton();
        Assert.assertEquals(
                SigninPage.Components.SIGNIN_PANEL.getWebElement(driver,"ErrorMessage_Text").getText().trim(),
                "Username not exist\n" +
                        "Sign up >");
        SigninPage.Components.SIGNIN_PANEL.getWebElement(driver,"ErrorMessage_Link").click();
        Assert.assertEquals(true,driver.getCurrentUrl().contains("register"));

    }
}
