import com.codeborne.selenide.*;
import com.codeborne.selenide.conditions.Not;
import ge.tbcitacademy.data.Constants;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.*;

public class RadioButtonTests extends ConfigTests{
    SoftAssert sfa = new SoftAssert();
    @BeforeMethod
    public void before(){
        open(Constants.checkBoxURL);
        Configuration.reportsFolder = "CheckboxFailedTests";
    }

    @Test
    public void uncheckCheckBoxTest(){
        Configuration.assertionMode = AssertionMode.SOFT;
        ElementsCollection checkBoxes = $$("#checkboxes input");
        checkBoxes.get(1).setSelected(true);
        sfa.assertFalse(checkBoxes.get(1).isSelected());
    }

    @Test
    public void checkCheckBoxTest(){
        SoftAssert sfa = new SoftAssert();
        ElementsCollection checkBoxes = $$("#checkboxes input");
        checkBoxes.get(0).setSelected(false);
        sfa.assertTrue(checkBoxes.get(0).isSelected());
        System.out.println("I am the Fix");
    }

    @AfterTest
    public void tear(){
        sfa.assertAll();
    }

}
