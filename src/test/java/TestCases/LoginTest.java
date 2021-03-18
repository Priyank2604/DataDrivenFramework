package TestCases;

import Base.TestBase;
import com.aventstack.extentreports.ExtentReports;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utilities.ExtentReporterNG;

public class LoginTest extends TestBase{
    ExtentReports extent= ExtentReporterNG.getReportObject();

    public static Logger log= LogManager.getLogger(LoginTest.class.getName());
    @Test
    public void loginASBankManager() throws InterruptedException {
        extent.createTest("Login Demo");
        log.info("Inside Login Test");
    driver.findElement(By.cssSelector(OR.getProperty("bmlBtn"))).click();
    Thread.sleep(3000);
        Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn"))),"Login not successfull");
        log.info("Login Successfull");
        Reporter.log("Login Succesfully");
//        Assert.fail("Login unsuccessfull");
        extent.flush();
    }
}
