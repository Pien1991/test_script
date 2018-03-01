package remote.pages;

import config.ComponentImp;
import config.PagesEnum;
import core.PageObject;
import org.openqa.selenium.WebDriver;

/**
 * Created by ShepardPin on 1/3/2018.
 */
public class SigninPage  extends PageObject{

    protected static final String XMLPath = XMLRootPath+"/signin";


    public SigninPage(WebDriver dr) {
        super(dr, PagesEnum.SIGNIN);
    }

    @Override
    protected void waitUntilLoaded() {

    }

    public enum Components implements ComponentImp {
        SIGNIN_PANEL{
            @Override
            public String getPath() {
                return XMLPath+"/SigninPanel.xml";
            }
        };

    }
}
