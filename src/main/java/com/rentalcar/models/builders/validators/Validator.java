package com.rentalcar.models.builders.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Base field validator
 */
public interface Validator {

    default boolean isEmpty(String string){
        return isNull(string) || string.length() <= 0;
    }

    default boolean isMatches(String string, String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    default boolean isNull(Object object){
        return object == null;
    }
}
