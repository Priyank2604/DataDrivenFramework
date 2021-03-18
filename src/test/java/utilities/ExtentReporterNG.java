package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReporterNG {
   static ExtentReports extent;
    public static ExtentReports getReportObject(){
        String path= System.getProperty("user.dir")+"\\reports\\index.html"; // set the path where reports should be displayed after execution

        //creating supported class ExtentSparkReporter and object reporter
        ExtentSparkReporter reporter=new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation Results"); // set report name
        reporter.config().setDocumentTitle("Test Results"); // set report title


        // creating main class ExtentReports and object extent
        extent=new ExtentReports();
        extent.attachReporter(reporter); // main class extent have knowledge of supported class object ExtentSparkReporter (knowledge of configuration)
        extent.setSystemInfo("Tester","Priyank Tailor"); // set tester name on report
        return extent;
    }
}
