package tests;

import driver.Driver;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public abstract class BaseTest {

    public static WebDriver driver = Driver.getDriver();

    @BeforeClass
    public static void initDriver() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterClass
    public static void closeDriver() {
        driver.quit();
    }
}
