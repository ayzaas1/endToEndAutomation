package api.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.Driver;

public class Hooks {

    static Logger logger = LogManager.getLogger(Hooks.class);

    @Before
    public static void setUp(Scenario scenario){
        logger.info("STARTING SCENARIO: " + scenario.getName());
        logger.info("========================================");
    }

    @After(order = 1)
    public void attachIfFailed(Scenario scenario) {
        if (Driver.isInitialized() && scenario.isFailed()) {
            try {
                byte[] png = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(png, "image/png", "failure");
            } catch (Exception ignored) {}
        }
        logger.info("ENDING SCENARIO: " + scenario.getName());
        logger.info("========================================");
    }

    @After(order = 0)
    public void tearDown() {
        Driver.closeDriver(); // never calls getDriver() → won’t create a new session
        logger.info("QUITTING THE DRIVER");
    }
}
