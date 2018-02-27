package listeners;


import br.eti.kinoshita.testlinkjavaapi.model.TestCase;
import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;
import config.ClassTestResult;
import config.TestConfigEnum;
import config.templates.DriverConfig;
import org.testng.*;
import org.testng.xml.XmlClass;
import util.helpers.TestLinkHelper;
import util.helpers.TestNgXMLHelper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * Created by ShepardPin on 22/2/2018.
 */
public class TestLinkReporter implements ISuiteListener,ITestListener {


    private static  Map<DriverConfig,List<TestCase>> testCasesMap ;


    private  static ThreadLocal<Map<XmlClass, ClassTestResult>>classTestResultMap =  new ThreadLocal();


    private static  TestPlan testPlan ;


    @Override
    public void onStart(ISuite iSuite) {
        String testProjectName = TestNgXMLHelper.getXmlParameter(iSuite, TestConfigEnum.TEST_PROJECT);
        String testPlanName = TestNgXMLHelper.getXmlParameter(iSuite, TestConfigEnum.TEST_PLAN);

        this.testPlan = TestLinkHelper.getTestPlan(testPlanName,testProjectName);

        if (this.testPlan == null){
            throw new IllegalArgumentException("Cannot get Test Plan from TestLink !!");
        }else {
            this.testCasesMap = TestLinkHelper.getTestCasesPerDriverConfig(testPlan,null);
        }
    }

    @Override
    public void onFinish(ISuite iSuite) {
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {

        String onGoingTestCaseId = TestNgXMLHelper.extractTestIdFromClassName(iTestResult);

        DriverConfig driverConfig = TestNgXMLHelper.getDriverConfig(iTestResult);
        List<TestCase> testCases = testCasesMap.get(driverConfig);
        if (testCases!=null){
            Iterator<TestCase> it = testCases.iterator();
            boolean isSame = false ;
            while (it.hasNext()){
                TestCase testCase = it.next();
                if (testCase.getFullExternalId().equals(onGoingTestCaseId)){
                    isSame =true;
                    break;
                }
            }
            if (isSame==false){
                iTestResult.setStatus(ITestResult.SKIP);
            }
        }else {
            System.out.println("Cannot get any test cases from TestLink");
            iTestResult.setStatus(ITestResult.SKIP);
        }

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        classTestResultMap.get().put(iTestResult.getTestClass().getXmlClass(),ClassTestResult.SUCCESS);
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        classTestResultMap.get().put(iTestResult.getTestClass().getXmlClass(),ClassTestResult.FAILURE);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        XmlClass xmlClass = iTestResult.getTestClass().getXmlClass();
        if (classTestResultMap.get().get(xmlClass)!=ClassTestResult.FAILURE){
            classTestResultMap.get().put(iTestResult.getTestClass().getXmlClass(),ClassTestResult.SKIP);
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        if (classTestResultMap.get()==null){
            classTestResultMap.set(new HashMap());
        }



        iTestContext.getCurrentXmlTest().getPackages().forEach((xmlPackage -> xmlPackage.getXmlClasses().forEach((XmlClass xmlClass) -> {
            classTestResultMap.get().putIfAbsent(xmlClass,ClassTestResult.STARTED);
        })));
    }

    @Override
    public void onFinish(ITestContext iTestContext) {



        classTestResultMap.get().forEach((k,v)-> System.out.format("For Test : %s , Class : %s , Result : %s \n",iTestContext.getCurrentXmlTest().getName(),k.getName(),v));
    }


}
