package Base;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utilities.ExcelReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {
    public static WebDriver driver;
    public static Properties config=new Properties();
    public static Properties OR=new Properties();
    public static FileInputStream fis;
    public static Logger log= LogManager.getLogger(TestBase.class.getName());
//    public static ExcelReader excel=new ExcelReader(System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
    public static ExcelReader excel=new ExcelReader("G:\\FrameworkTest\\src\\test\\resources\\excel\\testdata.xlsx");
    public static WebDriverWait wait;
    @BeforeSuite
    public void setUp(){
        if (driver==null) {
//            FileInputStream fis = null;
            try {
//                fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
                fis = new FileInputStream("G:\\FrameworkTest\\src\\test\\resources\\properties\\Config.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                config.load(fis);
                log.info("Config file loaded !!!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
//                fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
                fis = new FileInputStream("G:\\FrameworkTest\\src\\test\\resources\\properties\\OR.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                OR.load(fis);
                log.info("OR file loaded !!!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (config.getProperty("browser").equals("firefox")) {
                System.setProperty("webdriver.gecko.driver", "F:\\Automation Practice\\BrowserDriver\\geckodriver-v0.28.0-win64\\geckodriver.exe");
                driver = new FirefoxDriver();
            } else if (config.getProperty("browser").equals("chrome")) {
                System.setProperty("webdriver.chrome.driver", "F:\\Automation Practice\\BrowserDriver\\chromedriver_win32\\chromedriver.exe");
                driver = new ChromeDriver();
                log.info("Chrome Launched !!!");
            } else if (config.getProperty("browser").equals("ie")) {
                System.setProperty("webdriver.ie.driver", "F:\\Automation Practice\\IEDriverServer_x64_3.150.1\\IEDriverServer.exe");
                driver = new InternetExplorerDriver();
            }
            driver.get(config.getProperty("testsiteurl"));
            log.info("Navigated to :"+config.getProperty("testsiteurl"));
            driver.manage().window().fullscreen();
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
            wait=new WebDriverWait(driver,5);
        }
    }

    public boolean isElementPresent(By by){
try {
    driver.findElement(by);
    return true;
}catch (NoSuchElementException e){
    return false;
}
    }
    @AfterSuite
    public void tearDown(){
        if (driver!=null){
            driver.quit();

        }
        log.info("Test Execution Completed !!!");
    }
}
