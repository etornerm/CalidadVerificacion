package TestCases.Test.HW2;

import Config.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class TC3 extends BaseTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void setup() throws Exception {
        driver = super.getWebDriver();
        wait  = new WebDriverWait(driver, 10);
        super.getEnvUrl(driver);
    }

    @Test(testName = "Busque el libro Fahrenheit 451.", priority = 1)
    public void SearchBook() {
        try {
            waitForPageLoad();
            driver.findElement(By.id("small-searchterms")).sendKeys("Fahrenheit 451");
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#small-search-box-form .search-box-button")));
            driver.findElement(By.cssSelector("#small-search-box-form .search-box-button")).click();

        } catch (Exception e) {
            super.errorLog(e);
        }
    }

    @Test(testName = "Agregue el libro al Wishlist.", priority = 2)
    public void AddBookToWishList() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".search-results .item-box:first-child .add-to-wishlist-button")));
            driver.findElement(By.cssSelector(".search-results .item-box:first-child .add-to-wishlist-button")).click();
        } catch (Exception e) {

            super.errorLog(e);
        }
    }

    @Test(testName = "Ir a la pagina shopping cart", priority = 3)
    public void OpenShoppingCart() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.id("topcartlink")));
            driver.findElement(By.id("topcartlink")).click();
            waitForPageLoad();
            Assert.assertTrue(validateURL("cart"));
        }catch (Exception e) {
            super.errorLog(e);
        }
    }

    @Test(testName = "Agregar libro al carrito desde la lista de libros", priority = 4)
    public void AddBookToCart() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".header-links .ico-wishlist")));
            driver.findElement(By.cssSelector(".header-links .ico-wishlist")).click();

            wait.until(ExpectedConditions.elementToBeClickable(By.name("addtocart")));
            driver.findElement(By.name("addtocart")).click();

            wait.until(ExpectedConditions.elementToBeClickable(By.name("addtocartbutton")));
            driver.findElement(By.name("addtocartbutton")).click();
        } catch (Exception e) {
            super.errorLog(e);
        }
    }

    @Test(testName = "Validar que el libro se ha añadido al carrito", priority = 5)
    public void ValidateAdditionToCart() {
        try {
            String cart = driver.findElement(By.cssSelector("span.cart-qty")).getText();
            Assert.assertEquals(cart, "(1)");
        } catch (Exception e) {
            super.errorLog(e);
        }
    }


    @Test(testName = "Ir al campo Country y seleccionar Costa Rica", priority = 6)
    public void selectCountry() {
        try {
            Select dropdown = new Select(driver.findElement(By.id("CountryId")));
            dropdown.selectByVisibleText("Costa Rica");
            WebElement option = dropdown.getFirstSelectedOption();
            Assert.assertEquals(option.getText(), "Costa Rica");
        }catch (Exception e) {
            super.errorLog(e);
        }
    }


    @Test(testName = "Ir al campo Zip/ Postal code y agregar el texto '1000-1'", priority = 7)
    public void addPostalCode() {
        try {
            driver.findElement(By.id("ZipPostalCode")).sendKeys("1000-1");
            Assert.assertEquals(driver.findElement(By.id("ZipPostalCode")).getAttribute("value"), "1000-1");
        }catch (Exception e) {
            super.errorLog(e);
        }
    }

    @Test(testName = "Hacer click en el button 'estimate-shipping-button'", priority = 8)
    public void clickEstimateShipping() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.id("estimate-shipping-button")));
            driver.findElement(By.id("estimate-shipping-button")).click();
            Assert.assertEquals(driver.findElement(By.id("ZipPostalCode")).getAttribute("value"), "1000-1");
        }catch (Exception e) {
            super.errorLog(e);
        }
    }

    @Test(testName = "Aceptar términos y condiciones", priority = 9)
    public void clickTermsOfService() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.id("termsofservice")));
            driver.findElement(By.id("termsofservice")).click();
            Assert.assertTrue(driver.findElement(By.id("termsofservice")).isSelected());
        }catch (Exception e) {
            super.errorLog(e);
        }
    }

    @Test(testName = "Hacer click en check out", priority = 10)
    public void clickCheckOut() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
            driver.findElement(By.id("checkout")).click();
            waitForPageLoad();
            Assert.assertEquals(driver.findElement(By.className("login-button")).getAttribute("value"), "Log in");
        }catch (Exception e) {
            super.errorLog(e);
        }
    }

    @Test(testName = "Hacer click en Shopping cart y seleccionar Qty", priority = 11)
    public void setQty() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.id("topcartlink")));
            driver.findElement(By.id("topcartlink")).click();
            driver.findElement(By.className("qty-input")).clear();
            driver.findElement(By.className("qty-input")).sendKeys("0");
            Assert.assertEquals(driver.findElement(By.className("qty-input")).getAttribute("value"), "0");
        }catch (Exception e) {
            super.errorLog(e);
        }
    }


    @Test(testName = "Hacer click en Update Shopping cart", priority = 12)
    public void clickUpdateShoppingCart() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.name("updatecart")));
            driver.findElement(By.name("updatecart")).click();

            WebElement elem = driver.findElement(By.cssSelector(".page-body .no-data"));
            Assert.assertEquals(elem.getText(), "Your Shopping Cart is empty!");
            System.out.println("El TC3 se ejecutó correctamente");
        }catch (Exception e) {
            super.errorLog(e);
        }
    }

    private boolean validateURL(String endPoint) {
        String desired = "http://demo.nopcommerce.com/" + endPoint;
        return desired.equals(driver.getCurrentUrl());
    }

    private void waitForPageLoad(){
       wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
       System.out.println("Page Loaded Completely");
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
