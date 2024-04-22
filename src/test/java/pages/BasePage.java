package pages;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    protected final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void open(String url) {
        driver.get(url);
    }

    public abstract void open();
}
