package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertTrue;

public class DemoQAPage extends BasePage {
    final static String url = "https://demoqa.com/select-menu";
    private final static String COLOR_SELECTOR = "//select[@id='oldSelectMenu']";
    private final static String CAR_SELECTOR = "//select[@name='cars']";

    public DemoQAPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        super.open(url);
    }

    public void colorSelectorSelectMagenta() {
        WebElement element = driver.findElement(By.xpath(COLOR_SELECTOR));
        Select select = new Select(element);
        select.selectByValue("9");
    }

    public boolean isMagentaSelected() {
        return driver.findElement(By.xpath("//option[text()='Magenta']")).isSelected();
    }

    public void carSelectorSelectOpel() {
        WebElement element = driver.findElement(By.xpath(CAR_SELECTOR));
        Select select = new Select(element);
        select.selectByValue("opel");
    }

    public boolean isOpelSelected() {
        return driver.findElement(By.xpath("//option[text()='Opel']")).isSelected();
    }
}
