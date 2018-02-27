package core;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import util.SslUtils;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ShepardPin on 22/2/2018.
 */
public class TestLinkManager implements TestReportManager {



    private static URL SERVER_URL;


    private static TestLinkAPI api;

    //Shepard 's  key
    private static final String DEV_KEY =  "74f33d9844f7bab28f0164bb44beb8b8";



    private TestLinkManager() {
    }

    public static synchronized TestLinkAPI getTestLinkClient() {
        if (api == null) createInstance();
        return api;
    }

    private static TestLinkAPI createInstance() {
        try {
            SERVER_URL = new URL("https://testlink.fintend.com/lib/api/xmlrpc/v1/xmlrpc.php");
            // To avoid PKIX path building failed exception which causes by CA issue
            if("https".equalsIgnoreCase(SERVER_URL.getProtocol())){
                SslUtils.ignoreSsl();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        api = new TestLinkAPI(SERVER_URL,DEV_KEY);
        return api;
    }


}
