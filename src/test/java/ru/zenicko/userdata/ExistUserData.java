package ru.zenicko.userdata;

import org.aeonbits.owner.ConfigFactory;
import ru.zenicko.config.ExistUserConfig;

public class ExistUserData {
    private static String userData = "";

    public static String setExistUsedData() {
        ExistUserConfig existData = ConfigFactory.create(ExistUserConfig.class);
        String email = existData.email(),
                password = existData.password();
        boolean rememberMe = existData.rememberMe();
        userData = getRequestDataForUser(email, password, rememberMe);

        return userData;
     }

    public static String setExistUsedData(String email, String password, boolean rememberMe) {
        userData = getRequestDataForUser(email, password, rememberMe);

        return userData;
    }

    public static String getUserData() {
        return userData;
    }

    public static String getEmailExistUser() {
         int startPos = "Email=".length();
         int endPos = userData.indexOf('&');

         return userData.substring(startPos, endPos).replace("%40", "@");
    }

    private static String getRequestDataForUser(String email, String password, boolean rememberMe) {

        return "Email="+  email.split("@")[0] +
                "%40" + email.split("@")[1] +
                "&Password=" +
                password +
                "&RememberMe" +
                rememberMe;
    }

}
