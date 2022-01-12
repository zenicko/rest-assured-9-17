package ru.zenicko.config;

import org.aeonbits.owner.Config;
@Config.Sources({"classpath:resources/urls/urls.properties"})
public interface UrlsConfig extends Config {
    @Key("url.ui.base")
    @DefaultValue("http://demowebshop.tricentis.com")
    String urlUiBase();

    @Key("url.ui.touchingObject")
    @DefaultValue("/Themes/DefaultClean/Content/images/logo.png")
    String urlUiTouchingObject();

    @Key("url.api.base")
    @DefaultValue("http://demowebshop.tricentis.com")
    String urlApiBase();

    @Key("url.api.registrate")
    @DefaultValue("/register")
    String urlApiRegistrate();

    @Key("url.api.login")
    @DefaultValue("/login")
    String urlApiLogin();

    @Key("url.api.addCart")
    @DefaultValue("/addproducttocart/catalog/31/1/1")
    String urlApiAddCart();
}
