package ru.zenicko.tests.demowebshop;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.zenicko.config.NewUserConfig;
import ru.zenicko.config.UrlsConfig;

public class TestBase {
    public static UrlsConfig urlsConfig = ConfigFactory.create(UrlsConfig.class);

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = urlsConfig.urlUiBase();
        Configuration.baseUrl = urlsConfig.urlApiBase();
    }

    @AfterEach
    void setDown() {
        WebDriverRunner.clearBrowserCache();
        WebDriverRunner.getWebDriver().quit();
    }
}
