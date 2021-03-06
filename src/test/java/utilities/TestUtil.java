package utilities;

import Base.TestBase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class TestUtil extends TestBase {
    public static String screenshotPath;
    public static String screenshotName;
    public static void cpaturescreenshot() throws IOException {
        File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        Date d=new Date();
        screenshotName=d.toString().replace(":","_").replace(" ","_")+".jpg";
        FileUtils.copyFile(srcFile,new File(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\"+screenshotName));
    }
}
