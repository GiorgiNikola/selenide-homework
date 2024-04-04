import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import ge.tbcitacademy.data.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class TestConfig {
    @BeforeClass
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser){
        if (browser.equalsIgnoreCase(Constants.chromeName)){
            WebDriverManager.chromedriver().setup();
            Configuration.browser = "chrome";
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            Configuration.browserCapabilities = options;
            Configuration.browserSize = null;
        } else if (browser.equalsIgnoreCase(Constants.firefoxName)){
            WebDriverManager.firefoxdriver().setup();
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
