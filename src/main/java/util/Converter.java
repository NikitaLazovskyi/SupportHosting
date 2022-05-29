package util;

import exception.InvalidQueryException;

import java.time.LocalDate;
import java.util.StringJoiner;

public class Converter {
    /**
     * Method to convert String date format in LocalDate valid format
     * @param date must be String in format DD.MM.YYYY or DD-MM-YYYY or DD_MM_YYYY
     * @return LocalDate in format YYYY.MM.DD
     */
    public static LocalDate convertDate(String date) {
        if (Validation.validateDate(date)) {
            String[] split = date.split("[.]");
            StringJoiner stringJoiner = new StringJoiner("-");
            stringJoiner.add(split[2]);
            stringJoiner.add(split[1]);
            stringJoiner.add(split[0]);
            return LocalDate.parse(stringJoiner.toString());
        } else {
            throw new InvalidQueryException("invalid date format: " + date);
        }
    }
}
