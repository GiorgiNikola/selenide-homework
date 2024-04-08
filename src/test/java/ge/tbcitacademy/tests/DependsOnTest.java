package ge.tbcitacademy.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ge.tbcitacademy.data.Constants;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class DependsOnTest extends ConfigTests{
    @BeforeMethod
    public void setup(){
        open(Constants.swoopLink);
    }

    @Test
    public void searchTest(){
        SelenideElement searchBox = $(".search-box input[class='reheadersearch']");
        searchBox.sendKeys(Constants.burgerTxt);
        SelenideElement searchButton = $(".SearchButton");
        searchButton.click();
        ElementsCollection offers = $$(".special-offer");
        for (SelenideElement offer : offers){
            offer.shouldHave(Condition.innerText(Constants.burgerTxt));
        }
        System.out.println("I am the change in file 1");
    }

    @Test(dependsOnMethods = "searchTest")
    public void validateIndividualOfferNameFromSearch(){
        SelenideElement searchBox = $(".search-box input[class='reheadersearch']");
        searchBox.sendKeys(Constants.burgerTxt);
        SelenideElement searchButton = $(".SearchButton");
        searchButton.click();
        ElementsCollection offers = $$(".special-offer");
        String offerTitle = offers.get(0).$("div.special-offer-title").getText();
        offers.get(0).click();
        SelenideElement title = $(".merchantTitle");
        title.shouldHave(Condition.text(offerTitle));
    }

    @AfterMethod
    public void tear(){
        closeWindow();
    }
}
