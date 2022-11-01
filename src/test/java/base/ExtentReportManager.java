package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

    private ExtentReports reports;
    private ExtentTest test;

    private static ExtentReportManager manager =new ExtentReportManager();

    private ExtentReportManager(){}


    public static ExtentReportManager getInstance(){
        return manager;
    }

    public void configReport(){
        ExtentSparkReporter sparkReporter=new ExtentSparkReporter("./reports/API-report.html");
        sparkReporter.config().setReportName("API");
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("PetStore API");
        reports = new ExtentReports();
        reports.setSystemInfo("OS", System.getProperty("os.name"));
        reports.setSystemInfo("URI","https://petstore.swagger.io/v2");
        reports.attachReporter(sparkReporter);
    }

    public ExtentReportManager createTest(String testname){
        if(reports!=null) {
            test = reports.createTest(testname);
        }else{
            System.out.println("report is null");
        }
        return manager;
    }

    public ExtentReportManager info(String message){
        if(test!=null && reports!=null){
            test.log(Status.INFO, message);
        }
        return manager;
    }

    public void flushReport(){
        if(reports!=null){
            reports.flush();
        }
    }

}
