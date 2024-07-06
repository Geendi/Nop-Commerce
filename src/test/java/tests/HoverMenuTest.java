package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HoverMenu;


public class HoverMenuTest extends TestBase{

    HoverMenu hoverMenu;

    @Test
    public void hoverOverComputers() {
        hoverMenu = new HoverMenu(driver);
        hoverMenu.hoverComputers();
        Assert.assertEquals(hoverMenu.desktopPageAssertion(), "Desktops");
    }

    @Test(priority = 1)
    public void hoverOverElectronics() {
        hoverMenu.hoverElectronics();
        Assert.assertEquals(hoverMenu.electronicsPageAssertion(), "Cell phones");
    }

    @Test(priority = 2)
    public void hoverOverApparel() {
        hoverMenu.hoverApparel();
        Assert.assertEquals(hoverMenu.apparelPageAssertion(), "Shoes");
    }
}
