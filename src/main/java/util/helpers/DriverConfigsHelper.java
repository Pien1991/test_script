package util.helpers;

import config.DriverEnum;
import config.SystemEnum;
import config.templates.DataTemplate;
import config.templates.DriverConfig;
import util.parsers.CSVParser;

import java.io.File;
import java.util.List;

/**
 * Created by ShepardPin on 13/2/2018.
 */
public class DriverConfigsHelper extends DataHelper{

    //TODO : hard-coded
    private static String path = getRootPath()+"/driverConfigs.csv";
    private static List<DataTemplate> driverConfigs ;

    static {
        driverConfigs = CSVParser.getData(new DriverConfig() , new File(path));
    }

    public static  DriverConfig getDriverConfigByDriverEnum(DriverEnum driverType , SystemEnum systemEnum){
        return (DriverConfig) getDataByConditions(driverConfigs,driverType,systemEnum).get(0);
    }

    public static  DriverConfig getDriverConfigById(int i){
        return (DriverConfig) getDataByConditions(driverConfigs,i).get(0);
    }


}
