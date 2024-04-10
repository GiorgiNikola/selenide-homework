package ge.tbcitacademy.tests;

import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import com.codeborne.selenide.Selenide;
import ge.tbcitacademy.data.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;

public class ConfigTests {
    @BeforeSuite(alwaysRun = true)
    public void initialSetup(){
        Configuration.timeout = 12000;
        Configuration.reopenBrowserOnFail = true;
        Configuration.screenshots = true;
        Configuration.fileDownload = FileDownloadMode.HTTPGET;
        Configuration.pageLoadTimeout = 20000;
    }
    @BeforeTest(alwaysRun = true)
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser){
        if (browser.equalsIgnoreCase(Constants.chromeName)){
            Configuration.browser = "chrome";
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            Configuration.browserCapabilities = options;
            Configuration.browserSize = null;
        } else if (browser.equalsIgnoreCase(Constants.edgeName)){
            Configuration.browser = "edge";
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--start-maximized");
            Configuration.browserCapabilities = options;
            Configuration.browserSize = null;
        } else if (browser.equalsIgnoreCase(Constants.firefoxName)){
            Configuration.browser = "firefox";
            FirefoxOptions options = new FirefoxOptions();
            // start maximized does not work for firefox
            options.addArguments("--start-maximized");
            Configuration.browserCapabilities = options;
            Configuration.browserSize = null;
        }
    }

}
