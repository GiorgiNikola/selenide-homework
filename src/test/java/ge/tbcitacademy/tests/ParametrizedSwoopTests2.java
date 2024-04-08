package ge.tbcitacademy.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ge.tbcitacademy.data.Constants;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class ParametrizedSwoopTests2 extends ConfigTests{
    private String sectionName;

    public ParametrizedSwoopTests2(String sectionName) {
        this.sectionName = sectionName;
    }

    @BeforeMethod
    public void setup(){
        open(Constants.swoopLink);
    }

    @Test
    public void checkDividePriceBtnTest(){
        ElementsCollection sections = $$(".Menus li");
        sections.filter(Condition.innerText(sectionName)).get(0).click();
        SelenideElement divideIntoFourMonthsBtn = $(".MenuSponsored.TbcGanacileba")
                .shouldBe(Condition.visible);
        System.out.println("I am the fix in file4");
    }

    @AfterMethod
    public void tear(){
        closeWindow();
    }
}
