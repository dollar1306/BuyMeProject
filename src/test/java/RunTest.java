import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.testng.FileAssert.fail;

public class RunTest {

    private static WebDriver driver;
    private static ExtentReports extent;
    private static ExtentTest test;
    private static WebElement element;

    @BeforeClass
    public static void runBeforeClass() throws Exception {
        String cwd = System.getProperty("user.dir");
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(cwd + "\\extent.html");
        // attach reporter
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        // name your test and add description
        test = extent.createTest("MyFirstTest", "Sample description");
        // add custom system info
        extent.setSystemInfo("Environment", "Production");
        extent.setSystemInfo("Tester", "Daniel");
        // log results
        test.log(Status.INFO, "@Before class");

        boolean driverEstablish = false;
        try {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\Alex\\AppData\\Local\\Google\\Chrome\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driverEstablish = true;
        } catch (Exception e) {
            e.printStackTrace();
            fail("Cant connect chromeDriver");
            test.log(Status.FAIL, "Driver Connection Failed! " + e.getMessage());
            driverEstablish = false;
        } finally {
            if (driverEstablish) {
                test.log(Status.PASS, "Driver established successfully");
            }
        }
    }




    @Test
    public void RegistrationScreen(){
        driver.get("https://buyme.co.il/");
        element=driver.findElement(By.partialLinkText("כניסה"));//Sign in--registration
        element.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        if(driver.findElement(By.xpath("//span[.='כניסה ל-BUYME']")).equals(driver.findElement(By.xpath("//*[contains(text(),'כניסה ל-BUYME')]")))){//Sign in
//      element = driver.findElement (By.xpath ("//*[contains(text(),'כניסה ל-BUYME')]"));
            driver.findElement(By.cssSelector("input[placeholder='מייל']")).sendKeys("dollar1306@gmail.com");//Email
            driver.findElement(By.cssSelector("input[placeholder='סיסמה']")).sendKeys("Alex1234");//Password;
            driver.findElement(By.cssSelector("button[type='submit']")).click();


        } else if(driver.findElement(By.cssSelector("//span[.='הרשמה ל-BUYME']")).equals(driver.findElement(By.xpath("//*[contains(text(),'הרשמה ל-BUYME')]")))){//registration
            driver.findElement(By.cssSelector("#ember1376")).sendKeys("Alex Alex");//Name
            driver.findElement(By.cssSelector("#ember1379")).sendKeys("dollar1306@gmail.com");//Email
            driver.findElement(By.cssSelector("#ember1382")).sendKeys("Alex1234");//Password
            driver.findElement(By.cssSelector("#ember1385")).sendKeys("Alex1234");//Password Authentication
            driver.findElement(By.cssSelector("#ember1387")).click();
        }
    }





    @AfterClass
    public static void close () {
        test.log(Status.INFO, "@After test " + "After test methods");
        //driver.quit();
        extent.flush();
    }

    private static String takeScreenShot (String ImagesPath){
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(ImagesPath +".png");
        try {
            FileUtils.copyFile(screenShotFile, destinationFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ImagesPath + ".png";
    }
}
