package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.PropertyFinancePage;
import utils.extentReports.ExtentManager;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class TestBase {

    public static WebDriver driver;
    public static SoftAssert softAssert;
    public static PropertyFinancePage propertyFinancePage;

    @BeforeMethod
    public void setup(){

        driver  = new ChromeDriver();
        softAssert = new SoftAssert();
        propertyFinancePage = new PropertyFinancePage(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }


    @AfterSuite
    public void afterSuite(){

         Desktop desktop = Desktop.getDesktop();

        //let's try to open the report after running the test suite
        File file = new File("./extent-reports/extent-report.html");
        if(file.exists()) {
            try {
                desktop.open(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
