package ge.tbcitacademy.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import ge.tbcitacademy.retry.RetryAnalyzer;
import ge.tbcitacademy.retry.RetryCount;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.*;

public class RadioButtonTests extends ConfigTests{
    @BeforeMethod
    public void before(){
        Configuration.reportsFolder = "RadioButtonFailedTests";
    }
    @Test(groups = "RadioButtons-FrontEnd")
    public void selectYesTest(){
        SoftAssert sfa = new SoftAssert();
        open("https://demoqa.com/radio-button");
        SelenideElement yesElement = $("label[for='yesRadio']");
        yesElement.click();
        sfa.assertTrue(yesElement.isSelected());
        sfa.assertAll();
        System.out.println("I am the change in file1");
    }

    // ეს ტესტი აფეილებს ხუთჯერ
    @RetryCount(count = 5)
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "RadioButtons-FrontEnd")
    public void failTests(){
        SoftAssert sfa = new SoftAssert();
        open("https://demoqa.com/radio-button");
        SelenideElement yesElement = $("label[for='yesRadio']");
        yesElement.click();
        sfa.assertTrue(yesElement.isImage());
        sfa.assertAll();
    }
}
