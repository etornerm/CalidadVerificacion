package Config;

import Config.json.JsonConfig;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.Reporter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gerardo.soto on 10/4/17.
 */
public class BaseTest {

    /**
     * This code is to load the json file, make sure that you are adding the correct path. If you need to add more than
     * one file you should duplicate the JSONObject with new name and path.
     */

    static JSONObject data = new JsonConfig().getJsonInfo(System.getProperty("user.dir") +
            "/jsonConfig/config.json");
    static JSONObject bsdata = (JSONObject) data.get("browserStack");

    private static final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd___HH-mm-ss");

    /**
     * In this section We define the key access to run the test in sauceLabs.
     */

    // SuaceLabs
    public static final String USERNAME = (String) data.get("saucelabsuser");
    public static final String ACCESS_KEY = (String) data.get("saucelabsaccesskey");
    public static final String URL = "http://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";

    // BrowserStack
    public static final String USERNAMEBS = (String) bsdata.get("namebs");
    public static final String AUTOMATE_KEY = (String) bsdata.get("automatekey");
    public static final String URLBS = "https://" + USERNAMEBS + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    /**
     * This method is the base to define the browser and which driver we want use. getWebDriver() is the only method
     * that you need put into @BeforeTest
     */

    public WebDriver getWebDriver() throws Exception {

        WebDriver driver;
        JSONObject sauceLabsInfo = (JSONObject) data.get("saucelabs");

        if ((boolean) data.get("remote")) {

            if ((boolean) data.get("saucelabstool")) {

                DesiredCapabilities caps = getDesiredCapabilities(data);
                caps.setCapability("platform", sauceLabsInfo.get("platform"));
                caps.setCapability("version", sauceLabsInfo.get("version"));
                caps.setCapability("screenResolution", sauceLabsInfo.get("screenResolution"));
                caps.setCapability("name", getTestName());
                driver = new RemoteWebDriver(new URL(URL), caps);

                return driver;
            } else {

                DesiredCapabilities capsbs = getDesiredCapabilities(data);
                capsbs.setCapability("browser", bsdata.get("browser"));
                capsbs.setCapability("browser_version", bsdata.get("browserversion"));
                capsbs.setCapability("os", bsdata.get("os"));
                capsbs.setCapability("os_version", bsdata.get("osversion"));
                capsbs.setCapability("resolution", bsdata.get("resolution"));
                capsbs.setCapability("name", getTestName());
                capsbs.setCapability("browserstack.debug", true);
                capsbs.setCapability("project", bsdata.get("project"));
                driver = new RemoteWebDriver(new URL(URLBS), capsbs);

                return driver;
            }

        } else {
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
//                cOptions.addArguments("test-type");
//                cOptions.addArguments("start-maximized");
//                cOptions.addArguments("--js-flags=--expose-gc");
//                cOptions.addArguments("--enable-precise-memory-info");
//                cOptions.addArguments("--disable-popup-blocking");
//                cOptions.addArguments("--disable-default-apps");
                cOptions.addArguments("disable-infobars");
                return new ChromeDriver(cOptions);
            } else if (data.get("browser").equals("ie")) {

                System.setProperty("webdriver.ie.driver", ".\\IEDriverServer.exe");
                return new InternetExplorerDriver();

            } else if (data.get("browser").equals("safari")) {

                return new SafariDriver();

            } else {

                throw new Exception("Browser is not correct");
            }
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
            case "safari":
                return DesiredCapabilities.safari();
            case "ie":
                return DesiredCapabilities.internetExplorer();
            case "edge":
                return DesiredCapabilities.edge();
            default:
                return DesiredCapabilities.firefox();
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
     * This method is to define the name of the Screencasts
     */

    public String getScreencastName(String Module) {
        Date date = new Date();
        String testName = this.getClass().getSimpleName();
        String dateSc = sdf.format(date);
        String SCName = testName + "-" + Module + "-" + dateSc;

        return SCName;
    }

    /**
     * @param driver
     * @throws IOException
     * @throws InterruptedException
     * This method take a screenshot for the entire page.
     */

    public void fullScreenShot (WebDriver driver) throws Exception {

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(2000);
        jse.executeScript("scroll(0, 0);");

        WebElement domBase = driver.findElement(By.tagName("html"));

        int ImageHeight = domBase.getSize().getHeight();
        System.out.println("The Height of the DOM is: " + ImageHeight);

        String height = (String) data.get("resolution-height");
        int valueheight = Integer.parseInt(height);
        System.out.println("The Height of the Window is: " + valueheight);

        double numberOfSS = ImageHeight / valueheight + 1;
        System.out.println("The number of panels are: " + numberOfSS);

        double sSLocation = (int) (ImageHeight / numberOfSS);
        System.out.println("The location is: " + sSLocation);

        BufferedImage screenshotFinal = null;

        for (int i = 0; i < numberOfSS; i++) {
            jse.executeScript("scroll(0, " + sSLocation * i + ")");
            Thread.sleep(3000);
            File screenshotTemp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            BufferedImage imageTemp = ImageIO.read(screenshotTemp);
            if(i == 0) {
                screenshotFinal = imageTemp;
            } else screenshotFinal = joinBufferedImage(screenshotFinal, imageTemp);
        }

        if (data.get("device")=="pc"){

            ImageIO.write(screenshotFinal, "png", new File(System.getProperty("user.dir") + data.get("screenshotspath") + "errors/" + getTestName() + ".png"));

        } else

        ImageIO.write(screenshotFinal , "png", new File(System.getProperty("user.dir") + data.get("screenshotspath") + "screencastTest/" + getTestName() + ".png"));
    }


    /**
     * This method is the logic to merge all image to get the final one.
     * @param img1
     * @param img2
     * @return
     */

    public static BufferedImage joinBufferedImage(BufferedImage img1, BufferedImage img2) {
        int offset = 2;
        int width = Math.max(img1.getWidth(), img2.getWidth()) + offset;
        int height = img1.getHeight() + img2.getHeight() + offset;
        BufferedImage newImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = newImage.createGraphics();
        Color oldColor = g2.getColor();
        g2.setPaint(Color.BLACK);
        g2.fillRect(0, 0, width, height);
        g2.setColor(oldColor);
        g2.drawImage(img1, null, 0, 0);
        g2.drawImage(img2, null, 0, img1.getHeight() + offset);
        g2.dispose();
        return newImage;
    }

    /**
     * @param driver
     * @param elementCss
     * @throws InterruptedException
     * @throws IOException
     * This method take a screenshot for a specific element, please take note that the element only works with CSS Selector
     */

    public File elementScreenShot(WebDriver driver, String elementCss) throws InterruptedException, IOException {

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        String folder = getScreencastName("elementSS");
        WebElement element = driver.findElement(By.cssSelector(elementCss));
        jse.executeScript("arguments[0].scrollIntoView();", element);
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenshotLocation = new File(data.get("screenshotspath") + folder + "/" + element + ".png");
        FileUtils.copyFile(screenshot, screenshotLocation);

        return screenshot;
    }

    /**
     * This method is used to take a screenshot when the test fails.
     * @param driver
     * @throws IOException
     */

    public void errorSS (WebDriver driver) throws IOException, InterruptedException {

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(2000);
        jse.executeScript("scroll(0, 0);");

        WebElement domBase = driver.findElement(By.tagName("html"));

        int ImageHeight = domBase.getSize().getHeight();
        System.out.println("The Height of the DOM is: " + ImageHeight);

        String height = (String) data.get("resolution-height");
        int valueheight = Integer.parseInt(height);
        System.out.println("The Height of the Window is: " + valueheight);

        double numberOfSS = ImageHeight / valueheight + 1;
        System.out.println("The number of panels are: " + numberOfSS);

        double sSLocation = (int) (ImageHeight / numberOfSS);
        System.out.println("The location is: " + sSLocation);

        BufferedImage screenshotFinal = null;

        for (int i = 0; i < numberOfSS; i++) {
            jse.executeScript("scroll(0, " + sSLocation * i + ")");
            Thread.sleep(3000);
            File screenshotTemp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            BufferedImage imageTemp = ImageIO.read(screenshotTemp);
            if(i == 0) {
                screenshotFinal = imageTemp;
            } else screenshotFinal = joinBufferedImage(screenshotFinal, imageTemp);
        }

        if (data.get("device")=="pc"){

            ImageIO.write(screenshotFinal, "png", new File(System.getProperty("user.dir") + data.get("screenshotspath") + "errors/" + getTestName() + ".png"));

        } else

        ImageIO.write(screenshotFinal, "png", new File(System.getProperty("user.dir") + data.get("screenshotspath") + "errors/" + getTestName() + ".png"));

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