package ru.zenicko.tests.demowebshop;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    @BeforeAll
    static void setUp() {

        RestAssured.baseURI = "http://demowebshop.tricentis.com";
        Configuration.baseUrl = "http://demowebshop.tricentis.com";

    }

    @AfterEach
    void setDown() {
        WebDriverRunner.clearBrowserCache();
        WebDriverRunner.getWebDriver().quit();

    }
}
