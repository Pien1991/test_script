package config;

/**
 * Created by ShepardPin on 13/2/2018.
 */
public enum SystemEnum {
    MAC("MAC"),
    WINDOWS("WINDOWS"),
    UBUNTU("UBUNTU");

    private final String name;

    private SystemEnum(String s) {
        name = s;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static SystemEnum getOS(String OS){
        switch (OS){
            case "MAC"      :   return SystemEnum.MAC;
            case "WINDOWS"      :   return SystemEnum.WINDOWS;
            case "UBUNTU"      :   return SystemEnum.UBUNTU;
            default         :   throw new IllegalArgumentException("We do not support this OS : "+OS);
        }
    }
}
