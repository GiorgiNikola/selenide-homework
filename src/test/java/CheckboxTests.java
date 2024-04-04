import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CheckboxTests {
    @BeforeMethod
    public void before(){
        Configuration.reportsFolder = "RadioButtonFailedTests";
    }
    @Test
    public void selectYesTest(){
        SoftAssert sfa = new SoftAssert();
        open("https://demoqa.com/radio-button");
        SelenideElement yesElement = $("label[for='yesRadio']");
        yesElement.click();
        sfa.assertFalse(yesElement.isSelected());
        sfa.assertAll();
    }
}
