package core;

import config.DriverEnum;
import config.templates.DriverConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;


/**
 * Created by ShepardPin on 12/2/2018.
 */
public class BrowserFactory {


    //TODO
    public  static WebDriver createBrowser(DriverConfig driverConfig) {

        DriverEnum driverEnum =  DriverEnum.valueOf(driverConfig.getDriverEnum());

        switch (driverEnum) {
            case CHROME:
                System.setProperty("webdriver.chrome.driver", driverConfig.getDriverPath());
                return new ChromeDriver();

            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", driverConfig.getDriverPath());
                return new FirefoxDriver();

            case SAFARI:
                return new SafariDriver(new SafariOptions());
            default:
                throw new NullPointerException("Do not support this driver : " + driverConfig.getDriverEnum());
        }
    }

}
