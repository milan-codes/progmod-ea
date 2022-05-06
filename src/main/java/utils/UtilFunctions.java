package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A collection of static, utility functions.
 */
public class UtilFunctions {

    /**
     * Capitalizes the first letter of a string.
     *
     * @param original the original string
     * @return the original string with the first letter capitalized
     */
    public static String capitalizeFirstLetter(String original) {
        return original.substring(0, 1).toUpperCase() + original.substring(1);
    }

    /**
     * Validates a date string for the format dd-MM-yyyy
     *
     * @param date - string to be validated
     * @return true if the string is a valid date, false otherwise
     */
    public static boolean isValidDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }
}
