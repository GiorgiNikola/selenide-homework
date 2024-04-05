import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ge.tbcitacademy.data.Constants;
import ge.tbcitacademy.listeners.CustomReportListener;
import ge.tbcitacademy.listeners.CustomSuiteListener;
import ge.tbcitacademy.listeners.CustomTestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
@Listeners({CustomSuiteListener.class, CustomTestListener.class, CustomReportListener.class})
public class SelenideTests extends ConfigTests {
    @Test
    public void validateBundleOffers(){
        open(Constants.telerikURL);
        SelenideElement pricingBtn = $x("//a[@class='TK-Menu-Item-Link'][text()='Pricing']");
        pricingBtn.click();
        ElementsCollection uiFeatures = $$x("//th[@class='UI is-active']//child::ul/li");
        ElementsCollection completeFeatures = $$x("//th[@class='Complete']//child::ul/li");
        ElementsCollection ultimateFeatures = $$x("//th[@class='Ultimate']//child::ul/li");
        for (SelenideElement element : uiFeatures){
            element.shouldNotHave(text(Constants.mockingSolutionTxt));
            element.shouldNotHave(text(Constants.endToEndReport));
        }
        for (SelenideElement element : completeFeatures){
            element.shouldNotHave(text(Constants.endToEndReport));
        }
        ultimateFeatures.shouldHave(CollectionCondition.itemWithText(Constants.endToEndReport));

        SelenideElement ultimateSupport = $x("//th[@class='Ultimate']//p[@class='u-fs12 u-fwn u-mb0']");
        SelenideElement completeSupport = $x("//th[@class='Complete']//p[@class='u-fs12 u-fwn u-mb0']");
        SelenideElement uiSupport = $("th[class='UI is-active'] p[class='u-fs12 u-fwn u-mb0']");
        ultimateSupport.shouldHave(partialText(Constants.issueEscalation));
        completeSupport.shouldNotHave(partialText(Constants.issueEscalation));
        uiSupport.shouldNotHave(partialText(Constants.issueEscalation));

        SelenideElement telerikTestStudio = $x("//p[contains(text(),'Telerik Test Studio')]/parent::td");
        ElementsCollection telerikTestStudioTds = telerikTestStudio.parent().$$(Constants.tdTxt);
        for (int i = 0; i < telerikTestStudioTds.size(); i++){
            if (i == telerikTestStudioTds.size() - 1){
                Assert.assertTrue(telerikTestStudioTds.get(i).innerHtml().contains(Constants.dotTxt));
                break;
            }
            Assert.assertFalse(telerikTestStudioTds.get(i).innerHtml().contains(Constants.dotTxt));
        }

        ElementsCollection uiForJqueryTds = $$x("//td[text()='Kendo UI for jQuery']/following-sibling::td");
        for (int i = 0; i < uiForJqueryTds.size(); i++){
            Assert.assertTrue(uiForJqueryTds.get(i).innerHtml().contains(Constants.dotTxt));
        }

        SelenideElement telerikReportServer = $x("//p[contains(text(),'Telerik Report Server')]/parent::td/following-sibling::td[3]");
        telerikReportServer.shouldHave(innerText(Constants.telerikReportTxt));

        ElementsCollection telerikReportingTds = $$x("//p[contains(text(),'Telerik Reporting')]/parent::td/following-sibling::td");
        Assert.assertFalse(telerikReportingTds.get(0).innerHtml().contains(Constants.dotTxt));
        Assert.assertTrue(telerikReportingTds.get(1).innerHtml().contains(Constants.dotTxt));
        Assert.assertTrue(telerikReportingTds.get(2).innerHtml().contains(Constants.dotTxt));

        ElementsCollection accessToVideosTds = $$x("//td[contains(text(),'Access to on-demand videos')]/following-sibling::td");
        for (SelenideElement element : accessToVideosTds){
            Assert.assertTrue(element.innerHtml().contains(Constants.dotTxt));
        }
    }

    @Test
    public void validateIndividualOffers(){
        open(Constants.telerikURL);
        SelenideElement acceptCookies = $(withText(Constants.acceptCookies));
        acceptCookies.click();
        SelenideElement pricingBtn = $x("//a[@class='TK-Menu-Item-Link'][text()='Pricing']");
        pricingBtn.click();
        SelenideElement individualProductsBtn = $x("//span[text()='Individual Products']");
        individualProductsBtn.click();

        SelenideElement offer1 = $("div[data-opti-expid='Kendo UI']").shouldBe(visible);
        SelenideElement offer2 = $("div[data-opti-expid='KendoReact']");
        offer1.scrollTo();
        offer1.hover();
        SelenideElement offer1Ninja = $("img[title='Kendo Ui Ninja']").shouldBe(visible);
        offer2.hover();
        SelenideElement offer2Ninja = $("img[title='Kendo React Ninja']").shouldBe(visible);

        SelenideElement dropDown1 = $x("//div[@class='Dropdown u-pr u-zi1 js-select-pricing-item u-ha u-mb1']/a");
        dropDown1.shouldHave(text(Constants.prioritySupportTxt));

        SelenideElement dropDown2 = $x("//div[@id='ContentPlaceholder1_C714_Col01']//div[@class='Dropdown u-pr u-zi1 u-ha u-mb1 js-select-pricing-item']/a");
        dropDown2.shouldHave(text(Constants.prioritySupportTxt));

        SelenideElement kendoReactPrice = $("div[id='ContentPlaceholder1_C714_Col01'] span[class='js-price']");
        kendoReactPrice.shouldHave(text(Constants.kendoReactPrice));

        SelenideElement kendoUiPrice = $("div[data-opti-expid='Kendo UI'] span[class='js-price']");
        kendoUiPrice.shouldHave(text(Constants.kendoUiPrice));
    }

    @Test
    public void checkBoxTest(){
        open(Constants.checkBoxURL);
        ElementsCollection checkBoxes = $$("form#checkboxes input");
        checkBoxes.get(0).click();
        checkBoxes.get(0).shouldHave(type(Constants.checkBoxTxt));
        checkBoxes.get(1).shouldHave(type(Constants.checkBoxTxt));
    }

    @Test(description = "This should also fail")
    public void dropDownTest(){
        open(Constants.dropDownURL);
        SelenideElement dropDown = $(byId(Constants.dropDownTxt));
        dropDown.getSelectedOption().shouldHave(text(Constants.selectOptionTxt));
        dropDown.selectOption(Constants.option2Txt);
        dropDown.getSelectedOption().shouldHave(text(Constants.option2Txt + "fail this"));
    }

    @Test
    public void collectionsTest(){
        open(Constants.textBoxURL);
        SelenideElement fullName = $(byId("userName"));
        SelenideElement email = $(by("type", "email"));
        SelenideElement currentAddress = $(by("placeholder", "Current Address"));
        SelenideElement permanentAddress = $x("//textarea[@id='permanentAddress']");

        fullName.sendKeys(Constants.fullName);
        email.sendKeys(Constants.myEmail);
        currentAddress.sendKeys(Constants.currentAddress);
        permanentAddress.sendKeys(Constants.permanentAddress);

        SelenideElement submitBtn = $("#submit");
        submitBtn.scrollIntoView(true);
        submitBtn.click();

        ElementsCollection result = $$("div.border.col-md-12 p");
        result.shouldHave((CollectionCondition.textsInAnyOrder(
                Constants.nameTxt + Constants.fullName,
                Constants.emailTxt + Constants.myEmail,
                Constants.currentAddressTxt + Constants.currentAddress,
                Constants.permanentAddressTxt + Constants.permanentAddress
        )));
    }

}
