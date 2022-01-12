package ru.zenicko.tests.demowebshop;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Cookie;
import ru.zenicko.api.ApiSteps;
import ru.zenicko.userdata.ExistUserData;

import java.util.Map;

import static io.qameta.allure.Allure.step;

public class MainPageTests extends TestBase {
    static Map<String, String> cookies;

    @BeforeEach
    @DisplayName("Set sign-in cookies")
    void SetUp(){
        cookies = ApiSteps.getCookiesAfterSignIn();
        Selenide.open(urlsConfig.urlUiTouchingObject());
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("NOPCOMMERCE.AUTH", cookies.get("NOPCOMMERCE.AUTH")));
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("Nop.customer", cookies.get("Nop.customer")));
    }


    @Test
    @DisplayName("Check the user email in header main page after sign-in")
    void shouldBeEmail() {
        step("Open the main page by exist user",()->{
            Selenide.open();
        });
        step("The emails are equal?", ()->
            Assertions.assertThat(Selenide.$("a.account").getText()).isEqualTo(ExistUserData.getEmailExistUser())
        );
    }

    @Test
    @DisplayName("Check The basket")
    void shouldBeBasketIsEmpty() {
        step("Open the main page by exist user",()->{
            Selenide.open();
        });
        step("Shopping cart(0)?", ()->
            Assertions.assertThat(Selenide.$("a.ico-wishlist span",1).getText()).isEqualTo("(0)")
        );
        step("Wishlist(0)?", ()->
            Assertions.assertThat(Selenide.$("a.ico-cart span",1).getText()).isEqualTo("(0)")
        );
        step("Add two cards",()-> {
            ApiSteps.putStafToBaskit(urlsConfig.urlApiAddCart());
            ApiSteps.putStafToBaskit(urlsConfig.urlApiAddCart());
        });
        step("Open the main page by exist user",()->{
            Selenide.open();
        });
        step("The number of cards are equal 2?", ()->
                Assertions.assertThat(Selenide.$("a.ico-cart span",1).getText()).isEqualTo("(2)")
        );
    }
}
