package util.helpers;

import config.DriverEnum;
import config.SystemEnum;
import config.TestConfigEnum;
import config.templates.DriverConfig;
import config.templates.User;
import org.testng.IClass;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlTest;

import java.util.List;
import java.util.Map;

/**
 * Created by ShepardPin on 21/2/2018.
 */
public class TestNgXMLHelper {

    public static DriverEnum getDriverEnum(ITestContext context){
        String driverName = getXmlParameter(context,TestConfigEnum.DRIVER);
        if (driverName == null){
            throw new IllegalArgumentException("Cannot identify driver information from TestNG XML !");
        }
        return DriverEnum.valueOf(driverName);
    }

    public static DriverEnum getDriverEnum(ITestResult result){
        return getDriverEnum(result.getTestContext());
    }


    public static SystemEnum getSystemEnum(ITestContext context){
        String systemName = getXmlParameter(context,TestConfigEnum.SYSTEM);
        if (systemName == null){
            throw new IllegalArgumentException("Cannot identify operation system information from TestNG XML !");
        }
        return SystemEnum.valueOf(systemName);
    }

    public static boolean getIsCloseBrowser(ITestContext context){
        String is_close_browser = getXmlParameter(context,TestConfigEnum.IS_CLOSE_BROWSER);
        if (is_close_browser == null){
            return false;
        }else {
            return Boolean.valueOf(is_close_browser.toLowerCase());
        }
    }

    public static SystemEnum getSystemEnum(ITestResult result){
        return getSystemEnum(result.getTestContext());
    }

    public static DriverConfig getDriverConfig(ITestContext context){
        return DriverConfigsHelper.getDriverConfigByDriverEnum(getDriverEnum(context),getSystemEnum(context));
    }

    public static DriverConfig getDriverConfig(ITestResult result){
        ITestContext context = result.getTestContext();
        return DriverConfigsHelper.getDriverConfigByDriverEnum(getDriverEnum(context),getSystemEnum(context));
    }

    public static User getUser(ITestContext context){

        /*
         *
         * There will be a scenario that there is two same parameter in different test suite level :
         * 1. Global parameter in xml suite level
         * 2. Local parameter in test suite level
         *
         * Therefore , the return parameter should be local one if it exists .
         *
         *
         */

        Map<String, String> localMap = context.getCurrentXmlTest().getLocalParameters();
        if (localMap!=null){

            String localAlias  = localMap.get(TestConfigEnum.USERALIAS.toString());
            if (localAlias!=null){
                return UsersHelper.getUserByAlias(localAlias);
            }

            String localName = localMap.get(TestConfigEnum.USERNAME.toString());
            if (localName!=null){
                return UsersHelper.getUserByUsername(localName);
            }

        }

        String globalName  = getXmlParameter(context, TestConfigEnum.USERNAME);
        String globalAlias = getXmlParameter(context, TestConfigEnum.USERALIAS);

        if (globalName!=null){
            return   UsersHelper.getUserByUsername(globalName);

        }
        else if (globalAlias!=null){
            return   UsersHelper.getUserByAlias(globalAlias);
        }
        else {
            throw new IllegalArgumentException("Cannot identify any user information from TestNG XML");
        }



    }


    public static String getXmlParameter(ITestContext context , TestConfigEnum key){
        /*
         * In TestNg , getXmlParameter will search local para .
         * If it is null , it will return global parameter .
         *
         */
        return context.getCurrentXmlTest().getParameter(key.toString());
    }

    public static String getXmlParameter(ISuite iSuite , TestConfigEnum key){
        /*
         * In TestNg , getXmlParameter will search local para .
         * If it is null , it will return global parameter .
         *
         */
        return iSuite.getParameter(key.toString());
    }

    /**
     *
     * @param className
     * @return the string that is as same as test case id stored in TestLink
     */

    public static String extractTestIdFromClassName(String className){
        if (className==null){
            throw new IllegalArgumentException(" The class path should NOT be null , cannot get test case name");
        }

        //Since class path has such format : src.aa.pp.test. We need to split it by "."
        String [] strings = className.split("\\.");
        //The last string in class path is the name of test case
        int last_element_number = strings.length-1;
        //In testLink , test case id is like aa-1 and test case in java is aa_1 , therefore need to modify .
        return strings[last_element_number].replaceFirst("_","-") ;
    }

    public static String extractTestIdFromClassName(ITestResult iTestResult){
        return extractTestIdFromClassName(iTestResult.getTestClass().getName());
    }

}
