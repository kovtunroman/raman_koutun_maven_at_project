package tests;

import org.junit.Assert;
import org.junit.Test;
import pages.GooglePage;
import pages.W3SchoolsPage;

public class W3SchoolsTest extends BaseTest {
    @Test
    public void w3SchoolsComCopyTitlePateInGoogleValidateThatAllResultHAveThisValue() {
        W3SchoolsPage w3SchoolsPage = new W3SchoolsPage(driver);
        w3SchoolsPage.open();
        w3SchoolsPage.findAndCopyTitle();
        GooglePage googlePage = new GooglePage(driver);
        googlePage.open();
        googlePage.pasteAndSearchForTitle();
        Assert.assertTrue("Result not contain Tutorial:", googlePage.resultsContainTutorial());
    }
}
