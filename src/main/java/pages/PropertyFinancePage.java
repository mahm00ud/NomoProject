package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PropertyFinancePage extends PageBase {

    //Locators
    private By acceptCookiesBtn = By.cssSelector("[class=\"MuiButtonBase-root MuiButton-root MuiButton-contained CookieBannerstyled__StyledButton-bikat5-1 CookieBannerstyled__AcceptCookiesButton-bikat5-2 femXLK lodDwG MuiButton-containedPrimary MuiButton-containedSizeLarge MuiButton-sizeLarge\"]");

    //Calculation Section locators
    private By calculatorFrame = By.cssSelector("[class=\"mc-embedded_Iframe__27PR_\"]");

    private By estimatePropertyField = By.name("estimate-property");
    private By propertyValueValidationMsg = By.cssSelector("[class=\"mc-form_ValidationMsgDefault__YYmwZ\"]");

    private By estimateRentalIncomeField = By.name("estimate-rental-income");

    private By downPaymentField = By.name("down-payment");
    private By maximumFinanceErrorMsg = By.cssSelector("[class=\"mc-form_ValidationMsgError__JNryE\"]");

    private By fixedRatePeriodList = By.cssSelector("[class=\"MuiOutlinedInput-root MuiInputBase-root MuiInputBase-colorPrimary mc-form_MCFormDropdown__M6n_f css-1v4ccyo\"]");

    private By  fixedRatePeriodUL =By.cssSelector("ul[role=\"listbox\"]");

    private By financeAmountValue = By.cssSelector("h2[class=\"mc-results_MCResultsValueOfFinance__CBt1j\"]");
    private By monthlyCostsValue = By.cssSelector("h3[class=\"mc-results_MCResultsMonthlyCosts__pRvOw\"]");

    //Constructor
    public PropertyFinancePage(WebDriver driver) {
        super(driver);
    }

    //Actions
    public void clickOnAcceptCookies(){
        waitClick(acceptCookiesBtn);
    }

    //Actions to fill in Estimated values
    public void goToCalculationsFrame(){
        driver.switchTo().frame(driver.findElement(calculatorFrame));
    }

    public void insertEstimatedPropertyValue(String propertyValue){
        insertText(estimatePropertyField, propertyValue);
    }

    public void insertEstimatedRentalIncomeValue(String rentalValue){
        insertText(estimateRentalIncomeField,rentalValue);
    }

    public void insertDownPaymentValue(String downPaymentValue){
        insertText(downPaymentField,downPaymentValue);
    }

    public void chooseFixedRatePeriod(String listIndex){
        waitClick(fixedRatePeriodList);
        driver.findElement(fixedRatePeriodUL).findElement(By.xpath("//li[text()='"+listIndex.toLowerCase()+"']")).click();
    }

    public int getFinanceAmount(){
        String financeAmountWithCurrency = driver.findElement(financeAmountValue).getText();
        return Integer.parseInt(financeAmountWithCurrency.replaceAll("[^0-9]", ""));
    }

    public int getMonthlyCostsValue(){
        String monthlyCostsWithCurrency = driver.findElement(monthlyCostsValue).getText();
        return Integer.parseInt(monthlyCostsWithCurrency.replaceAll("[^0-9]", ""));
    }

    //Get error msgs text
    public String getValidationMsgPropertyValue(){
        return driver.findElement(propertyValueValidationMsg).getText();
    }

    public String getMaximumFinanceErrorMsg(){
        return driver.findElement(maximumFinanceErrorMsg).getText();
    }

}
