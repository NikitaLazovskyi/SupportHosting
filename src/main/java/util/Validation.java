package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public static boolean validateServiceAndQuestionTypes(String type) {
        if (type.matches("^\\*$")) {
            return true;
        } else {
            Pattern pattern = Pattern.compile("[^\\d.]");
            Matcher matcher = pattern.matcher(type);
            return !matcher.find();
        }
    }

    public static boolean validateType(String type) {
        return type.matches("^\\p{L}$");
    }

    public static boolean validateDate(String stringDate) {
        return stringDate.matches("^\\d{2}[._-]\\d{2}[._-]\\d{4}$");
    }
}
