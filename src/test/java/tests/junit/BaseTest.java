package tests.junit;

import driver.Driver;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public abstract class BaseTest {

    @AfterClass
    public static void tearDown() {
        Driver.destroy();
    }
}
