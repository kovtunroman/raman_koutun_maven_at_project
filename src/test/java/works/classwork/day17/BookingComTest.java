package works.classwork.day17;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.*;
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
    private final static String SEARCH_BUTTON = "//span[text()='Search']";
    private final static String SIX_SCORE_RAITING_CHECKBOX = "//div[contains(@id, 'filter_group_review_score_:r')]/div[10]";
    private final static String SORT_BY = "//span[text()='Sort by:']";
    private final static String SORT_BY_LOW_TO_HIGH = "//span[text()='Property rating (low to high)']";
    private final static String FIRST_CARD_RAITING = "//div[@data-testid='property-card'][1]//div[@data-testid='review-score']/div[1]/div";

    private static WebDriver driver;

    @Before
    public void beforeBookingComTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @Test
    public void searchForParis4Persons2Rooms6AndMoreRaitingSortByLowToHigh() {
        driver.get("https://booking.com");
        findElementExplicitlyWaitClick(driver, SIGN_IN_ALERT_CLOSE);

        driver.findElement(By.xpath(WHERE_ARE_YOU_GOING)).sendKeys("Париж, Иль-де-Франс, Франция");
        driver.findElement(By.xpath(PARIS_CITY_SEARCH_OPTION)).click();

        driver.findElement(By.xpath(ARRAIVAL_DAY)).click();
        driver.findElement(By.xpath(DEPARTUREL_DAY)).click();

        driver.findElement(By.xpath(OCCUPACITY_BUTTON)).click();
        driver.findElement(By.xpath(ADULTS_OCCUPACITY)).click();
        driver.findElement(By.xpath(ADULTS_OCCUPACITY)).click();
        driver.findElement(By.xpath(ROOMS_OCCUPACITY)).click();

        driver.findElement(By.xpath(SEARCH_BUTTON)).click();

        WebElement sixScoreRaitingCheckbox = driver.findElement(By.xpath(SIX_SCORE_RAITING_CHECKBOX));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sixScoreRaitingCheckbox);
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath(SIX_SCORE_RAITING_CHECKBOX))
            ).click();
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                    ExpectedConditions.elementToBeSelected(By.xpath(SIX_SCORE_RAITING_CHECKBOX))
            );
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        } catch (TimeoutException e) {
            System.out.println("Something went wrong.");
        }

        // TODO
        WebElement sortBy = driver.findElement(By.xpath(SORT_BY));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sortBy);
        sortBy.click();
        driver.findElement(By.xpath(SORT_BY_LOW_TO_HIGH)).click();

        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath(FIRST_CARD_RAITING))
            );
            assertEquals("Score is wrong", "Scored 6.0 ", driver.findElement(By.xpath(FIRST_CARD_RAITING)).getText());
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        } catch (TimeoutException e) {
            System.out.println("Something went wrong.");
        }

    }

    @After
    public void afterBookingComTest() {
        driver.quit();
    }

    private static void findElementExplicitlyWaitClick(WebDriver driver, String by) {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath(by))
            ).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        } catch (TimeoutException e) {
            System.out.println("Something went wrong.");
        }
    }
}