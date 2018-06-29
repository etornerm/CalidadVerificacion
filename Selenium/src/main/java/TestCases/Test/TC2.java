package TestCases.Test;

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

    @Test(testName = "test",priority=2)
    public void test() throws IOException, InterruptedException {
        try {

            System.out.println("TC2: Paso correctamente");
        } catch (Exception e){

            super.errorLog(e);
        }
    }

    @AfterClass
    public void tearDown() throws IOException, InterruptedException {

        driver.quit();
    }
}
