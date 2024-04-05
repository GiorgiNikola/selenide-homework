package ge.tbcitacademy.tests;

import com.codeborne.selenide.*;
import ge.tbcitacademy.data.Constants;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.*;

public class CheckboxTests extends ConfigTests {


    @BeforeMethod
    public void before(){
        Configuration.reportsFolder = "CheckboxFailedTests";
    }

    @Test(priority = 1, groups = "CheckBoxes-FrontEnd")
    public void uncheckCheckBoxTest(){
        SoftAssert sfa = new SoftAssert();
        open(Constants.checkBoxURL);
        Configuration.assertionMode = AssertionMode.SOFT;
        ElementsCollection checkBoxes = $$("#checkboxes input");
        checkBoxes.get(1).setSelected(true);
        sfa.assertFalse(checkBoxes.get(1).isSelected());
        sfa.assertAll();
        System.out.println("I am the change in File2");
    }

    @Test(priority = 2, groups = "CheckBoxes-FrontEnd")
    public void checkCheckBoxTest(){
        SoftAssert sfa = new SoftAssert();
        open(Constants.checkBoxURL);
        ElementsCollection checkBoxes = $$("#checkboxes input");
        checkBoxes.get(0).setSelected(false);
        sfa.assertTrue(checkBoxes.get(0).isSelected());
        sfa.assertAll();
    }

}
