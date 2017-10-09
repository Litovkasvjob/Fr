package util;

import org.apache.commons.validator.routines.EmailValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Serg on 23.08.2017.
 */
public class StringValidator {



    private static final String PASSWORD_PATTERN = "^[A-Za-z0-9]{3,10}$";

    public static boolean isEmpty(String data) {
        if (data == null) {
            return true;
        }
        data = data.trim();
        return data.isEmpty();
    }

    public static boolean isEmailValid(String email) {
        return EmailValidator.getInstance().isValid(email);

    }

    public static boolean isPasswordValid(String password) {
        return findMatcher(PASSWORD_PATTERN, password);

    }

    private static boolean findMatcher(String pattern, String value) {
        Pattern pat = Pattern.compile(pattern);
        Matcher matcher = pat.matcher(value);

        return matcher.find();
    }

}
