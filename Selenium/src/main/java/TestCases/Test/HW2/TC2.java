package TestCases.Test.HW2;

import Config.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class TC2 extends BaseTest {
    
    WebDriver driver;
    WebDriverWait wait;
    
    @BeforeTest
    public void setup() throws Exception {
        driver = super.getWebDriver();
        wait  = new WebDriverWait(driver, 10);
        super.getEnvUrl(driver);
    }
    
    @Test(testName = "Ir a la pagina wishlist", priority = 1)
    public void OpenWishList() {
        try {
            driver.findElement(By.cssSelector(".header-links .ico-wishlist")).click();
            Assert.assertTrue(validateURL("wishlist"));
        }catch (Exception e) {
            super.errorLog(e);
        }
    }
    
    @Test(testName = "Despliegue de mensaje 'The wishlist is empty!'", priority = 2)
    public void VerifyEmptyWishList() {
        try {
            WebElement elem = driver.findElement(By.cssSelector(".page-body .no-data"));
            Assert.assertEquals(elem.getText(), "The wishlist is empty!");
        }catch (Exception e){
            super.errorLog(e);
        }
    }
    
    @Test(testName = "Busque el libro Fahrenheit 451.", priority = 3)
    public void SearchBook() {
        try {
            driver.findElement(By.id("small-searchterms")).sendKeys("Fahrenheit 451");
            driver.findElement(By.cssSelector("#small-search-box-form .search-box-button")).click();
        } catch (Exception e) {
            super.errorLog(e);
        }
    }
    
    @Test(testName = "Agregue el libro al Wishlist.", priority = 4)
    public void AddBookToWishList() {
        try {
            driver.findElement(By.cssSelector(".search-results .item-box:first-child .add-to-wishlist-button")).click();
        } catch (Exception e) {
            
            super.errorLog(e);
        }
    }
    
    @Test(testName = "Verificar que el libro se agrego correctamente", priority = 5)
    public void VerifyBookWasAdded() {
        try {
            driver.findElement(By.cssSelector(".header-links .ico-wishlist")).click();
            Assert.assertEquals(driver.findElement(By.cssSelector(".page-body .wishlist-content .cart tbody tr:first-child .sku-number")).getText(), "FR_451_RB");
        } catch (Exception e) {
            
            super.errorLog(e);
        }
    }
    
    @Test(testName = "Ir a la pagina shopping cart", priority = 6)
    public void OpenShoppingCart() {
        try {
            driver.findElement(By.id("topcartlink")).click();
            Assert.assertTrue(validateURL("cart"));
        }catch (Exception e) {
            super.errorLog(e);
        }
    }
    
    @Test(testName = "Despliegue de mensaje 'Your Shopping Cart is empty!'", priority = 7)
    public void VerifyEmptyShoppingCart() {
        try {
            WebElement elem = driver.findElement(By.cssSelector(".page-body .no-data"));
            Assert.assertEquals(elem.getText(), "Your Shopping Cart is empty!");
        }catch (Exception e){
            super.errorLog(e);
        }
    }
    
    @Test(testName = "Agregar libro al carrito desde la lista", priority = 8)
    public void AddBookToCart() {
        try {
            driver.findElement(By.cssSelector(".header-links .ico-wishlist")).click();
            driver.findElement(By.name("addtocart")).click();
            driver.findElement(By.name("addtocartbutton")).click();
        } catch (Exception e) {
            super.errorLog(e);
        }
    }
    
    @Test(testName = "Validar que el libro se ha añadido al carrito", priority = 9)
    public void ValidateAdditionToCart() {
        try {
            String cart = driver.findElement(By.cssSelector("span.cart-qty")).getText();
            Assert.assertEquals(cart, "(1)");
        } catch (Exception e) {
            super.errorLog(e);
        }
    }
    
    @Test(testName = "Seguir comprando", priority = 10)
    public void ContinueShopping() {
        try {
            driver.findElement(By.name("continueshopping")).click();
            Assert.assertTrue(validateURL("search"));
        } catch (Exception e) {
            super.errorLog(e);
        }
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
