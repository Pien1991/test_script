package core;

import config.DriverEnum;
import config.templates.DriverConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import util.ReportEventHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ShepardPin on 12/2/2018.
 */
public class DriverManager {


    private static ThreadLocal<Map<DriverConfig,WebDriver>> ThreadDriver = new ThreadLocal<>();



    // For each thread , there is a HashMap which contains different driverConfig-WebDriver pair .
    public static synchronized WebDriver getDriver(DriverConfig driverConfig) {
        setThreadDriver(driverConfig);
        return ThreadDriver.get().get(driverConfig);
    }

    public static synchronized void setThreadDriver(DriverConfig driverConfig) {


        WebDriver driver = null;

        if (ThreadDriver.get()==null){
            ThreadDriver.set(new HashMap());
        }

        if (ThreadDriver.get().get(driverConfig)==null){
            driver = BrowserFactory.createBrowser(driverConfig);
            // Add event handler.
            EventFiringWebDriver eventDriver = new EventFiringWebDriver(driver);
            eventDriver.register(new ReportEventHandler());
            ThreadDriver.get().put(driverConfig,eventDriver);
        }

    }

}

