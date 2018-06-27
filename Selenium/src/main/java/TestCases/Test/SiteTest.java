package TestCases.Test;

import Config.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class SiteTest extends BaseTest {
    
    WebDriver driver;

    @BeforeTest
    public void setup() throws Exception {

        driver = super.getWebDriver();
    }

    @Test
    public void OpenWishList() throws IOException, InterruptedException {
        try {
            JavascriptExecutor jse = (JavascriptExecutor) driver;

            /* Define URL to test */
            super.getEnvUrl(driver);

            /* Maximize Window and load */
            Thread.sleep(3000);
            jse.executeScript("window.scrollBy(0,5550)");

            driver.findElement(By.cssSelector(".header-links .ico-wishlist")).click();
            Thread.sleep(3000);

            Assert.assertEquals(driver.findElement(By.cssSelector(".page-body .no-data")).getText(),"The wishlist is empty!");

            driver.findElement(By.id("small-searchterms")).sendKeys("Fahrenheit 451");
            Thread.sleep(1000);

            driver.findElement(By.cssSelector("#small-search-box-form .search-box-button")).click();
            Thread.sleep(3000);

            driver.findElement(By.cssSelector(".search-results .item-box:first-child .add-to-wishlist-button")).click();
            Thread.sleep(2000);

            Assert.assertEquals(driver.findElement(By.cssSelector(".header-links .wishlist-qty")).getText(),"(1)");

            driver.findElement(By.cssSelector("#bar-notification .close")).click();
            Thread.sleep(1000);

            driver.findElement(By.cssSelector(".header-links .ico-wishlist")).click();
            Thread.sleep(3000);

            Assert.assertEquals(driver.findElement(By.cssSelector(".page-body .wishlist-content .cart tbody tr:first-child .sku-number")).getText(),"FR_451_RB");

            System.out.println("TC3: Paso correctamente");

        } catch (Exception e){

            super.errorLog(e);
        }
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException, InterruptedException {

        if (ITestResult.FAILURE==result.getStatus()){
            super.errorSS(driver);
            System.out.println();
        }

        driver.quit();
    }
}
