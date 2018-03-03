package remote.pages;

import config.PagesEnum;
import config.ComponentImp;
import core.PageObject;
import org.openqa.selenium.WebDriver;


/**
 * Created by ShepardPin on 13/02/2018.
 */
public class FundsPage extends PageObject {

    protected static final String XMLPath = XMLRootPath+"/funds";

    public FundsPage(WebDriver dr) {
        super(dr, PagesEnum.FUNDS );
    }




    @Override
    protected void waitUntilLoaded() {

    }



    public enum Components implements ComponentImp {
        FIAT_CURRENCIES_PANEL {
            @Override
            public String getPath() {
                return XMLPath+"/FiatCurrenciesPanel.xml";
            }
        },
        DIGITAL_ASSETS_PANEL {
            @Override
            public String getPath() {
                return XMLPath+"/DigitalAssetsPanel.xml";
            }
        };

    }

}

