package remote.pages;

import config.ComponentImp;
import config.PagesEnum;
import core.PageObject;
import org.openqa.selenium.WebDriver;

/**
 * Created by ShepardPin on 27/2/2018.
 */
public class RegisterPage extends PageObject {

    protected static final String XMLPath = XMLRootPath+"/register";


    public RegisterPage(WebDriver dr) {
        super(dr, PagesEnum.REGISTER);
    }

    @Override
    protected void waitUntilLoaded() {

    }


    public enum Components implements ComponentImp {
        REGISTRATION_PANEL{
            @Override
            public String getPath() {
                return XMLPath+"/RegisterPanel.xml";
            }
        };

    }
}
