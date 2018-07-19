package TestCases.Test.HW2;

import Config.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TC4 extends BaseTest {
    
    WebDriver driver;
    WebDriverWait wait;
    
    @BeforeTest
    public void setup() throws Exception {
        driver = super.getWebDriver();
        wait  = new WebDriverWait(driver, 10);
        super.getEnvUrl(driver);
    }
    
    @Test(testName = "Conectarse a la pagina web", priority = 1)
    public void OpenWebSite() {
        try {
            Assert.assertTrue(validateURL(""));
        }catch (Exception e) {
            super.errorLog(e);
        }
    }
    
    private boolean validateURL(String endPoint) {
        String desired = "http://demo.nopcommerce.com/" + endPoint;
        return desired.equals(driver.getCurrentUrl());
    }
    
    @Test(testName = "Ir a la categoria software", priority = 2)
    public void NavigateToSoftware() {
        try {
            Actions action = new Actions(driver);
            action.moveToElement(driver.findElement(By.linkText("Computers"))).perform();
            driver.findElement(By.linkText("Software")).click();
            Assert.assertTrue(validateURL("software"));
        }catch (Exception e) {
            super.errorLog(e);
        }
    }
    
    @Test(testName = "Cambiar despliegue a lista", priority = 3)
    public void ChangeToListView() {
        try {
            driver.findElement(By.cssSelector("a.viewmode-icon.list")).click();
            WebElement elem = driver.findElement(By.cssSelector("a.viewmode-icon.list"));
            Assert.assertTrue(elem.getAttribute("class").contains("selected"));
        }catch (Exception e) {
            super.errorLog(e);
        }
    }
    
    @Test(testName = "Cambiar el orden de despliegue por precio menor a mayor", priority = 4)
    public void ChangeSortToPriceLowToHigh() {
        try {
            ChangeSorting("Price: Low to High");
            Assert.assertTrue(DropdownOptionIsSelected("products-orderby", "Price: Low to High"));
        }catch (Exception e) {
            super.errorLog(e);
        }
    }
    
    private void ChangeSorting(String desiredSortText) {
        Select dropdown = new Select(driver.findElement(By.id("products-orderby")));
        dropdown.selectByVisibleText(desiredSortText);
    }
    
    private boolean DropdownOptionIsSelected(String dropdownId, String optionText) {
        Select dropdown = new Select(driver.findElement(By.id(dropdownId)));
        return dropdown.getFirstSelectedOption().getText().equals(optionText);
    }
    
    @Test(testName = "Confirmar primer elemento por precio: menor a mayor", priority = 5)
    public void ConfirmFirstElementLowToHigh() {
        try {
            boolean result = ConfirmFirstElement("Sound Forge Pro 11 (recurring)");
            Assert.assertTrue(result);
        }catch (Exception e) {
            super.errorLog(e);
        }
    }
    
    private boolean ConfirmFirstElement(String elementName) {
        WebElement elem = driver.findElement(By.cssSelector(".product-title"));
        return elem.getText().equals(elementName);
    }
    
    @Test(testName = "Cambiar el orden de despliegue por precio mayor a menor", priority = 6)
    public void ChangeSortToPriceHighToLow() {
        try {
            ChangeSorting("Price: High to Low");
            Assert.assertTrue(DropdownOptionIsSelected("products-orderby", "Price: High to Low"));
        }catch (Exception e) {
            super.errorLog(e);
        }
    }
    
    @Test(testName = "Confirmar primer elemento por precio: menor a mayor", priority = 7)
    public void ConfirmFirstElementHighToLow() {
        try {
            boolean result = ConfirmFirstElement("Adobe Photoshop CS4");
            Assert.assertTrue(result);
        }catch (Exception e) {
            super.errorLog(e);
        }
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
