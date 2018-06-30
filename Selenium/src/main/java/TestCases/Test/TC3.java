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

public class TC3 extends BaseTest {

    WebDriver driver;

    @BeforeTest
    public void setup() throws Exception {

        driver = super.getWebDriver();
    }

    @Test(testName = "Vaya la pagina wishlist", priority = 1)
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

        } catch (Exception e) {

            super.errorLog(e);
        }
    }


    @Test(testName = "Verifique que se despliega el mensaje “The wishlist is empty!”", priority = 2)
    public void VerifyEmptyWishList() throws IOException, InterruptedException {
        try {

            Assert.assertEquals(driver.findElement(By.cssSelector(".page-body .no-data")).getText(), "The wishlist is empty!");

        } catch (Exception e) {

            super.errorLog(e);
        }
    }

    @Test(testName = "Busque el libro Fahrenheit 451.", priority = 3)
    public void SearchBook() throws IOException, InterruptedException {
        try {

            driver.findElement(By.id("small-searchterms")).sendKeys("Fahrenheit 451");
            Thread.sleep(1000);

            driver.findElement(By.cssSelector("#small-search-box-form .search-box-button")).click();
            Thread.sleep(3000);

        } catch (Exception e) {

            super.errorLog(e);
        }
    }

    @Test(testName = "Agregue el libro al Wishlist.", priority = 4)
    public void AddBookToWishList() throws IOException, InterruptedException {
        try {

            driver.findElement(By.cssSelector(".search-results .item-box:first-child .add-to-wishlist-button")).click();
            Thread.sleep(3000);

            driver.findElement(By.cssSelector("#bar-notification .close")).click();
            Thread.sleep(1000);

            Assert.assertEquals(driver.findElement(By.cssSelector(".header-links .wishlist-qty")).getText(), "(1)");


        } catch (Exception e) {

            super.errorLog(e);
        }
    }

    @Test(testName = "Verificar que el libro se agrego correctamente", priority = 5)
    public void VerifyBookWasAdded() throws IOException, InterruptedException {
        try {

            driver.findElement(By.cssSelector(".header-links .ico-wishlist")).click();
            Thread.sleep(3000);

            Assert.assertEquals(driver.findElement(By.cssSelector(".page-body .wishlist-content .cart tbody tr:first-child .sku-number")).getText(), "FR_451_RB");

            System.out.println("TC3: Paso correctamente");

        } catch (Exception e) {

            super.errorLog(e);
        }
    }

    @AfterClass
    public void tearDown() throws IOException, InterruptedException {

        driver.quit();
    }
}
