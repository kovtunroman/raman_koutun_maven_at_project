package pages.w3schools;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.BasePage;

public class W3SchoolsPage extends BasePage {
    private final static String url = "https://w3schools.com/java";
    private final static String TITLE_WITH_TUTORIAL = "//*[text()='Tutorial']";

    public void open() {
        super.open(url);
    }

    public void findAndCopyTitle() {
        WebElement title = driver.findElement(By.xpath(TITLE_WITH_TUTORIAL));
        Actions make = new Actions(driver);
        make
                .doubleClick(title)
                .keyDown(Keys.LEFT_CONTROL)
                .sendKeys("c")
                .keyUp(Keys.LEFT_CONTROL)
                .build().perform();
    }
}
