package pages.booking;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

import java.time.Duration;
import java.time.LocalDate;

public class BookingMainPaige extends BasePage {
    private final static String url = "https://booking.com";
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

    public void open() {
        super.open(url);
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
