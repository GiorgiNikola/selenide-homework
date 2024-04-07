package ge.tbcitacademy.tests;

import com.codeborne.selenide.*;
import ge.tbcitacademy.data.Constants;
import ge.tbcitacademy.listeners.CustomReportListener;
import ge.tbcitacademy.listeners.CustomSuiteListener;
import ge.tbcitacademy.listeners.CustomTestListener;
import ge.tbcitacademy.tests.ConfigTests;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.text.DecimalFormat;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

@Listeners({CustomSuiteListener.class, CustomTestListener.class, CustomReportListener.class})
public class SelenideTests2 extends ConfigTests {

    @Test(groups = "Selenide 2")
    public void validateDemosDesign(){
        SoftAssert sfa = new SoftAssert();
        open(Constants.telerikURL);
        SelenideElement acceptCookies = $(withText(Constants.acceptCookies));
        acceptCookies.click();
        ElementsCollection webHoverElements = $(byTitle("Kendo Ui"))
                .ancestor(".row u-mb8")
                .$$(".HoverImg");
        for (SelenideElement element : webHoverElements){
            element.scrollTo();
            element.hover();
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
    }

    @Test(groups = "Selenide 2")
    public void validateOrderMechanics(){
        SoftAssert sfa = new SoftAssert();
        open(Constants.telerikURL);
        SelenideElement acceptCookies = $(withText(Constants.acceptCookies));
        SelenideElement pricingBtn = $x("//a[@class='TK-Menu-Item-Link'][text()='Pricing']");
        pricingBtn.click();
        SelenideElement buyBtn = $("th[class='Complete'] a[class='Btn Btn--prim4 u-db']");
        buyBtn.click();
        acceptCookies.click();
        SelenideElement dismissBtn = $(".far.fa-times.label.u-cp");
        dismissBtn.click();
        SelenideElement priceElement = $("span[class='e2e-price-per-license ng-star-inserted']");
        sfa.assertEquals(priceElement.getText(), formatCurrency(Constants.unitPrice));
        SelenideElement dropDown = $("period-select[class='u-db td-cell--data border-left'] div[class='ng-star-inserted']");
        dropDown.click();
        ElementsCollection options = $$x("//ul[@role='listbox']//li").shouldBe();
        options.filter(Condition.innerText(Constants.oneYearTxt)).get(0).click();
        double subTotalPrice = Constants.unitPrice + calculatePercentage(Constants.maintenancePrice, Constants.unitPricePercent);
        SelenideElement subTotalPriceElement = $(".u-mt8.e2e-cart-item-subtotal.sm-no-spacing.td-cell.td-cell--data")
                .shouldHave(text(formatCurrency(subTotalPrice)));
        SelenideElement quantityDropDown = $(".td-cell--data.border-left.ng-star-inserted");
        quantityDropDown.click();
        options.filter(Condition.innerText("2")).get(0).click();
        subTotalPrice = calculatePercentage(Constants.unitPrice * 2, Constants.unitPricePercent)
                + calculatePercentage(Constants.maintenancePrice * 2, Constants.maintenancePricePercent);
        subTotalPriceElement.shouldHave(text(formatCurrency(subTotalPrice)));
        SelenideElement questionMark = $("i[class='far fa-question-circle tooltip-icon']");
        questionMark.hover();
        double licenceDiscountPrice = calculatePercentage(Constants.unitPrice * 2, Constants.unitDiscountPercent);
        SelenideElement licensesDiscount = $(".u-pr5.e2e-licenses-discounts").shouldHave(text(formatCurrency(licenceDiscountPrice)));
        double maintenanceDiscountPrice = calculatePercentage(Constants.maintenancePrice * 2, Constants.maintenanceDiscountPercent);
        SelenideElement maintenanceDiscount = $(".u-pr5.e2e-ms-discounts").shouldHave(text(formatCurrency(maintenanceDiscountPrice)));
        double totalDiscount = textToDouble(licensesDiscount.getText()) + textToDouble(maintenanceDiscount.getText());
        SelenideElement totalDiscountElement =  $(".u-fr.e2e-total-discounts-price").shouldHave(partialText(formatCurrency(totalDiscount)));
        SelenideElement continueAsGuest = $(byText(Constants.continueAsGuestTxt));
        continueAsGuest.click();
        SelenideElement firstName = $("#biFirstName");
        firstName.sendKeys(Constants.fullName);
        SelenideElement lastName = $("#biLastName");
        lastName.sendKeys(Constants.lastName);
        SelenideElement email = $("#biEmail");
        email.sendKeys(Constants.myEmail);
        SelenideElement company = $("#biCompany");
        company.sendKeys(Constants.companyName);
        SelenideElement phone = $("#biPhone");
        phone.sendKeys(Constants.myPhone);
        SelenideElement address = $("#biAddress");
        address.sendKeys(Constants.currentAddress);
        SelenideElement countrySelect = $(".k-select");
        countrySelect.click();
        //countrySelect.sendKeys("Georgia");
        options.filter(Condition.text(Constants.myCountry)).get(0).click();
        SelenideElement city = $("#biCity");
        city.sendKeys(Constants.permanentAddress);
        SelenideElement postalCode = $("#biZipCode");
        postalCode.sendKeys(Constants.postalCode);
        SelenideElement continueBtn = $(byText(Constants.continueTxt));
        continueBtn.click();
        SelenideElement backBtn = $(byText(Constants.backTxt));
        backBtn.click();
        firstName.shouldHave(Condition.value(Constants.fullName));
        lastName.shouldHave(Condition.value(Constants.lastName));
        email.shouldHave(Condition.value(Constants.myEmail));
        company.shouldHave(Condition.value(Constants.companyName));
        phone.shouldHave(Condition.value(Constants.myPhone));
        address.shouldHave(Condition.value(Constants.currentAddress));
        SelenideElement country = $(".k-dropdown-wrap.k-state-default input.k-input").shouldHave(value(Constants.myCountry));
        city.shouldHave(Condition.value(Constants.permanentAddress));
        postalCode.shouldHave(value(Constants.postalCode));
        sfa.assertAll();
    }

    @Test(groups = "Selenide 2")
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

    @Test(description = "This test intentionally fails!", groups = "Selenide 2")
    public void softAssertTest(){
        SoftAssert sfa = new SoftAssert();
        open("https://demoqa.com/books");
        ElementsCollection books = $(".rt-tbody")
                .findAll(".rt-tr-group")
                .filterBy(Condition.innerText(Constants.publisher))
                .filterBy(Condition.partialText(Constants.innerText));
        sfa.assertEquals(books.size(), 10);
        sfa.assertEquals(books.get(0), Constants.firstBookName);
        sfa.assertAll();
    }

    public static String formatCurrency(double value) {
        DecimalFormat df = new DecimalFormat("$#,##0.00");
        return df.format(value);
    }

    public static Double textToDouble(String text){
        return Double.parseDouble(text.trim().replaceAll("[^\\d.]", ""));
    }

    public static double calculatePercentage(double total, double percent) {
        if (total == 0) {
            throw new IllegalArgumentException("Total value cannot be zero");
        }
        return (total * percent) / 100.0;
    }
}
