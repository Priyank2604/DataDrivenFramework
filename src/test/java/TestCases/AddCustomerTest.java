package TestCases;

import Base.TestBase;
import com.aventstack.extentreports.ExtentReports;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.ExtentReporterNG;

public class AddCustomerTest extends TestBase {
    ExtentReports extent= ExtentReporterNG.getReportObject();

    @Test(dataProvider = "getData")
    public void addCustomer(String  firstName, String lastName, String postCode, String alerttext) throws InterruptedException {
        extent.createTest("Add Customer");
        driver.findElement(By.cssSelector(OR.getProperty("addCustBtn"))).click();
        driver.findElement(By.cssSelector(OR.getProperty("firstName"))).sendKeys(firstName);
        driver.findElement(By.cssSelector(OR.getProperty("lastName"))).sendKeys(lastName);
        driver.findElement(By.cssSelector(OR.getProperty("postCode"))).sendKeys(postCode);
        driver.findElement(By.cssSelector(OR.getProperty("addBtn"))).click();
//        driver.switchTo().alert().accept();
        Alert alert=wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertTrue(alert.getText().contains(alerttext));
        alert.accept();
        Thread.sleep(3000);
        extent.flush();
    }
    @DataProvider
    public Object[][] getData(){
        String sheetName ="AddCustomerTest";
        int rows=excel.getRowCount(sheetName);
        int cols=excel.getColumnCount(sheetName);
        Object[][] data=new Object[rows-1][cols];

        for (int rowNum = 2; rowNum <= rows; rowNum++){ //2
            for (int colNum = 0; colNum < cols; colNum++){
                // data [0] [0]
                data[rowNum -2] [colNum] =excel.getCellData(sheetName, colNum, rowNum);//2
            }
        }
return data;



    }
}

