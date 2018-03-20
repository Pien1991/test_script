package testScripts.funManagementSystem;

import config.PagesEnum;
import config.templates.User;
import core.DriverTestScript;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import remote.pages.FundsPage;
import remote.pages.SigninPage;
import util.helpers.NavigationHelper;
import util.helpers.UsersHelper;

/**
 * Created by ShepardPin on 20/3/2018.
 */
public class fd_41 extends DriverTestScript{

    User user;
    FundsPage page;

    @BeforeClass
    public  void signin(){
        user = UsersHelper.getUserByUsername("shepardpin+coin_address_display_test@fintend.com");

        SigninPage page = (SigninPage) NavigationHelper.navigate(driver,PagesEnum.SIGNIN);
        page.signin(user.getUsername(),user.getPassword());
    }

    /**
     *  Check whether the sequence is correct
     *
     */
    @Test
    public void test1()  {



        page = (FundsPage) NavigationHelper.navigate(driver,PagesEnum.FUNDS);

        page.selectTab(driver, FundsPage.Tabs.FinancialAccount);

        Assert.assertEquals(
            FundsPage.Components.WITHDRAWAL_ADDRESS_ACCOUNTS_PANEL.getListWebElement(driver,"WithdrawalAddress_Label",1)
                .getText() , "BBBBB:");

        Assert.assertEquals(
                FundsPage.Components.WITHDRAWAL_ADDRESS_ACCOUNTS_PANEL.getListWebElement(driver,"WithdrawalAddress_Label",2)
                        .getText() , "AAAAA:");

    }



    @Test(dependsOnMethods = "test1")
    public void test2()  {

        Assert.assertEquals(
                FundsPage.Components.WITHDRAWAL_ADDRESS_ACCOUNTS_PANEL.getListWebElement(driver,"WithdrawalAddress_Currency",1)
                        .getText() , "Currency: ETH");

        Assert.assertEquals(
                FundsPage.Components.WITHDRAWAL_ADDRESS_ACCOUNTS_PANEL.getListWebElement(driver,"Withdrawal_Address",1)
                        .getText() , "Address: 2234567890");

    }

    @Test(dependsOnMethods = "test1")
    public void test3()  {

        Assert.assertEquals(
                FundsPage.Components.WITHDRAWAL_ADDRESS_ACCOUNTS_PANEL.getListWebElement(driver,"WithdrawalAddress_Currency",2)
                        .getText() , "Currency: BTC");

        Assert.assertEquals(
                FundsPage.Components.WITHDRAWAL_ADDRESS_ACCOUNTS_PANEL.getListWebElement(driver,"Withdrawal_Address",2)
                        .getText() , "Address: 1234567890");

    }


}
