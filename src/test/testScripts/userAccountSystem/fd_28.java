package testScripts.userAccountSystem;

import config.PagesEnum;
import core.DriverTestScript;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;
import remote.pages.RegisterPage;
import remote.pages.SigninPage;
import util.helpers.NavigationHelper;
import util.helpers.UsersHelper;

/**
 * Created by ShepardPin on 2/3/2018.
 */
public class fd_28 extends DriverTestScript{

    @Test
    public void test1() throws Exception {
        SigninPage page = (SigninPage) NavigationHelper.navigate(driver, PagesEnum.SIGNIN);
        page.enterEmail(UsersHelper.getUserByAlias("verified_non_kyc_user").getUsername());


        try {
            page.clickSubmitButton();
            Assert.fail("Submit button should Not be clickable" ,new TimeoutException());
        }catch (Exception e){

        }
    }
}
