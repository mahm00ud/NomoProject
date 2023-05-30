package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageBase {
    WebDriver driver;
    Actions actions;
    WebDriverWait wait;

    public PageBase(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
    }
    public void insertText(By locator, String text){
        WebElement webElement = driver.findElement(locator);
        wait.until(ExpectedConditions.visibilityOf(webElement));
        webElement.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        webElement.sendKeys(text);
    }
    public void waitClick(By locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(locator))).click();
    }

}
