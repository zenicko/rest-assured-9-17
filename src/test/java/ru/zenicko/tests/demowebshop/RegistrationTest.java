package ru.zenicko.tests.demowebshop;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.zenicko.config.NewUserConfig;

import static io.qameta.allure.Allure.step;

@Tag("registration")
public class RegistrationTest extends TestBase {
    final private NewUserConfig newDataUser = ConfigFactory.create(NewUserConfig.class, System.getProperties());

    @Test
    void registrateNewUserByIU() {
        String gender = newDataUser.gender(),
                firstName = newDataUser.firstName(),
                lastName = newDataUser.LastName(),
                email = newDataUser.email(),
                password = newDataUser.password();

        step("Open the registration page",()->{
            Selenide.open(urlsConfig.urlApiRegistrate());

        });
        step("Set a gender",()->{
            Selenide.$("#gender-" + gender).click();
        });
        step("Set a first name",()->{
            Selenide.$("#FirstName").setValue(firstName);
        });
        step("Set a last name",()->{
            Selenide.$("#LastName").setValue(lastName);
        });

        step("Set an email",()->{
            Selenide.$("#Email").setValue(email);
        });
        step("Set a password and a confirm password",()->{
            Selenide.$("#Password").setValue(password);
            Selenide.$("#ConfirmPassword").setValue(password);
        });
        step("",()->{
            Selenide.$("#register-button").click();
        });
        System.out.println(WebDriverRunner.getWebDriver().manage().getCookies().toString());
        System.out.println();

        // Checking result
        step("Open the page @Registration is done",()->{
            Selenide.$("div.result").shouldHave(Condition.text("Your registration completed"));
        });
        step("Account is created",()->{
            Selenide.$("a.account").shouldHave(Condition.text(email));
        });

    }

}
