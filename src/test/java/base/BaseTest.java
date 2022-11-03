package base;

import util.RequestUtil;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import util.ResponseUtil;

public class BaseTest {

    public RequestUtil requestUtil;
    public ResponseUtil responseUtil;

    @BeforeSuite
    public void setBase(){
        ExtentReportManager.getInstance().configReport();
    }

    @BeforeMethod
    public void configBM(ITestResult result){
        ExtentReportManager.getInstance().createTest(result.getMethod().getMethodName());
        requestUtil = new RequestUtil("https://petstore.swagger.io/v2");
        responseUtil = new ResponseUtil();
    }

    @AfterMethod
    public void configAM(){}

    @AfterSuite
    public void closeBase(){
    ExtentReportManager.getInstance().flushReport();
    }
}
