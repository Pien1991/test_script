package remote.pages;

import config.PagesEnum;
import config.ComponentImp;
import core.PageObject;
import org.openqa.selenium.WebDriver;
import util.helpers.WaitHelper;

/**
 * Created by ShepardPin on 13/2/2018.
 */
public class OrdersPage extends PageObject {

    protected static final String XMLPath = XMLRootPath+"/orders";

    public OrdersPage(WebDriver dr) {
        super(dr, PagesEnum.ORDERS );
    }


    @Override
    protected void waitUntilLoaded() {

    }



    enum Components implements ComponentImp {
        OPEN_ORDER_PANEL{
            @Override
            public String getPath() {
                return XMLPath+"/OpenOrdersPanel.xml";
            }
        };

    }

}