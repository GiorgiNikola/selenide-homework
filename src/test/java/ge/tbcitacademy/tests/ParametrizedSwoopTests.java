package ge.tbcitacademy.tests;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ge.tbcitacademy.data.Constants;
import ge.tbcitacademy.dataprovider.CustomDataProvider;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class ParametrizedSwoopTests extends ConfigTests{
    @BeforeMethod
    public void before(){
        open(Constants.swoopLink);
    }

    @Test(dataProvider = "offersDataProvider", dataProviderClass = CustomDataProvider.class)
    public void checkSaleValuesTest(String offerName, int originalPrice, int discount){
        SelenideElement sportSection = $("div.Menus")
                .$(byAttribute("href","/category/110/sporti"));
        sportSection.click();
        ElementsCollection offers = $$(".special-offer");
        AtomicInteger atomicExpectedPrice = new AtomicInteger(Integer.MAX_VALUE);
        offers.filterBy(innerText(offerName))
                .get(0).$$(".deal-voucher-price").forEach(e -> {
                    if (!e.getText().isBlank()){
                        int price = Integer.parseInt(e.getText().trim().replaceAll("[^\\d.]", ""));
                        // intellij indicated to use AtomicInteger, and it works fine.
                        if (price < atomicExpectedPrice.get()) {
                            atomicExpectedPrice.set(price);
                        }
                    }
                });
        int actualPrice = atomicExpectedPrice.get();
        Assert.assertEquals(actualPrice, originalPrice - discount);
        System.out.println("I am the fix in file3");
    }

    @Test(dataProvider = "offerNamesDataProvider", dataProviderClass = CustomDataProvider.class)
    public void validateCartBehavior(String offerName){
        SelenideElement sportSection = $("div.Menus")
                .$(byAttribute("href","/category/110/sporti"));
        sportSection.click();
        SelenideElement acceptCookieBtn = $(".acceptCookie");
        acceptCookieBtn.click();
        SelenideElement offer = $$(".special-offer").filterBy(innerText(offerName)).get(0);
        offer
                .scrollTo()
                .click();
        SelenideElement addToCart = $(".addBasket.add-item-animation");
        addToCart.click();
        SelenideElement cartBtn = $("img[src='/Images/NewDesigneImg/ReHeader/basket.svg']");
        cartBtn.click();
        SelenideElement itemTitleInBasket = $(".item-title.desktop-version a");
        Assert.assertEquals(itemTitleInBasket.getText(), offerName);
    }

    @AfterMethod
    public void tear(){
        closeWindow();
    }
}
