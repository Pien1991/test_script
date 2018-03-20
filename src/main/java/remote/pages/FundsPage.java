package remote.pages;

import config.PagesEnum;
import config.ComponentImp;
import core.PageObject;
import org.openqa.selenium.WebDriver;



/**
 * Created by ShepardPin on 13/02/2018.
 */
public class FundsPage extends PageObject {

    public enum Tabs{
        FiatCurrencies,
        DigitalAssets,
        TransferHistory,
        FinancialAccount;
    }

    protected static final String XMLPath = XMLRootPath+"/funds";

    public FundsPage(WebDriver dr) {
        super(dr, PagesEnum.FUNDS );
    }




    @Override
    protected void waitUntilLoaded() {

    }


    public void  selectTab(WebDriver driver,Tabs tabs){
        switch (tabs){
            case DigitalAssets: Components.FUNDS_TABS_PANEL.getWebElement(driver,"DigitalAssets_Tab").click();
                                break;
            case FiatCurrencies: Components.FUNDS_TABS_PANEL.getWebElement(driver,"FiatCurrencies_Tab").click();
                break;
            case TransferHistory:Components.FUNDS_TABS_PANEL.getWebElement(driver,"TransferHistory_Tab").click();
                break;
            case FinancialAccount:Components.FUNDS_TABS_PANEL.getWebElement(driver,"FinancialAccount_Tab").click();
                break;
        }
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
        },
        WITHDRAWAL_ADDRESS_ACCOUNTS_PANEL{
            @Override
            public String getPath() {
                return XMLPath+"/WithdrawalAddressAccountsPanel.xml";
            }
        },

        FUNDS_TABS_PANEL{
            @Override
            public String getPath() {
                return XMLPath+"/FundsTabsPanel.xml";
            }
        }
    }

}

