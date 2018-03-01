package debug;



import org.testng.ITestContext;
import org.testng.annotations.Test;


/**
 * Created by ShepardPin on 13/2/2018.
 */
public class fd_1  {

    @Test()
    public void Test1(ITestContext context){
        System.out.println("fd_1.Testing 1");
        throw new IllegalArgumentException("Fuck");
    }

    @Test(dependsOnMethods = "Test1")
    public void Test2(ITestContext context){
        System.out.println("fd_1.Testing 2");
    }



}
