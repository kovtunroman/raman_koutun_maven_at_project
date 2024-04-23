package pages;

import driver.Driver;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    public final WebDriver driver = Driver.getDriver();

    public void open(String url) {
        driver.get(url);
    }
}
