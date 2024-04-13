package classwork.day17;

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
    private final static String SEARCH_BUTTON ="//span[text()='Search']";
    private final static String SIX_SCORE_RAITING_CHECKBOX = "//div[contains(@id, 'filter_group_review_score_:r')]/div[10]";
    private final static String SORT_BY = "//span[text()='Sort by:']";
    private final static String SORT_BY_LOW_TO_HIGH = "//span[text()='Property rating (low to high)']";

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get("https://booking.com");
//        closeSignInAlertIfDisplayed(driver);
        findElementExplicitlyWaitClick(driver, SIGN_IN_ALERT_CLOSE);

        driver.findElement(By.xpath(WHERE_ARE_YOU_GOING)).sendKeys("Париж, Иль-де-Франс, Франция");
        driver.findElement(By.xpath(PARIS_CITY_SEARCH_OPTION)).click();

//        driver.findElement(By.xpath(DATES_SEARCHBOX)).click();
        driver.findElement(By.xpath(ARRAIVAL_DAY)).click();
        driver.findElement(By.xpath(DEPARTUREL_DAY)).click();

        driver.findElement(By.xpath(OCCUPACITY_BUTTON)).click();
        driver.findElement(By.xpath(ADULTS_OCCUPACITY)).click();
        driver.findElement(By.xpath(ADULTS_OCCUPACITY)).click();
        driver.findElement(By.xpath(ROOMS_OCCUPACITY)).click();

        driver.findElement(By.xpath(SEARCH_BUTTON)).click();

        WebElement sixScoreRaitingCheckbox = driver.findElement(By.xpath(SIX_SCORE_RAITING_CHECKBOX));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sixScoreRaitingCheckbox);
        findElementExplicitlyWaitClick(driver, SIX_SCORE_RAITING_CHECKBOX);

        // TODO
        WebElement sortBy = driver.findElement(By.xpath(SORT_BY));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sortBy);
        sortBy.click();
        driver.findElement(By.xpath(SORT_BY_LOW_TO_HIGH)).click();
    }

//    private static void closeSignInAlertIfDisplayed(WebDriver driver){
//        try {
//            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
//            new WebDriverWait(driver, Duration.ofSeconds(5)).until(
//                    ExpectedConditions.visibilityOfElementLocated(By.xpath(SIGN_IN_ALERT_CLOSE))
//            ).click();
//            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//        } catch (TimeoutException e){
//            System.out.println("Something went wrong.");
//        }
//    }

    private static void findElementExplicitlyWaitClick(WebDriver driver, String by){
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath(by))
            ).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        } catch (TimeoutException e){
            System.out.println("Something went wrong.");
        }
    }
}
