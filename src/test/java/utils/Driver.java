package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class Driver {

    static WebDriver driver;

    public static WebDriver getDriver(){

        String browser = ConfigurationReader.getProperty("browser");

        if (driver != null){
            return driver;
        }

        switch (browser){
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "safari":
                driver= new SafariDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser! Please provide:" +
                        "chrome, firefox, edge or safari");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

        return driver;
    }


    public static void shutDownDriver(){
        if (driver != null){
            driver.quit();
            driver = null;
        }
    }




}
