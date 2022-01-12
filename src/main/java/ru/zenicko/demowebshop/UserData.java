package ru.zenicko.demowebshop;

public class UserData {
    private static String userData = "";

    public static void setUsedData() {
        userData = "Email=timrode%40mail.com&Password=123456&RememberMe=false";
     }

    public static String setUsedData(String email, String password, boolean rememberMe) {
        userData = "Email="+  email.split("@")[0] +
                "%40" + email.split("@")[1] +
                "&Password=" +
                password +
                "&RememberMe" +
                rememberMe;

        return userData;
    }

    public static String getUserData() {
        return userData;
    }

    public static String getEmailUser() {
         int startPos = "Email=".length();
         int endPos = userData.indexOf('&');

         return userData.substring(startPos, endPos).replace("%40", "@");
    }

}
