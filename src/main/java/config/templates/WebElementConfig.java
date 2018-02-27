package config.templates;


/**
 * Created by ShepardPin on 13/2/2018.
 */
public class WebElementConfig implements DataTemplate{

    private String name = null;
    private String method = null;
    private String path = null;
    private int waitTime = 0;
    private boolean isComponentRoot = false;
    private WebElementConfig parentWebElementConfig = null;

    public void setPath(String path) {
        this.path = path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setWaitTime(String waitTime) {
        this.waitTime = Integer.parseInt(waitTime);
    }

    public int getWaitTime() {
        return waitTime;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public String getMethod() {
        return method;
    }

    public void setComponentRoot(boolean componentRoot) {
        isComponentRoot = componentRoot;
    }

    public boolean isComponentRoot(){
        return isComponentRoot;
    }

    public WebElementConfig getParentWebElementConfig() {
        return parentWebElementConfig;
    }

    public void setParentWebElementConfig(WebElementConfig parentWebElementConfig) {
        this.parentWebElementConfig = parentWebElementConfig;
    }

    @Override
    public String toString() {
        return "WebElement -- Name : "+name+", Method : "+method+", Path : "+path ;
    }
}
