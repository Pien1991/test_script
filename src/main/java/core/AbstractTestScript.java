package core;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;


/**
 * Created by ShepardPin on 12/2/2018.
 */
public abstract class AbstractTestScript {

    protected WebDriver driver;


    @BeforeTest()
    public void CreateBrowser(){
//        DriverConfig driverConfig = DriverConfigsHelper.getDriverConfigByDriverEnum(DriverEnum.CHROME, SystemEnum.MAC);
//        driver = DriverManager.getDriver(driverConfig);

    }

//    @AfterTest(alwaysRun = true)
//    public void CloseBrowser(){
//        driver.close();
//    }
}
