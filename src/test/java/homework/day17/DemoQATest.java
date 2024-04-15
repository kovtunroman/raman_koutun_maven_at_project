package homework.day17;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DemoQATest {
    private final static String COLOR_SELECTOR = "//select[@id='oldSelectMenu']";
    private final static String CAR_SELECTOR = "//select[@name='cars']";

    private static WebDriver driver;

    @Before
    public void beforeBookingComTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @Test
    public void colorSelectorTestSelkectAnyColor() {
        driver.get("https://demoqa.com/select-menu ");
        WebElement element = driver.findElement(By.xpath(COLOR_SELECTOR));

        Select select = new Select(element);
        select.selectByValue("9");
        assertTrue("Magenta is not selected", driver.findElement(By.xpath("//option[text()='Magenta']")).isSelected());
    }

    @Test
    public void carSelectorTestSelkectAnyColor() {
        driver.get("https://demoqa.com/select-menu ");
        WebElement element = driver.findElement(By.xpath(CAR_SELECTOR));

        Select select = new Select(element);
        select.selectByValue("opel");
        assertTrue("Magenta is not selected", driver.findElement(By.xpath("//option[text()='Opel']")).isSelected());
    }

    @After
    public void afterBookingComTest() {
        driver.quit();
    }
}
