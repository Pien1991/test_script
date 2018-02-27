package config.templates;

import com.opencsv.bean.CsvBindByName;


/**
 * Created by ShepardPin on 12/2/2018.
 */
public class DriverConfig implements DataTemplate {

    @CsvBindByName
    private String id = null;
    @CsvBindByName
    private String driverEnum = null;
    @CsvBindByName
    private String system = null;
    @CsvBindByName
    private String driverPath = null;

    public String getId() {
        return id;
    }

    public String getDriverPath() {
        return driverPath;
    }

    public String getDriverEnum() {
        return driverEnum;
    }

    public String getSystem() {
        return system;
    }

    public void setDriverPath(String driverPath) {
        this.driverPath = driverPath;
    }

    public void setDriverEnum(String driverEnum) {
        this.driverEnum = driverEnum;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    @Override
    public boolean equals(Object obj) {

        String compareString = obj.toString();
        if (compareString.equals(id)){
            return true;
        }
        else if (compareString.equals(driverEnum)){
            return true;
        }
        else if (compareString.equals(driverPath)){
            return true;
        }
        else if (compareString.equals(system)){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean equals(DriverConfig driverConfig) {

        if (driverConfig.getId().equals(id)){
            return true;
        }
        else if (driverConfig.getDriverEnum().equals(driverEnum) && driverConfig.getSystem().equals(system)){
            return true;
        }
        else if (driverConfig.getDriverPath().equals(driverPath)){
            return true;
        }
        else {
            return false;
        }
    }


    @Override
    public String toString() {
        return "Driver Configuration - Driver Type : "+ this.driverEnum + " , System : "+this.system+" , path : "+this.driverPath;
    }
}
