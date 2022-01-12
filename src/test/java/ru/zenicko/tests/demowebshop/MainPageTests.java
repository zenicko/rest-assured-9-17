package ru.zenicko.tests.demowebshop;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import ru.zenicko.demowebshop.ApiSteps;
import ru.zenicko.demowebshop.UserData;

import java.util.Map;

import static io.qameta.allure.Allure.step;

public class MainPageTests {
    static Map<String, String> cookies;

    @BeforeAll
    @DisplayName("Set sign-in cookies")
    static void SetUp(){
        cookies = ApiSteps.getCookiesAfterSignIn(UserData.setUsedData("timrode@mail.com","123456", false), ApiSteps.getBaseUrl(), "/login");
        Selenide.open(ApiSteps.getBaseUrl() + "/Themes/DefaultClean/Content/images/logo.png");
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("NOPCOMMERCE.AUTH", cookies.get("NOPCOMMERCE.AUTH")));
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("Nop.customer", cookies.get("Nop.customer")));
    }


    @Test
    @DisplayName("Check the user email in header main page after sign-in")
    void shouldBeEmail() {
        step("Open the main page by exist user",()->{
            Selenide.open(ApiSteps.getBaseUrl());
        });
        step("The emails are equal?", ()->
            Assertions.assertThat(Selenide.$("a.account").getText()).isEqualTo(UserData.getEmailUser())
        );
    }

    @Test
    @DisplayName("Check The basket")
    void shouldBeBasketIsEmpty() {
        step("Open the main page by exist user",()->{
            Selenide.open(ApiSteps.getBaseUrl());
        });
        step("Shopping cart(0)?", ()->
            Assertions.assertThat(Selenide.$("a.ico-wishlist span",1).getText()).isEqualTo("(0)")
        );
        step("Wishlist(0)?", ()->
            Assertions.assertThat(Selenide.$("a.ico-cart span",1).getText()).isEqualTo("(0)")
        );
        step("Add two cards",()-> {
            ApiSteps.putStafToBaskit("/addproducttocart/catalog/31/1/1");
            ApiSteps.putStafToBaskit("/addproducttocart/catalog/31/1/1");
        });
        step("Open the main page by exist user",()->{
            Selenide.open(ApiSteps.getBaseUrl());
        });
        step("The number of cards are equal 2?", ()->
                Assertions.assertThat(Selenide.$("a.ico-cart span",1).getText()).isEqualTo("(2)")
        );
    }

    @AfterAll
    static void shouldBeEmptyAfterLogOut() {

        WebDriverRunner.clearBrowserCache();
        WebDriverRunner.getWebDriver().quit();
    }

}
