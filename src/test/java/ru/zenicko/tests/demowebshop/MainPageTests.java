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

@Tag("mainPageAndBasket")
public class MainPageTests extends TestBase {
    Map<String, String> cookies;
    ApiSteps apiSteps = new ApiSteps();

    @BeforeEach
    @DisplayName("Set sign-in cookies")
    void SetUp() {
        cookies = apiSteps.getCookiesAfterSignIn();
        Selenide.open(urlsConfig.urlUiTouchingObject());
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("NOPCOMMERCE.AUTH", cookies.get("NOPCOMMERCE.AUTH")));
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("Nop.customer", cookies.get("Nop.customer")));
    }

    @Test
    @DisplayName("Check the user email in header main page after sign-in")
    void shouldBeEmail() {
        step("Open the main page by exist user", () -> {
            Selenide.open(urlsConfig.urlUiBase());
        });
        step("The emails are equal?", () ->
                Assertions.assertThat(Selenide.$("a.account").getText()).isEqualTo(existUser.getEmailExistUser())
        );
    }

    @Test
    @DisplayName("Check the basket")
    void shouldBeBasketIsEmpty() {
        step("Open the main page by exist user", () -> {
            Selenide.open(urlsConfig.urlUiBase());
        });
        step("Shopping cart(0)?", () ->
                Assertions.assertThat(Selenide.$("a.ico-wishlist span", 1).getText()).isEqualTo("(0)")
        );
        step("Wishlist(0)?", () ->
                Assertions.assertThat(Selenide.$("a.ico-cart span", 1).getText()).isEqualTo("(0)")
        );
        step("Add two cards", () -> {
            apiSteps.putStafToBaskit(urlsConfig.urlApiAddCart());
            apiSteps.putStafToBaskit(urlsConfig.urlApiAddCart());
        });
        step("Open the main page by exist user", () -> {
            Selenide.open(urlsConfig.urlUiBase());
        });
        step("The number of cards are equal 2?", () ->
                Assertions.assertThat(Selenide.$("a.ico-cart span", 1).getText()).isEqualTo("(2)")
        );
    }


}
