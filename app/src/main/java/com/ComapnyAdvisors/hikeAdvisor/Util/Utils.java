package com.ComapnyAdvisors.hikeAdvisor.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static final String PARKS_URL = "";
    private Utils() {

    }

    //Minimum eight characters, at least one letter, one number and one special character
    public static boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
        Pattern p = Pattern.compile(passwordRegex);
        Matcher m = p.matcher(password.toString().trim());

        return m.matches();
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern p = Pattern.compile(emailRegex);
        Matcher m = p.matcher(email.toString().trim());

        return m.matches();
    }
}
