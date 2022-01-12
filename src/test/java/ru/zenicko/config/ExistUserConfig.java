package ru.zenicko.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:resources/existuser.properties"})
public interface ExistUserConfig extends Config {

    @Key("existUser.email")
    String email();
    @Key("existUser.password")
    String password();

    @Key("rememberMe")
    boolean rememberMe();
}
