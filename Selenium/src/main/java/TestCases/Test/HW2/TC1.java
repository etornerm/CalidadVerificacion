package TestCases.Test.HW2;

import Config.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TC1 extends BaseTest {
    
    WebDriver driver;
    WebDriverWait wait;
    
    @BeforeTest
    public void setup() throws Exception {
        driver = super.getWebDriver();
        wait  = new WebDriverWait(driver, 10);
        super.getEnvUrl(driver);
    }
    
    @Test(testName = "Navegar a computadoras", priority = 1)
    public void NavigateToComputers() {
        try {
            Assert.assertTrue(navigationQuery("computers"));
        }catch (Exception e) {
            super.errorLog(e);
        }
    }
    
    @Test(testName = "Navegar a electronicos", priority = 2)
    public void NavigateToElectronics() {
        try {
            Assert.assertTrue(navigationQuery("electronics"));
        }catch (Exception e) {
            super.errorLog(e);
        }
    }
    
    @Test(testName = "Navegar a ropa", priority = 3)
    public void NavigateToApparel() {
        try {
            Assert.assertTrue(navigationQuery("apparel"));
        }catch (Exception e) {
            super.errorLog(e);
        }
    }
    
    @Test(testName = "Navegar a descargas", priority = 4)
    public void NavigateToDownloads() {
        try {
            Assert.assertTrue(navigationQuery("digital-downloads"));
        }catch (Exception e) {
            super.errorLog(e);
        }
    }
    
    @Test(testName = "Navegar a libros", priority = 5)
    public void NavigateToBooks() {
        try {
            Assert.assertTrue(navigationQuery("books"));
        }catch (Exception e) {
            super.errorLog(e);
        }
    }
    
    @Test(testName = "Navegar a joyeria", priority = 6)
    public void NavigateToJewelry() {
        try {
            Assert.assertTrue(navigationQuery("jewelry"));
        }catch (Exception e) {
            super.errorLog(e);
        }
    }
    
    @Test(testName = "Navegar a tarjetas de regalo", priority = 7)
    public void NavigateToGiftCards() {
        try {
            Assert.assertTrue(navigationQuery("gift-cards"));
        }catch (Exception e) {
            super.errorLog(e);
        }
    }
    
    private boolean navigationQuery(String option) {
        driver.findElement(By.cssSelector(buildSelector(option))).click();
        return validateURL(option);
    }
    
    private String buildSelector(String element) {
        return String.format(".header-menu .top-menu a[href*='/"+ element +"']");
    }
    
    private boolean validateURL(String endPoint) {
        String desired = "http://demo.nopcommerce.com/" + endPoint;
        return desired.equals(driver.getCurrentUrl());
    }
    
    @AfterClass
    public void tearDown() {
        try {
            driver.quit();
        }
        catch(Exception e){
            super.errorLog(e);
        }
    }
    
}
