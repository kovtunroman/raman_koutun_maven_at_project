package pages.google;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.BasePage;

import java.util.List;


public class GooglePage extends BasePage {
    private final static String url = "https://google.com";
    final static String SEARCH_FIELD = "//textarea[@name='q']";
    final static String CHANGE_TO_ENGLISH = "//a[text()='Change to English']";
    final static String SEARCH_RESULTS = "//div/h1[text()='Search Results']/../div/div";

    public GooglePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        super.open(url);
    }

    public void pasteAndSearchForTitle() {
        WebElement searchField = driver.findElement(By.xpath(SEARCH_FIELD));
        Actions make = new Actions(driver);
        make
                .click(searchField)
                .keyDown(Keys.LEFT_CONTROL)
                .sendKeys("v")
                .keyUp(Keys.LEFT_CONTROL)
                .sendKeys(Keys.ENTER)
                .build().perform();

        driver.findElement(By.xpath(CHANGE_TO_ENGLISH)).click();
    }

    public boolean resultsContainTutorial(){
        List<WebElement> searchResults = driver.findElements(By.xpath(SEARCH_RESULTS));
        boolean isResultsContainTutorial = true;
        for(WebElement searchResult  : searchResults){
            if (!searchResult.getText().toLowerCase().contains("tutorial")){
                System.out.println("Search Result without word Tutorial: " + searchResult.getText());
                isResultsContainTutorial = false;
                break;
            }
        }
        return isResultsContainTutorial;
    }
}
