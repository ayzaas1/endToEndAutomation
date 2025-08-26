package utils;

import org.openqa.selenium.WebDriver;

public final class Driver {
    private static final ThreadLocal<WebDriver> TL = new ThreadLocal<>();

    public static WebDriver getDriver() {
        WebDriver d = TL.get();
        if (d == null) {
            d = create();
            TL.set(d);
            d.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(30));
            d.manage().window().maximize();
        }
        return d;
    }

    public static boolean isInitialized() {
        return TL.get() != null;
    }

    public static void closeDriver() {
        WebDriver d = TL.get();
        if (d != null) {
            try {
                try { d.manage().deleteAllCookies(); } catch (Exception ignored) {}
                d.quit();
            } catch (Exception ignored) {
                // swallow to guarantee cleanup
            } finally {
                TL.remove(); // IMPORTANT in parallel
            }
        }
    }

    private static WebDriver create() {
        String browser = ConfigurationReader.getProperty("browser");
        switch (browser) {
            case "chrome": {
                org.openqa.selenium.chrome.ChromeOptions o = new org.openqa.selenium.chrome.ChromeOptions();
                o.setUnhandledPromptBehaviour(org.openqa.selenium.UnexpectedAlertBehaviour.DISMISS_AND_NOTIFY);
                return new org.openqa.selenium.chrome.ChromeDriver(o);
            }
            case "firefox":  return new org.openqa.selenium.firefox.FirefoxDriver();
            case "edge":     return new org.openqa.selenium.edge.EdgeDriver();
            case "safari":   return new org.openqa.selenium.safari.SafariDriver();
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }
}
