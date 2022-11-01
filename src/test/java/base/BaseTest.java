package base;

import RequestUtil.RequestUtil;
import io.restassured.RestAssured;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    public RequestUtil requestUtil;

    @BeforeSuite
    public void setBase(){
        ExtentReportManager.getInstance().configReport();
    }

    @BeforeMethod
    public void configBM(ITestResult result){
        ExtentReportManager.getInstance().createTest(result.getMethod().getMethodName());
        requestUtil = new RequestUtil("https://petstore.swagger.io/v2");
    }

    @AfterMethod
    public void configAM(){}

    @AfterSuite
    public void closeBase(){
    ExtentReportManager.getInstance().flushReport();
    }
}
