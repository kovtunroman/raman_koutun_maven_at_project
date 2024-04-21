package works.homework.day17;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class W3schoolsComTest {
    private final static String TITLE_WITH_TUTORIAL = "//*[text()='Tutorial']";
    final static String SEARCH_FIELD = "//textarea[@name='q']";
    final static String CHANGE_TO_ENGLISH = "//a[text()='Change to English']";
    final static String SEARCH_RESULTS = "//div/h1[text()='Search Results']/../div/div";

    private static WebDriver driver;

    @Before
    public void beforeBookingComTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @Test
    public void w3SchoolsComCopyTitlePateInGoogleValidateThatAllResultHAveThisValue(){
        driver.get("https://w3schools.com/java ");
        WebElement title = driver.findElement(By.xpath(TITLE_WITH_TUTORIAL));
        Actions make = new Actions(driver);

        make
                .doubleClick(title)
                .keyDown(Keys.LEFT_CONTROL)
                .sendKeys("c")
                .keyUp(Keys.LEFT_CONTROL)
                .build().perform();

        driver.get("https://google.com");
        WebElement searchField = driver.findElement(By.xpath(SEARCH_FIELD));
        make
                .click(searchField)
                .keyDown(Keys.LEFT_CONTROL)
                .sendKeys("v")
                .keyUp(Keys.LEFT_CONTROL)
                .sendKeys(Keys.ENTER)
                .build().perform();


        driver.findElement(By.xpath(CHANGE_TO_ENGLISH)).click();
        List<WebElement> searchResults = driver.findElements(By.xpath(SEARCH_RESULTS));

        boolean resultsContainTutorial = true;
        for(WebElement searchResult  : searchResults){
            if (!searchResult.getText().toLowerCase().contains("tutorial")){
                System.out.println("Search Result without word Tutorial: " + searchResult.getText());
                resultsContainTutorial = false;
                break;
            }
        }
        assertTrue("Result not contain Tutorial:", resultsContainTutorial);
    }

    @After
    public void afterBookingComTest() {
        driver.quit();
    }
}
