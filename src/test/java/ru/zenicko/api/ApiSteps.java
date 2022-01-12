package ru.zenicko.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import ru.zenicko.tests.demowebshop.TestBase;
import ru.zenicko.userdata.ExistUserData;

import java.util.Map;

import static org.hamcrest.core.Is.is;

public class ApiSteps extends TestBase {

    private static Map<String, String> cookiesStart;
    private static Map<String, String> cookiesAfterSignIn;
    final private static String EXISTUSER = ExistUserData.setExistUsedData();


    public static Map<String, String> getStartCookies() {
        cookiesStart =
                RestAssured
                        .given()
                        .when()
                        .get(urlsConfig.urlApiBase())
                        .then()
                        .extract().cookies();

        return cookiesStart;
    }
    public static Map<String, String> getStartCookies(String urlPage) {
        cookiesStart =
                RestAssured
                        .given()
                        .when()
                        .get(urlPage)
                        .then()
                        .extract().cookies();

        return cookiesStart;
    }

    public static Map<String, String> getCookiesAfterSignIn() {
        getStartCookies();
        cookiesAfterSignIn =
                RestAssured
                        .given()
                        .contentType("application/x-www-form-urlencoded")
                        .cookies(cookiesStart)
                        .body(EXISTUSER)
                        .when()
                        .post(urlsConfig.urlApiBase() + urlsConfig.urlApiLogin())
                        .then()
                        .extract().cookies();

        return cookiesAfterSignIn;
    }

    public static  Map<String, String> getCookiesAfterSignIn(String existUser, String urlPage, String apiRequest) {
        getStartCookies(urlPage);
        cookiesAfterSignIn =
                RestAssured
                        .given()
                        .contentType("application/x-www-form-urlencoded")
                        .cookies(cookiesStart)
                        .body(existUser)
                        .when()
                        .post(urlPage + apiRequest)
                        .then()
                        .extract().cookies();

        return cookiesAfterSignIn;
    }

    public static Response putStafToBaskit(String apiUrlAddCart) {
        Response data =
            RestAssured
                    .given()
                    .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                    .cookies(cookiesAfterSignIn)
                    .body(urlsConfig.urlApiBase() + apiUrlAddCart)
                    .when()
                    .post(urlsConfig.urlApiBase() + apiUrlAddCart)
                    .then()
                    .statusCode(200)
                    .body("success", is(true))
                    .extract().response();

        return data;
    }

}
