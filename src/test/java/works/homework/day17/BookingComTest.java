package works.homework.day17;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BookingComTest {
    private final static String WHERE_ARE_YOU_GOING = "//input[@name='ss']";
    private final static String PARIS_CITY_SEARCH_OPTION = "//li[@id='autocomplete-result-0']/div/div/div/div[text()='Париж']";
    private final static String LONDON_CITY_SEARCH_OPTION = "//li[@id='autocomplete-result-0']/div/div/div/div[text()='London']";
    private final static String PRAGUE_CITY_SEARCH_OPTION = "//li[@id='autocomplete-result-0']/div/div/div/div[text()='Prague']";
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
    private final static String SORT_BY_HIGH_TO_LOW = "//span[text()='Property rating (high to low)']";
    private final static String FIRST_CARD_RAITING = "//div[@data-testid='property-card'][1]//div[@data-testid='review-score']/div[1]/div";
    private final static String FIRST_CARD_IMAGE = "//div[@data-testid='property-card'][1]//img";
    private final static String TENTH_HOTEL_CARD = "//div[@data-testid='property-card'][10]";
    private final static String CURRANCY_BUTTON = "//button[@data-testid='header-currency-picker-trigger']";
    private final static String CURRANCY_HINT = "//div[text()='Select your currency']";
    private final static String LANGUAGE_BUTTON = "//button[@data-testid='header-language-picker-trigger']";
    private final static String LANGUAGE_HINT = "//div[text()='Select your language']";
    private final static String HOTEL_RAITING = "//div[@data-testid='review-score-right-component']/div[1]/div";

    private static WebDriver driver;

    @Before
    public void beforeBookingComTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get("https://booking.com");
        findElementExplicitlyWaitClick(driver, SIGN_IN_ALERT_CLOSE);
    }

    @Test
    public void bookingComSearchLondonHotelsChangeTextColorFor10thHotelTakeScereenshot() {
        driver.findElement(By.xpath(WHERE_ARE_YOU_GOING)).sendKeys("London");
        driver.findElement(By.xpath(LONDON_CITY_SEARCH_OPTION)).click();

        driver.findElement(By.xpath(SEARCH_BUTTON)).click();

        WebElement tenthHotelCard = driver.findElement(By.xpath(TENTH_HOTEL_CARD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tenthHotelCard);
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.backgroundColor = 'green'", tenthHotelCard);
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.color = 'red'", tenthHotelCard);

        byte[] asBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        try {
            Files.write(Paths.get("/home/romankovtun/IdeaProjects/raman_koutun_maven_at_project/src/test/resources/screenshot.png"), asBytes);
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    @Test
    public void bookingComSearchForParis4Persons2Rooms6AndMoreRaitingSortByLowToHigh() {
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
            assertEquals("Score is wrong", "Scored 6.0", driver.findElement(By.xpath(FIRST_CARD_RAITING)).getText());
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        } catch (TimeoutException e) {
            System.out.println("Something went wrong.");
        }

    }

    @Test
    public void validateCurrancyHintValue() {
        WebElement currancyButton = driver.findElement(By.xpath(CURRANCY_BUTTON));
        Actions actions = new Actions(driver);
        actions.moveToElement(currancyButton);
        actions.perform();
        assertEquals("Value is not correct", driver.findElement(By.xpath(CURRANCY_HINT)).getText(), "Select your currency");
    }

    @Test
    public void validateLanguageHintValue() {
        WebElement languageButton = driver.findElement(By.xpath(LANGUAGE_BUTTON));
        Actions actions = new Actions(driver);
        actions.moveToElement(languageButton);
        actions.perform();
        assertEquals("Value is not correct", driver.findElement(By.xpath(LANGUAGE_HINT)).getText(), "Select your language");
    }

    @Test
    public void bookingComSearchForPraga2Persons1RoomMAxRaitingOpenHotelPageValidateRaiting() {
        driver.findElement(By.xpath(WHERE_ARE_YOU_GOING)).sendKeys("Prague");
        driver.findElement(By.xpath(PRAGUE_CITY_SEARCH_OPTION)).click();

        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath(ARRAIVAL_DAY))
            ).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        } catch (TimeoutException e) {
            System.out.println("Something went wrong.");
        }
        findElementExplicitlyWaitClick(driver, DATES_SEARCHBOX);
        driver.findElement(By.xpath(SEARCH_BUTTON)).click();
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath(FIRST_CARD_RAITING))
            );

        } catch (TimeoutException e) {
            System.out.println("Something went wrong.");
        }
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath(SORT_BY))
            ).click();

        } catch (TimeoutException e) {
            System.out.println("Something went wrong.");
        }
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath(SORT_BY_HIGH_TO_LOW))
            ).click();

        } catch (TimeoutException e) {
            System.out.println("Something went wrong.");
        }
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath(FIRST_CARD_IMAGE))
            ).click();

        } catch (TimeoutException e) {
            System.out.println("Something went wrong.");
        }
        driver.getWindowHandles().forEach(tab -> driver.switchTo().window(tab));
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath(HOTEL_RAITING))
            );

        } catch (TimeoutException e) {
            System.out.println("Something went wrong.");
        }
        assertEquals("Raiting is wrong", "Scored 8.7", driver.findElement(By.xpath(HOTEL_RAITING)).getText());
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
