package ru.zenicko.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:users/newuser.properties"})
public interface NewUserConfig extends Config {
    @Key("gender")
    String gender();

    @Key("firstName")
    String firstName();

    @Key("LastName")
    String LastName();

    @Key("email")
    String email();

    @Key("password")
    String password();
}
