package utils.listeners;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testCases.TestBase;
import utils.extentReports.ExtentManager;
import java.util.Objects;

import static utils.extentReports.ExtentTestManager.getTest;

public class TestListener extends TestBase implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        iTestContext.setAttribute("WebDriver", driver);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        //Do tier down operations for ExtentReports reporting!
        ExtentManager.extentReports.flush();
    }
    @Override
    public void onTestStart(ITestResult iTestResult) {
    }
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        //ExtentReports log operation for passed tests.
        getTest().log(Status.PASS, "Test passed");
    }
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        //Get driver from BaseTest and assign to local webdriver variable.
        Object testClass = iTestResult.getInstance();
        //Take base64Screenshot screenshot for extent reports
        String base64Screenshot =
                "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
        //ExtentReports log and screenshot operations for failed tests.
        getTest().log(Status.FAIL, "Test Failed",
                getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
    }
    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        //ExtentReports log operation for skipped tests.
        getTest().log(Status.SKIP, "Test Skipped");
    }
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

}
