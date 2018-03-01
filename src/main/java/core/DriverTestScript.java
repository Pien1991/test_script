package core;

import config.DriverEnum;
import config.SystemEnum;
import config.templates.DriverConfig;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import util.helpers.DriverConfigsHelper;
import util.helpers.TestNgXMLHelper;

/**
 * Created by ShepardPin on 27/2/2018.
 */
public class DriverTestScript extends AbstractTestScript{

    protected WebDriver driver;


    @BeforeClass()
    public void CreateBrowser(ITestContext context){

        DriverConfig driverConfig = null;
        try {
            driverConfig = TestNgXMLHelper.getDriverConfig(context);
        }catch (IllegalArgumentException e){
            if (driverConfig==null){
                driverConfig= DriverConfigsHelper.getDriverConfigByDriverEnum(DriverEnum.CHROME, SystemEnum.MAC);
            }
        }



        driver = DriverManager.getDriver(driverConfig);



    }

    @AfterTest(alwaysRun = true)
    public void CloseBrowser(ITestContext context){

        if (TestNgXMLHelper.getIsCloseBrowser(context)){
            driver.close();
        }

    }
}
