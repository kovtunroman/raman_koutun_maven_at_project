package tests.junit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.demoqa.DemoQAPage;

public class DemoQATest extends BaseTest {
    DemoQAPage demoQAPage;

    @Before
    public void openPage(){
        demoQAPage = new DemoQAPage(driver);
        demoQAPage.open();
    }

    @Test
    public void demoQAColorSelectorSelectMagentaAndValidateTest(){
        demoQAPage.colorSelectorSelectMagenta();
        Assert.assertTrue("Magenta is not selected", demoQAPage.isMagentaSelected());
    }

    @Test
    public void demoQACarSelectorSelectOpelAndValidateTest(){
        demoQAPage.carSelectorSelectOpel();
        Assert.assertTrue("Magenta is not selected", demoQAPage.isOpelSelected());
    }
}
