package tests.junit;

import org.junit.Assert;
import org.junit.Test;
import pages.google.GooglePage;
import pages.w3schools.W3SchoolsPage;

public class W3SchoolsTest extends BaseTest {
    @Test
    public void copyPasteAndSearchTitleTest() {
        W3SchoolsPage w3SchoolsPage = new W3SchoolsPage();
        w3SchoolsPage.open();
        w3SchoolsPage.findAndCopyTitle();
        GooglePage googlePage = new GooglePage();
        googlePage.open();
        googlePage.pasteAndSearchForTitle();
        Assert.assertTrue("Result not contain Tutorial:", googlePage.resultsContainTutorial());
    }
}
