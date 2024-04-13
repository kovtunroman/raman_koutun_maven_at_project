package homework.day16;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class WeatherTests {
    final static String SEARCH_FIELD = "//textarea[@name='q']";
    final static String NEXT_DAY = "//div[@class='wob_df wob_ds']/div[2]";
    final static String CHNAGE_TO_ENGLISH = "//a[text()='Change to English']";
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://google.com");
        WebElement searchField = driver.findElement(By.xpath(SEARCH_FIELD));
        searchField.sendKeys("погода минск");
        searchField.sendKeys(Keys.RETURN);
        driver.findElement(By.xpath(CHNAGE_TO_ENGLISH)).click();
        driver.findElement(By.xpath(NEXT_DAY)).click();
        String dayNext = LocalDate.now().plusDays(1).getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US);
        System.out.println(driver.findElement(By.xpath(String.format("//*[name()='text' and contains(@aria-label, '%s 12:00')]",dayNext))).getAttribute("aria-label"));
        driver.close();
    }
}
