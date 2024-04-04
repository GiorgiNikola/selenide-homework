import com.codeborne.selenide.*;
import ge.tbcitacademy.data.Constants;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTests2 extends TestConfig{
    SoftAssert sfa = new SoftAssert();
    @Test
    public void validateDemosDesign(){
        open(Constants.telerikURL);
        SelenideElement acceptCookies = $(withText(Constants.acceptCookies));
        acceptCookies.click();
        ElementsCollection webHoverElements = $(byTitle("Kendo Ui"))
                .ancestor(".row u-mb8")
                .$$(".HoverImg");
        for (SelenideElement element : webHoverElements){
            element.hover();
            element.$("img")
                    .shouldHave(Condition.cssValue("opacity", "0.1"));
            element.shouldHave(Condition.cssValue("background-color", "rgba(40, 46, 137, 0.75)"));
        }
        SelenideElement hoverElement = $(byTitle("Kendo Ui")).parent();
        SelenideElement innerElement = hoverElement.$(byText("UI for Vue demos"));
        hoverElement.scrollIntoView(false);
        hoverElement.hover();
        innerElement.shouldBe(Condition.appear);

        ElementsCollection desktopElements = $(byTitle("winforms"))
                .ancestor(".row u-mb8")
                .$$(".sf_colsIn");
        ElementsCollection filteredDesktopElements = desktopElements
                .filter(Condition.attributeMatching("title", "Get It from Microsoft"));
        SelenideElement mobileElement = $(byTitle("Telerik UI for Xamarin Demos Overview"))
                .ancestor(".row u-mb8");
        sfa.assertTrue(mobileElement.innerHtml().contains(Constants.downloadFromApple));
        sfa.assertTrue(mobileElement.innerHtml().contains(Constants.downloadFromGoogle));
        sfa.assertTrue(mobileElement.innerHtml().contains(Constants.downloadFromMicrosoft));
        ElementsCollection sectionLinks = $$x("//div[@class='container']//a");
        Selenide.executeJavaScript("window.scrollTo(0, document.body.scrollHeight)");
        for (SelenideElement element : sectionLinks){
            element.shouldHave(cssValue("position", "static"));
        }
        sectionLinks.get(0).click();
        SelenideElement webSection = $(byTitle("Kendo Ui")).ancestor(".row u-mb8").shouldBe(appear);
        sectionLinks.get(1).click();
        SelenideElement desktopSection = $(byTitle("winui-product-thumb")).ancestor(".row u-mb8").shouldBe(appear);
        sectionLinks.get(2).click();
        SelenideElement mobileSection = $(byTitle("Telerik UI for Xamarin Demos Overview")).ancestor(".row u-mb8").shouldBe(appear);
        sfa.assertAll();
        System.out.println("I AM The Change");
    }

    @Test
    public void validateOrderMechanics(){
        open(Constants.telerikURL);
        SelenideElement acceptCookies = $(withText(Constants.acceptCookies));
        acceptCookies.click();
        SelenideElement pricingBtn = $x("//a[@class='TK-Menu-Item-Link'][text()='Pricing']");
        pricingBtn.click();
        SelenideElement buyBtn = $("th[class='Complete'] a[class='Btn Btn--prim4 u-db']");
        buyBtn.click();
        acceptCookies.click();
        SelenideElement dismissBtn = $(".far.fa-times.label.u-cp");
        dismissBtn.click();
        SelenideElement priceElement = $("span[class='e2e-price-per-license ng-star-inserted']");
        //Double price = Double.parseDouble(priceElement.getText().trim().replaceAll("[^\\d.]", ""));
        sfa.assertEquals(priceElement.getText(), Constants.firstPrice);
        SelenideElement dropDown = $("period-select[class='u-db td-cell--data border-left'] div[class='ng-star-inserted']");
        dropDown.click();
        ElementsCollection options = $$x("//ul[@role='listbox']//li").shouldBe();
        options.get(1).click();
        SelenideElement subTotalPriceElement = $(".u-mt8.e2e-cart-item-subtotal.sm-no-spacing.td-cell.td-cell--data").shouldBe(visible);
        //sfa.assertEquals(subTotalPriceElement.getText(), "$2,505.55");
        sfa.assertAll();
    }

    @Test
    public void chainedLocatorsTest(){
        open("https://demoqa.com/books");
        ElementsCollection books = $$(".rt-tr-group");
        ElementsCollection filteredBooks = books
                .filterBy(Condition.innerText(Constants.publisher))
                .filterBy(Condition.partialText(Constants.innerText));
        ElementsCollection booksWithImg = $$(".rt-tr-group img");
        for (SelenideElement book : booksWithImg){
            book.shouldBe(image);
        }
    }

    @Test
    public void softAssertTest(){
        open("https://demoqa.com/books");
        ElementsCollection books = $(".rt-tbody")
                .findAll(".rt-tr-group")
                .filterBy(Condition.innerText(Constants.publisher))
                .filterBy(Condition.partialText(Constants.innerText));
        sfa.assertEquals(books.size(), 10);
        sfa.assertEquals(books.get(0), Constants.firstBookName);
        sfa.assertAll();
    }
}
