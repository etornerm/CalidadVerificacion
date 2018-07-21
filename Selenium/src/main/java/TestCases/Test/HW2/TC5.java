package TestCases.Test.HW2;

import Config.BaseTest;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class TC5 extends BaseTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void setup() throws Exception {
        driver = super.getWebDriver();
        wait  = new WebDriverWait(driver, 10);
        super.getEnvUrl(driver);
    }

    @Test(testName = "Verificar un wishlist utilizando un excel", priority = 1)
    public void SearchBook() {
        try {
            Sheet sheetLoad = readExcel("/",System.getProperty("user.dir")+"\\Parametros.xlsx","Hoja1");
            //Find number of rows in excel file
            int rowCount = sheetLoad.getLastRowNum()-sheetLoad.getFirstRowNum();
            Row row;
            //Create a loop over all the rows of excel file to read it
            waitForPageLoad();

            for (int i = 1; i < rowCount+1; i++) {
                row = sheetLoad.getRow(i);

                driver.findElement(By.id("small-searchterms")).sendKeys(row.getCell(0).toString());
                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#small-search-box-form .search-box-button")));
                driver.findElement(By.cssSelector("#small-search-box-form .search-box-button")).click();

                Assert.assertFalse(driver.findElements(By.cssSelector(".no-result")).size() > 0);

                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".add-to-wishlist-button")));
                driver.findElement(By.cssSelector(".add-to-wishlist-button")).click();
                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".bar-notification.success a")));
                driver.findElement(By.cssSelector(".bar-notification.success a")).click();
                waitForPageLoad();

                driver.findElement(By.cssSelector(".qty-input")).sendKeys(Keys.chord(Keys.CONTROL, "a"),""+ Double.valueOf(row.getCell(1).toString()).intValue());
                driver.findElement(By.cssSelector(".update-wishlist-button")).click();
                waitForPageLoad();

                Assert.assertTrue(driver.findElement(By.cssSelector(".product-subtotal")).getText().equals(row.getCell(2).toString()));
                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".remove-from-cart input")));
                driver.findElement(By.cssSelector(".remove-from-cart input")).click();
                driver.findElement(By.cssSelector(".update-wishlist-button")).click();
                waitForPageLoad();

                System.out.println("El registro '"+row.getCell(0).toString()+"' se procesó correctamente.");
            }
            System.out.println("El TC5 se ejecutó correctamente");

        } catch (Exception e) {
            super.errorLog(e);
        }
    }

    private void waitForPageLoad(){
       wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
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
