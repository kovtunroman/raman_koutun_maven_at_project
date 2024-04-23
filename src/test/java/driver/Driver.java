package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.Optional;
import java.util.Collections;

public class Driver {
    private static WebDriver driver;
    private static Config config = Optional.ofNullable(System.getProperty("CONFIG")).isEmpty() ?
            Config.CHROME : Config.valueOf(System.getProperty("CONFIG"));

    public static WebDriver getDriver(){
        if(null == driver){
            driver = getWebDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
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
