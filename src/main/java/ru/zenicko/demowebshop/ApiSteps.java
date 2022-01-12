package ru.zenicko.demowebshop;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.Map;

import static org.hamcrest.core.Is.is;

public class ApiSteps {
    private static Map<String, String> cookiesStart;
    private static Map<String, String> cookiesAfterSignIn;
    final private static String BASEURL = "http://demowebshop.tricentis.com";
    final private static String EXISTUSER = "Email=timrode%40mail.com&Password=123456&RememberMe=false";


    /**
     *
     * @return
     */
    static Map<String, String> getStartCookies() {
        cookiesStart =
                RestAssured
                        .given()
                        .when()
                        .get(BASEURL)
                        .then()
                        .extract().cookies();

        return cookiesStart;
    }

    static Map<String, String> getStartCookies(String urlPage) {
        cookiesStart =
                RestAssured
                        .given()
                        .when()
                        .get(urlPage)
                        .then()
                        .extract().cookies();

        return cookiesStart;
    }

    static public Map<String, String> getCookiesAfterSignIn() {
        getStartCookies();
        cookiesAfterSignIn =
                RestAssured
                        .given()
                        .contentType("application/x-www-form-urlencoded")
                        .cookies(cookiesStart)
                        .body(EXISTUSER)
                        .when()
                        .post(BASEURL + "/login")
                        .then()
                        .extract().cookies();

        return cookiesAfterSignIn;
    }

    static public Map<String, String> getCookiesAfterSignIn(String existUser, String urlPage, String apiRequest) {
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

    static public Response putStafToBaskit(String apiUrl) {
        Response data =
            RestAssured
                    .given()
                    .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                    .cookies(cookiesAfterSignIn)
                    .body(BASEURL + apiUrl)
                    .when()
                    .post(BASEURL + apiUrl)
                    .then()
                    .statusCode(200)
                    .body("success", is(true))
                    .extract().response();

        return data;
    }

    static public String getBaseUrl() {
        return BASEURL;
    }

    static public String getExistUser() {
        return EXISTUSER;
    }

}
