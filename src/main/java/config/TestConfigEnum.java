package config;

/**
 * Created by ShepardPin on 21/2/2018.
 */
public enum  TestConfigEnum {

    USERNAME ("USERNAME"),
    USERALIAS ("USERALIAS"),
    DRIVER("DRIVER"),
    SYSTEM("SYSTEM"),
    TEST_PROJECT("TEST_PROJECT"),
    IS_CLOSE_BROWSER("IS_CLOSE_BROWSER"),
    TEST_PLAN("TEST_PLAN");


    private final String name;

    private TestConfigEnum(String s) {
        name = s;
    }

    public String toString() {
        return this.name;
    }
}
