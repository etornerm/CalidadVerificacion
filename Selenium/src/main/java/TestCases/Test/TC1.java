package TestCases.Test;

import Config.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC1 extends BaseTest {

    WebDriver driver;

    @BeforeTest
    public void setup() throws Exception {

        driver = super.getWebDriver();
    }

    @Test(testName = "Abrir el navegador",priority=1)
    public void CheckTitle() throws IOException, InterruptedException {
        try {
            /* Define URL to test */
            super.getEnvUrl(driver);
            Assert.assertEquals("nopCommerce demo store", driver.getTitle());
            System.out.println("El titulo es correcto");

            Thread.sleep(5000);
            System.out.println("TC1: Paso correctamente");

        } catch (Exception e){

            super.errorLog(e);
        }
    }

    @AfterClass
    public void tearDown() throws IOException, InterruptedException {

        driver.quit();
    }
}
