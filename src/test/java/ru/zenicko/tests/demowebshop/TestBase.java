package ru.zenicko.tests.demowebshop;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import ru.zenicko.config.UrlsConfig;
import ru.zenicko.userdata.ExistUserData;

public class TestBase {
    public static UrlsConfig urlsConfig = ConfigFactory.create(UrlsConfig.class);
    public ExistUserData existUser = new ExistUserData();

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = urlsConfig.urlUiBase();
        Configuration.baseUrl = urlsConfig.urlApiBase();
    }

    @AfterEach
    void setDown() {
        WebDriverRunner.clearBrowserCache();
        WebDriverRunner.getWebDriver().quit();
    }
}
