package Config;

import Config.json.JsonConfig;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {

    static JSONObject data = new JsonConfig().getJsonInfo(System.getProperty("user.dir") +
            "/jsonConfig/config.json");
    private static final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd___HH-mm-ss");

    /**
     * This method is the base to define the browser and which driver we want use. getWebDriver() is the only method
     * that you need put into @BeforeTest
     */

    public WebDriver getWebDriver() throws Exception {

        WebDriver driver;

            System.out.println(data.get("browser"));
            System.out.println(data.get("device"));

            if (data.get("browser").equals("firefox")) {

                if (data.get("device").equals("pc")) {

                    System.setProperty("webdriver.firefox.marionette", System.getProperty("user.dir") + "\\geckodriver.exe");
                } else {

                    System.setProperty("webdriver.gecko.driver", "geckodriver");
                }
                return new FirefoxDriver();
            } else if (data.get("browser").equals("chrome")) {

                if (data.get("device").equals("pc")) {

                    System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
                } else {

                    System.setProperty("webdriver.chrome.driver", "chromedriver");
                }

                ChromeOptions cOptions = new ChromeOptions();
                cOptions.addArguments("disable-infobars");
                return new ChromeDriver(cOptions);
            } else {

                throw new Exception("Browser is not correct");
            }

    }

    /**
     * This section define the browser to use in sauceLabs, please notice that the browser should be relative to
     * platform and version defined in the json file.
     */

    private DesiredCapabilities getDesiredCapabilities(JSONObject data) {

        String browser = (String) data.get("browser");

        switch (browser.toLowerCase()) {
            case "firefox":
                return DesiredCapabilities.firefox();
            case "chrome":
                return DesiredCapabilities.chrome();
            default:
                return DesiredCapabilities.chrome();
        }
    }

    /**
     * This section define the URL to use, please notice that the driver is receiving from the testcase class.
     */

    public String getEnvUrl(WebDriver driver) throws InterruptedException {

        JSONObject urls = (JSONObject) data.get("urls");
        String width = (String) data.get("resolution-width");
        int valuewidth = Integer.parseInt(width);
        System.out.println("The width of the browser is : " + valuewidth);

        String height = (String) data.get("resolution-height");
        int valueheight = Integer.parseInt(height);
        System.out.println("The height of the browser is : " + valueheight);

        org.openqa.selenium.Dimension d = new org.openqa.selenium.Dimension(valuewidth,valueheight);
        driver.manage().window().setSize(d);

        if (((String) data.get("environment")).toLowerCase().equals("stage")) {

            driver.get((String) urls.get("stage"));
        } else {

            driver.get((String) urls.get("prod"));
        }

        return width;
    }

    public Sheet readExcel(String filePath,String fileName,String sheetName) throws IOException{
        //Create an object of File class to open xlsx file
        File file =    new File(filePath+"\\"+fileName);
        //Create an object of FileInputStream class to read excel file
        FileInputStream inputStream = new FileInputStream(file);
        Workbook excelFile = null;
        //Find the file extension by splitting file name in substring  and getting only extension name
        String fileExtensionName = fileName.substring(fileName.indexOf("."));
        //Check condition if the file is xlsx file
        if(fileExtensionName.equals(".xlsx")){
            //If it is xlsx file then create object of XSSFWorkbook class
            excelFile = new XSSFWorkbook(inputStream);
        }
        //Check condition if the file is xls file
        else if(fileExtensionName.equals(".xls")){
            //If it is xls file then create object of XSSFWorkbook class
            excelFile = new HSSFWorkbook(inputStream);
        }
        //Read sheet inside the workbook by its name
        return excelFile.getSheet(sheetName);
    }

    /**
     * This section define the path of URL to use in file name asserts.
     */

    public String defineEnvironmentPath() {

        JSONObject urls = (JSONObject) data.get("urls");

        if (((String) data.get("environment")).toLowerCase().equals("stage")) {

            return urls.get("stagePath").toString();
        } else {

            return urls.get("prod").toString();
        }
    }

    /**
     * This method is to define the name of the test in the report
     */

    public String getTestName() {
        Date date = new Date();
        String testName = this.getClass().getSimpleName();
        String dateTest = sdf.format(date);
        String className = testName + " " + dateTest;

        return className;
    }

    /**
     * This method is to get the info on fail status.
     * @param e
     * @return e
     */

    public Exception errorLog(Exception e){

        if ((Boolean)data.get("debugerror")){

            Reporter.log("<strong><h3> ERROR ********** FAIL " + getTestName() + " **********</h3></strong><br> The error is: " + e.getMessage().split("Session info:")[0] + "<br>");
            System.out.println("ERROR ********** FAIL " + getTestName() + " ********** The error is: " + e.getMessage());
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            Reporter.log("<strong><h4> STACK TRACE ********** " + getTestName() + " ********** </h4></strong><br> Here is the error " + errors.toString());
            System.out.println(" STACK TRACE ********** " + getTestName() + " ********** Here is the error " + errors.toString());
            Assert.fail("FAIL ********** " + getTestName() + " **********");

        } else {

            Reporter.log("<strong><h3> ERROR ********** FAIL " + getTestName() + " **********</h3></strong><br> The error is: " + e.getMessage().split("Session info:")[0] + "<br>");
            System.out.println("ERROR ********** FAIL " + getTestName() + " ********** The error is: " + e.getMessage());
            Assert.fail("FAIL ********** " + getTestName() + " **********");
        }

        return e;
    }
}