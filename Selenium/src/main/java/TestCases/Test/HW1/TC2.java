package TestCases.Test.HW1;

import Config.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC2 extends BaseTest {

    WebDriver driver;

    @BeforeTest
    public void setup() throws Exception {

        driver = super.getWebDriver();
    }

    @Test(testName = "TestMenu",priority=2)
    public void TestMenu() throws IOException, InterruptedException {
        try {

            /* Define URL to test */
            super.getEnvUrl(driver);

            Thread.sleep(3000);

            driver.findElement(By.cssSelector(".header-menu .top-menu a[href*='/computers']")).click();
            Thread.sleep(3000);

            Assert.assertEquals(driver.findElement(By.cssSelector(".header-menu .top-menu a[href*='/computers']")).getText(),"Computers");

            driver.findElement(By.cssSelector(".header-menu .top-menu a[href*='/electronics']")).click();
            Thread.sleep(3000);

            Assert.assertEquals(driver.findElement(By.cssSelector(".header-menu .top-menu a[href*='/electronics']")).getText(),"Electronics");

            driver.findElement(By.cssSelector(".header-menu .top-menu a[href*='/apparel']")).click();
            Thread.sleep(3000);

            Assert.assertEquals(driver.findElement(By.cssSelector(".header-menu .top-menu a[href*='/apparel']")).getText(),"Apparel");

            driver.findElement(By.cssSelector(".header-menu .top-menu a[href*='/digital-downloads']")).click();
            Thread.sleep(3000);

            Assert.assertEquals(driver.findElement(By.cssSelector(".header-menu .top-menu a[href*='/digital-downloads']")).getText(),"Digital downloads");

            driver.findElement(By.cssSelector(".header-menu .top-menu a[href*='/books']")).click();
            Thread.sleep(3000);

            Assert.assertEquals(driver.findElement(By.cssSelector(".header-menu .top-menu a[href*='/books']")).getText(),"Books");

            driver.findElement(By.cssSelector(".header-menu .top-menu a[href*='/jewelry']")).click();
            Thread.sleep(3000);

            Assert.assertEquals(driver.findElement(By.cssSelector(".header-menu .top-menu a[href*='/jewelry']")).getText(),"Jewelry");


            driver.findElement(By.cssSelector(".header-menu .top-menu a[href*='/gift-cards']")).click();
            Thread.sleep(3000);

            Assert.assertEquals(driver.findElement(By.cssSelector(".header-menu .top-menu a[href*='/gift-cards']")).getText(),"Gift Cards");


            driver.findElement(By.cssSelector(".header-logo  a[href*='/']")).click();
            Thread.sleep(3000);


            System.out.println("TC2: Pasó correctamente");

        } catch (Exception e){

            super.errorLog(e);
        }
    }

    @AfterClass
    public void tearDown() throws IOException, InterruptedException {

        driver.quit();
    }
}
