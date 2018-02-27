package config;

/**
 * Created by ShepardPin on 12/2/2018.
 *  We do not support different browser size in this moment . 18/04/2017
 */
public enum DriverEnum {

        FIREFOX("FIREFOX"),
        CHROME("CHROME"),
        SAFARI("SAFARI");




    private final String name;

    private DriverEnum(String s) {
        name = s;
    }

    public String toString() {
        return this.name;
    }

    public static DriverEnum getDriverType(String driver){
        switch (driver){
            case "FIREFOX"      :   return DriverEnum.FIREFOX;
            case "CHROME"      :   return DriverEnum.CHROME;
            case "SAFARI"      :   return DriverEnum.SAFARI;
            default         :   throw new IllegalArgumentException("Did not support this driver yet : "+driver);
        }
    }






}
