package ru.zenicko.config;

import org.aeonbits.owner.Config;

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
