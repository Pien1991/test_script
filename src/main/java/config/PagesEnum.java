package config;

/**
 * Created by ShepardPin on 12/2/2018.
 */
public enum PagesEnum {
    FRONT(""),
    FUNDS("/funds"),
    ORDERS("/orders");


    private final String name;

    private PagesEnum(String s) {
        name = s;
    }

    public String toString() {
        return this.name;
    }
}
