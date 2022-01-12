package ru.zenicko.tests.reqres.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsMapContaining.hasKey;

@DisplayName("Testing API from https://reqres.in/")
public class ReqresTests {
    @BeforeAll
    static void SetUp() {
        RestAssured.baseURI = "https://reqres.in/";
    }

    @Test
    @DisplayName("List users: check status code ")
    void shouldBeStatusCode200InResponseListUsers() {
        RestAssured
                .given()
                .when()
                    .get("/api/users?page=2")
                .then()
                    .statusCode(200);
    }
    @Test
    @DisplayName("Single users: check a value of a field response")
    void shouldBeStatusCode200InResponseSingleUser() {
        RestAssured
                .given()
                .when()
                    .get("/api/users/2")
                .then()
                    .body("data.id", is(2));
    }

    @Test
    @DisplayName("Single user: check existing field")
    void shouldHaveFieldInResponseSingleUser() {
        RestAssured
                .given()
                .when()
                    .get("/api/users/2")
                .then()
                    .body("$", hasKey("data"));
    }

    @Test
    @DisplayName("Single <resource> not found: check body is empty")
    void shouldBeBodyNullSingleResource() {
        String jsonBodyActually =
                RestAssured
                .given()
                .when()
                    .get("/api/unknown/23")
                .then()
                    .statusCode(404)
                    .extract()
                        .response()
                        .asString();

        Assertions.assertThat(jsonBodyActually).isEqualTo("{}");
    }

    @Test
    @DisplayName("Create: validation response JSON by schema.json")
    void validateResponseCreateNewUser() {
        String jsonUser = "{\"name\": \"morpheus\", \"job\": \"leader\"}";

        String jsonActual =
                RestAssured
                        .given()
                            .contentType(ContentType.JSON)
                            .body(jsonUser)
                        .post("/api/users")
                        .then()
                        .extract().response().asString();
        System.out.println(jsonActual);

        assertThat(jsonActual, JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonschemes/schema-new-user.json"));
    }
}
