package config;

import core.PageObject;
import org.openqa.selenium.WebDriver;
import remote.pages.FundsPage;
import remote.pages.OrdersPage;
import remote.pages.RegisterPage;
import remote.pages.SigninPage;

/**
 * Created by ShepardPin on 12/2/2018.
 */
public enum PagesEnum {
    FRONT(""),
    FUNDS("/funds"),
    ORDERS("/orders"),
    REGISTER("/register"),
    SIGNIN("/login");


    private final String name;

    private PagesEnum(String s) {
        name = s;
    }

    public String toString() {
        return this.name;
    }


    public  PageObject getPageObject(WebDriver driver){
        switch (this){
            case FUNDS: return new FundsPage(driver);
            case ORDERS: return new OrdersPage(driver);
            case REGISTER: return new RegisterPage(driver);
            case SIGNIN: return new SigninPage(driver);
            default: throw  new IllegalArgumentException("Now we don't support this page ");
        }
    }
}
