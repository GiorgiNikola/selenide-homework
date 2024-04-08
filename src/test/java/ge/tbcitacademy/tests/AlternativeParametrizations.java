package ge.tbcitacademy.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ge.tbcitacademy.data.Constants;
import ge.tbcitacademy.dataprovider.CustomDataProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class AlternativeParametrizations extends ConfigTests{

    @Parameters({"firstName", "lastName", "gender", "mobileNumber"})
    @Test(dataProvider = "studentInfoDataProvider", dataProviderClass = CustomDataProvider.class)
    public void fillFormWithParameters(String firstName, String lastName, String gender, String mobileNumber) {
        open(Constants.formLink);
        SelenideElement firstNameBox = $("#firstName");
        firstNameBox.sendKeys(firstName);
        SelenideElement lastNameBox = $("#lastName");
        lastNameBox.sendKeys(lastName);
        if (gender.equalsIgnoreCase("male")){
            SelenideElement maleBtn = $("label[for='gender-radio-1']");
            maleBtn.scrollTo().click();
        }else if (gender.equalsIgnoreCase("female")){
            SelenideElement femaleBtn = $("label[for='gender-radio-2']");
            femaleBtn.scrollTo().click();
        }else{
            SelenideElement otherBtn = $("label[for='gender-radio-3']");
            otherBtn.scrollTo().click();
        }
        SelenideElement mobileNumberBox = $("#userNumber");
        mobileNumberBox.sendKeys(mobileNumber);
        SelenideElement submitBtn = $("#submit");
        submitBtn.scrollTo().click();
        SelenideElement studentName = $x("//td[normalize-space()='Student Name']")
                .sibling(0)
                .shouldHave(Condition.text(firstName + " " + lastName));
        System.out.println("I am the fix in file2");
    }

    @AfterMethod
    public void tear(){
        closeWindow();
    }
}
