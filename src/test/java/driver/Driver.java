package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Optional;
import java.util.Collections;

public class Driver {
    public static WebDriver driver;
    protected static DriverConfig config = Optional.ofNullable(System.getProperty("CONFIG")).isEmpty() ?
            DriverConfig.CHROME : DriverConfig.valueOf(System.getProperty("CONFIG"));

    public static WebDriver getDriver(){
        if(null == driver){
            driver = getWebDriver();
        }
        return  driver;
    }

    private static WebDriver getWebDriver() {
        return switch (config) {
            case FF -> getFFDriver();
            case REMOTE -> getRemoteDriver();
            default -> getChromeDriver();
        };
    }

    private static WebDriver getChromeDriver() {
        ChromeOptions caps = new ChromeOptions();
        caps.addArguments("start-maximized");
        caps.addArguments("disable-infobars");
        caps.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        return new ChromeDriver(caps);
    }

    private static WebDriver getFFDriver(){
        return driver;
    }

    private static WebDriver getRemoteDriver(){
        return driver;
    }

    public static void destroy(){
        driver.quit();
        driver = null;
    }
}
