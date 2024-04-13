package classwork.day17;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;

public class BookingComTest {
    private final static String WHERE_ARE_YOU_GOING = "//input[@name='ss']";
    private final static String PARIS_CITY_SEARCH_OPTION = "//li[@id='autocomplete-result-0']/div/div/div/div[text()='Париж']";
    private final static String OCCUPACITY_BUTTON = "//button[@data-testid='occupancy-config']";
    private final static String ADULTS_OCCUPACITY = "//label[text()='Adults']/../following-sibling::div/button[2]";
    private final static String ROOMS_OCCUPACITY = "//label[text()='Rooms']/../following-sibling::div/button[2]";
    private final static String DATES_SEARCHBOX = "//div[@data-testid='searchbox-dates-container']";
    private final static String ARRAIVAL_DAY = String.format("//div[@data-testid='searchbox-datepicker-calendar']/div/*[1]/table/tbody/tr/td/span/span[text()='%s']", LocalDate.now().plusDays(3).getDayOfMonth());
    private final static String DEPARTUREL_DAY = String.format("//div[@data-testid='searchbox-datepicker-calendar']/div/*[1]/table/tbody/tr/td/span/span[text()='%s']", LocalDate.now().plusDays(10).getDayOfMonth());
    private final static String SIGN_IN_ALERT_CLOSE = "//button[@aria-label='Dismiss sign-in info.']";
    private final static String SEARCH_BUTTON ="//span[text()='Search']";

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get("https://booking.com");
        closeSignInAlertIfDisplayed(driver);

        driver.findElement(By.xpath(WHERE_ARE_YOU_GOING)).sendKeys("Париж, Иль-де-Франс, Франция");

        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath(PARIS_CITY_SEARCH_OPTION))
            ).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        } catch (TimeoutException e){
            System.out.println("Something went wrong.");
        }

//        driver.findElement(By.xpath(PARIS_CITY_SEARCH_OPTION)).click();

//        driver.findElement(By.xpath(DATES_SEARCHBOX)).click();
        driver.findElement(By.xpath(ARRAIVAL_DAY)).click();
        driver.findElement(By.xpath(DEPARTUREL_DAY)).click();

        driver.findElement(By.xpath(OCCUPACITY_BUTTON)).click();
        driver.findElement(By.xpath(ADULTS_OCCUPACITY)).click();
        driver.findElement(By.xpath(ADULTS_OCCUPACITY)).click();
        driver.findElement(By.xpath(ROOMS_OCCUPACITY)).click();

        driver.findElement(By.xpath(SEARCH_BUTTON)).click();

    }

    private static void closeSignInAlertIfDisplayed(WebDriver driver){
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath(SIGN_IN_ALERT_CLOSE))
            ).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        } catch (TimeoutException e){
            System.out.println("Something went wrong.");
        }
    }
}
