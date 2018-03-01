package debug;

import config.PagesEnum;
import core.DriverTestScript;
import org.testng.annotations.Test;
import util.helpers.NavigationHelper;

/**
 * Created by ShepardPin on 1/3/2018.
 */
public class signin  extends DriverTestScript{

    @Test
    public  void  test(){
        NavigationHelper.navigate(driver, PagesEnum.SIGNIN);
    }
}
