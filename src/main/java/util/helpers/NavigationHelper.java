package util.helpers;


import config.PagesEnum;
import org.openqa.selenium.WebDriver;

/**
 * Created by ShepardPin on 12/2/2018.
 */
public class NavigationHelper {

    private static String urlHeader = "https://";
    //TODO : Now is hard-coded .
    private static String baseUrl = "10.30.92.245:4200" ;



    public static void navigate(WebDriver driver){
       navigate(driver, PagesEnum.FRONT);
    }

    /**
     *
     *  The navigation Helper will ONLY help to navigate to page but it will NOT to wait all element until being loaded .
     *
     */
    public static void navigate(WebDriver driver, PagesEnum page){

        //TODO : Will be refactored .
        driver.get(urlHeader+baseUrl+page.toString());
    }



//    public  static void login(WebDriver driver, TestEnv testEnv, Site site , User user){
//        LoginPage loginPage = new LoginPage(driver,testEnv,site);
//        loginPage.login(user);
//    }
}
