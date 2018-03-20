package testScripts.userAccountSystem;

import config.PagesEnum;
import core.DriverTestScript;
import org.testng.Assert;
import org.testng.annotations.Test;
import remote.pages.SigninPage;
import util.helpers.NavigationHelper;
import util.helpers.UsersHelper;

/**
 * Created by ShepardPin on 2/3/2018.
 */
public class fd_27 extends DriverTestScript{


    @Test
    public void test1() throws Exception {
        SigninPage page = (SigninPage) NavigationHelper.navigate(driver, PagesEnum.SIGNIN);
        page.enterEmail(UsersHelper.getUserByAlias("verified_non_kyc_user").getUsername());
        page.enterPassword("123456");
        page.clickSubmitButton();
        Assert.assertEquals
                (SigninPage.Components.SIGNIN_PANEL.getWebElement(driver,"ErrorMessage_Text").getText().trim(),
                        "Incorrect username or password");

    }
}
