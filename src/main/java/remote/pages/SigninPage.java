package remote.pages;

import config.ComponentImp;
import config.PagesEnum;
import core.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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


    public void signin(String email , String password) {
        enterEmail(email);
        enterPassword(password);
        clickSubmitButton();
        new WebDriverWait(driver,6).until(ExpectedConditions.urlContains("succeed"));

    }

    public void enterPassword( String password) {
        SigninPage.Components.SIGNIN_PANEL.getWebElement(driver,"Password_Input").sendKeys(password);
    }

    public void enterEmail(String email ) {
        SigninPage.Components.SIGNIN_PANEL.getWebElement(driver,"Email_Input").sendKeys(email);
    }

    public void clickSubmitButton(){
        SigninPage.Components.SIGNIN_PANEL.getWebElement(driver,"Submit_Input").click();
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
