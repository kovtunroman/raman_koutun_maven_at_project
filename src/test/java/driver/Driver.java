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

    private static WebDriver getWebDriver() {
        return switch (config) {
//            case FF -> getFFDriver();
//            case REMOTE -> getRemoteDriver();
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
}
