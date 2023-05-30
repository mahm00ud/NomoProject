package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static utils.dataDriven.ExcelReader.*;
import static utils.extentReports.ExtentTestManager.startTest;


public class TestCases extends TestBase {

    @Test (priority = 1,description = "Check Nomo is open")
    public void verifyNomoIsOpen(Method method){

        startTest(method.getName(), "Check Nomo is open");

        driver.get(getExcelValueByAttribute("URLs","HomeURL"));
        Assert.assertEquals(driver.getCurrentUrl(),getExcelValueByAttribute("URLs","HomeURL"),"Nomo Website is not open");
    }

    @Test(priority = 2, description = "Check Property fincance calculations")
    public void verifyPropertyFinanceCalculations(Method method) {
        startTest(method.getName(), "Check Property fincance calculations");

        driver.get(getExcelValueByAttribute("URLs","PropertyFinanceURL"));
        propertyFinancePage.clickOnAcceptCookies();

        propertyFinancePage.goToCalculationsFrame();
        propertyFinancePage.insertEstimatedPropertyValue(getExcelValueByAttribute("Finance","PropertyValue"));
        propertyFinancePage.insertEstimatedRentalIncomeValue(getExcelValueByAttribute("Finance","MonthlyRentalIncome"));
        propertyFinancePage.insertDownPaymentValue(getExcelValueByAttribute("Finance","DownPayment"));
        propertyFinancePage.chooseFixedRatePeriod("2 year fixed rate");

        softAssert.assertEquals(propertyFinancePage.getFinanceAmount(),92915,"Finance Amount is not correct");
        softAssert.assertEquals(propertyFinancePage.getMonthlyCostsValue(),458,"Monthly costs are not correct");

        softAssert.assertAll();
    }

    @Test(priority = 3, description = "Check that Msg is displayed under property value field when it's > 3000000 and <= 5000000")
    public void verifyMaximumPropertyValue(Method method) {
        startTest(method.getName(), "Check that Msg is displayed under property value field when it's > 3000000 and <= 5000000");

        driver.get(getExcelValueByAttribute("URLs","PropertyFinanceURL"));
        propertyFinancePage.clickOnAcceptCookies();

        propertyFinancePage.goToCalculationsFrame();
        propertyFinancePage.insertEstimatedPropertyValue("3000001");

        softAssert.assertEquals(propertyFinancePage.getValidationMsgPropertyValue(),"For properties of value over 3,000,000 GBP, please visit\n" +
                "BLME.com","Property Validation Msg isn't displayed");

        softAssert.assertAll();
    }

    @Test(priority = 4, description = "Check that Msg is displayed under Down Payment if finance amount will exceed 1500000")
    public void verifyMaximumFinanceAmountErrorMsg(Method method) {
        startTest(method.getName(), "Check that Msg is displayed under Down Payment if finance amount will exceed 1500000");

        driver.get(getExcelValueByAttribute("URLs","PropertyFinanceURL"));
        propertyFinancePage.clickOnAcceptCookies();

        propertyFinancePage.goToCalculationsFrame();
        propertyFinancePage.insertEstimatedPropertyValue("3000000");
        propertyFinancePage.insertEstimatedRentalIncomeValue("10000");
        propertyFinancePage.insertDownPaymentValue("1400000");

        softAssert.assertEquals(propertyFinancePage.getMaximumFinanceErrorMsg(),"The maximum finance amount is 1,500,000 GBP","Maximum finance Error msg isn't displayed");

        softAssert.assertAll();
    }
}
