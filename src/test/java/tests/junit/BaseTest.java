package tests.junit;

import driver.Driver;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public abstract class BaseTest {

    public static WebDriver driver;

    @BeforeClass
    public static void initDriver() {
        driver = Driver.getDriver();
    }

    @AfterClass
    public static void closeDriver() {
        Driver.destroy();
    }
}
