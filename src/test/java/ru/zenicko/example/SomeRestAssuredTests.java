package ru.zenicko.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;

public class SomeRestAssuredTests {

    @Test
    void shouldBeStatusCode200() {
        RestAssured
                .given()
                .when()
                    .get("https://selenoid.autotests.cloud/status")
                .then()
                    .statusCode(200)
                    .body("total", is(20));

    }

    @Test
    void shouldBeTotal20() {
        Integer totalActual =
                    RestAssured
                            .given()
                            .when()
                                .get("https://selenoid.autotests.cloud/status")
                            .then()
                            .extract().path("total");

        Assertions.assertThat(totalActual).isEqualTo(20);
    }
    @Test
    void checkFieldResponse() {
        Response response = SomeRestAssuredTests.getResponse();

        System.out.println(response);
        System.out.println(response.toString());
        System.out.println(response.asString());
        System.out.println(response.path("browsers.android").toString());

    }

    private static Response getResponse() {
        return
                RestAssured
                        .given()
                        .when()
                            .get("https://selenoid.autotests.cloud/status")
                        .then()
                            .extract()
                            .response();
    }

    @Test
    void shouldBeStatusCodeAuthSelenoid200() {
        RestAssured
                .given()
                    .auth()
                    .basic("user1", "1234")
                .when()
                    .get("https://selenoid.autotests.cloud/wd/hub/status")
                .then()
                    .statusCode(200)
                    .body("value.ready", is(true));
    }
}
