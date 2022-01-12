package ru.zenicko.demowebshop;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.restassured.response.Response;
import org.openqa.selenium.Cookie;

import java.util.Map;

public class MainClass {

    public static void main(String[] args) {
        String urlPage = "http://demowebshop.tricentis.com";///Themes/DefaultClean/Content/images/logo.png";

        String existUser = UserData.setUsedData("timrode@mail.com","123456", false);

        Map<String, String> cookiesStart = ApiSteps.getStartCookies();
        System.out.println(cookiesStart.toString());

        Map<String, String> cookiesAfterSignIn = ApiSteps.getCookiesAfterSignIn();
        System.out.println("cookiesAfterSignIn  " + cookiesAfterSignIn.toString());

        Map<String, String> cookiesAfterSignInByFullParam = ApiSteps.getCookiesAfterSignIn(ApiSteps.getExistUser(), ApiSteps.getBaseUrl(), "/login");
        System.out.println("cookiesAfterSignInByFullParam  " + cookiesAfterSignInByFullParam.toString());

        Map<String, String> cookiesAfterSignInByFullParamSetUser = ApiSteps.getCookiesAfterSignIn(existUser, ApiSteps.getBaseUrl(), "/login");
        System.out.println("cookiesAfterSignInByFullParamSetUser  " + cookiesAfterSignInByFullParamSetUser.toString());
        System.out.println("cookiesAfterSignInByFullParamSetUser: email  " + UserData.getEmailUser());


        Selenide.open(ApiSteps.getBaseUrl() + "/Themes/DefaultClean/Content/images/logo.png");
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("NOPCOMMERCE.AUTH", cookiesAfterSignIn.get("NOPCOMMERCE.AUTH")));
        Selenide.open(ApiSteps.getBaseUrl());
        System.out.println(Selenide.$("a.account").getText());
        System.out.println();

        Response data = ApiSteps.putStafToBaskit("/addproducttocart/catalog/31/1/1");

        System.out.println(data.asString());
        Response data1 = ApiSteps.putStafToBaskit("/addproducttocart/catalog/31/1/1");
        System.out.println(data1.asString());
        Response data2 = ApiSteps.putStafToBaskit("/addproducttocart/catalog/31/1/1");
        System.out.println(data2.asString());

    }


}
