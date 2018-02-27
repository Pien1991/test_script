package util.helpers;


import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionType;
import br.eti.kinoshita.testlinkjavaapi.constants.TestCaseStatus;
import br.eti.kinoshita.testlinkjavaapi.model.*;
import config.DriverEnum;
import config.SystemEnum;
import config.templates.DriverConfig;
import core.TestLinkManager;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by ShepardPin on 22/2/2018.
 */
public class TestLinkHelper {


    /**
     *  In TestLink , we save platform as MAC.CHROME etc
     */
    public static DriverEnum extractDriverEnumFromPlatform(Platform platform){
        return DriverEnum.valueOf(extractFromPlatform(platform)[1]);
    }

    /**
     *  In TestLink , we save platform as MAC.CHROME etc
     */
    public static SystemEnum extractSystemEnumFromPlatform(Platform platform){
        return SystemEnum.valueOf(extractFromPlatform(platform)[0]);
    }

    public static DriverConfig extractDriverConfigFromPlatform(Platform platform){
        DriverEnum driverEnum = extractDriverEnumFromPlatform(platform);
        SystemEnum systemEnum = extractSystemEnumFromPlatform(platform);
        return DriverConfigsHelper.getDriverConfigByDriverEnum(driverEnum,systemEnum);
    }


    private static String[] extractFromPlatform(Platform platform ){
        if (platform==null){
            throw new IllegalArgumentException(" The platform should NOT be null , cannot get platform information");
        }

        String platformName = platform.getName();
        return platformName.split("\\.");
    }


    public static Platform[] getPlatform(TestPlan testPlan){
        return TestLinkManager.getTestLinkClient().getTestPlanPlatforms(testPlan.getId());
    }

    public static TestPlan getTestPlan(String testPlanName,String projectName){
        return TestLinkManager.getTestLinkClient().getTestPlanByName(testPlanName,projectName);
    }

    public static TestProject getTestProject(String projectName){
        return TestLinkManager.getTestLinkClient().getTestProjectByName(projectName);
    }

    public static TestCase[] getTestCasesFromTestPlan( TestPlan testPlan , ExecutionType executionType){
        return TestLinkManager.getTestLinkClient().getTestCasesForTestPlan(testPlan.getId(),null,null,null,null,null,null,null,executionType,true,null);
    }

    public static TestCase[] getAutomatedTestCasesFromTestPlan( TestPlan testPlan){
        return getTestCasesFromTestPlan(testPlan,ExecutionType.AUTOMATED);
    }

    public static Map<DriverConfig,List<TestCase>> getTestCasesPerDriverConfig(TestPlan testPlan ,ExecutionType executionType){
        Map<DriverConfig,List<TestCase>> map = new HashMap ();

        TestCase [] testCases= getTestCasesFromTestPlan(testPlan,executionType);

        for (int i = 0; i <testCases.length ; i++) {
            System.out.println("fuck");
            TestCase var  = testCases[i];
            Platform platform1 = var.getPlatform();
            DriverConfig config = extractDriverConfigFromPlatform(platform1);

            if (map.get(config)==null){
                List<TestCase> list1 = new LinkedList();
                list1.add(var);
                map.put(config,list1);
            }else {
                map.get(config).add(var);
            }
        }
        return map;
    }


    public static Build getLatestBuild(TestPlan testPlan){
        return TestLinkManager.getTestLinkClient().getLatestBuildForTestPlan(testPlan.getId());
    }

    public static void reportTestCaseResult(TestPlan testPlan , Platform platform , Build build , TestCase testCase, TestCaseStatus testCaseStatus,String notes ){
        TestLinkManager.getTestLinkClient().reportTCResult(null,testCase.getId(),testPlan.getId(),null,build.getId(),null,notes,null,null,platform.getId(),null,null,null);
    }











}
