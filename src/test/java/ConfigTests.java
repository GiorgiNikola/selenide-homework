import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import ge.tbcitacademy.data.Constants;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;

public class TestConfig {
    @BeforeSuite
    public void config(){
        Configuration.timeout = 12000;
        Configuration.reopenBrowserOnFail = true;
    }
    @BeforeTest
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

    @AfterClass
    public void tearDown(){
        Selenide.closeWindow();
    }
}
