package ui.pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import utils.Driver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

public abstract class BasePage implements Page {

    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final Actions actions;
    protected Faker faker;

    protected BasePage() {
        this.driver = Driver.getDriver();              // Inheritance + Encapsulation
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.actions = new Actions(driver);
        this.faker = new Faker();
    }


    protected  WebElement waitVisible(WebElement element){
        return wait.until(ExpectedConditions.visibilityOf(element));
    }


    // ---------- Common wait helpers ----------
    protected WebElement waitVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected WebElement waitClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    //for url wait
    protected boolean waitUrlContains(String content){
        return wait.until(ExpectedConditions.urlContains(content));
    }


    protected boolean waitGone(By locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    protected <V> V waitUntil(Function<? super WebDriver, V> condition) {
        return wait.until(condition);
    }

    // ---------- Common element actions ----------
    protected void click(By locator) {
        waitClickable(locator).click();
    }

    protected void type(By locator, CharSequence text) {
        WebElement el = waitVisible(locator);
        el.clear();
        el.sendKeys(text);
    }

    protected String text(By locator) {
        return waitVisible(locator).getText();
    }

    protected boolean isDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // ---------- Navigation ----------
    protected void goTo(String url) {
        driver.navigate().to(url);
    }


    //----------------------UI UTILS---------------------------------------------------------------
    public static void clickJavascript(WebDriver driver, WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    public static void scrollWithJS(WebDriver driver, WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void waitForElementAndClick(WebDriver driver, WebElement element, int seconds){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public static void selectFromDropdown(WebDriver driver, WebElement dropdown, String attribute, String optionToBeSelected){
        Select select = new Select(dropdown);

        switch (attribute){
            case "value":
                select.selectByValue(optionToBeSelected);
                break;
            case "index":
                select.selectByIndex(Integer.parseInt(optionToBeSelected));
                break;
            case "text":
                select.selectByVisibleText(optionToBeSelected);
                break;
            default:
                throw new IllegalArgumentException("Couldn't select value from dropdown! Please provide attribute: value, index or text!");

        }

    }

    public static void hover(WebDriver driver, WebElement element, int secondsToHover) {
        new Actions(driver).moveToElement(element).pause(Duration.ofSeconds(secondsToHover)).perform();
    }

    public static void doubleClick(WebDriver driver, WebElement element) {
        new Actions(driver).doubleClick(element).perform();
    }

    public static void rightClick(WebDriver driver, WebElement element) {
        new Actions(driver).contextClick(element).perform();
    }

    public static void dragAndDrop(WebDriver driver, WebElement source, WebElement target) {
        new Actions(driver).dragAndDrop(source, target).perform();
    }

    /** Clear anything inside the field first, then type. */
    public static void clearAndType(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Take a PNG screenshot, save to /screenshots, return absolute path.
     * Nice for attaching to Allure/Extent reports.
     */
    public static String takeScreenshot(WebDriver driver, String baseName) {

        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String fileName  = baseName + "_" + timeStamp + ".png";
        Path targetPath  = Paths.get(System.getProperty("user.dir"), "screenshots", fileName);

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.createDirectories(targetPath.getParent());
            Files.copy(src.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            return targetPath.toString();
        } catch (IOException e) {
            Assert.fail("Unable to save screenshot: " + e.getMessage());
            return null; // won’t be reached because of Assert.fail
        }
    }





}



