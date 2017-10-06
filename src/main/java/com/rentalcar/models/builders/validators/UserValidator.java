package com.rentalcar.models.builders.validators;

import java.util.Calendar;
import java.util.Date;

/**
 *  Fields validator, provides methods for entity fields.
 *  Methods return true in case of success and false otherwise
 */
public interface UserValidator extends Validator{

    String PASSPORT_PATTERN = "^([a-zA-Z0-9]){8}$";
    String CARD_NUMBER_PATTERN = "^\\d{16}$";
    String EMAIL_PATTERN = "(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$)";
    String FULL_NAME_PATTERN = "^([a-zA-Z ]){2,30}$";

    /**
     * @return true if login length greater than 3
     */
    default boolean isLoginValid(String login){
        return (!isEmpty(login) && login.length() > 2);
    }


    /**
     * @return true if password length greater than 6
     */
    default boolean isPasswordValid(String password){
        return (!isEmpty(password) && password.length() > 5);
    }

    /**
     * @return true if passport contains only digits and letters, length is 8
     */
    default boolean isPassportValid(String passport){
        return  (!isEmpty(passport) && passport.length() == 8 && isMatches(passport, PASSPORT_PATTERN));
    }

    /**
     * @return true if card number length 16 and contains only digits
     */
    default boolean isValidCardNumber(String cardNumber){
        return (!isEmpty(cardNumber) && isMatches(cardNumber, CARD_NUMBER_PATTERN));
    }

    /**
     * @return true in case date not empty and it after than current month
     */
    default boolean isValidExpireDate(Date date){
        if(date != null){
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, 1);
            Date endDate = calendar.getTime();
            return endDate.after(date);
        }
        return false;
    }

    /**
     * @return true in case cvv2 valid to pattern
     */
    default boolean isValidCVV2(int cvv2){
        return (cvv2 > 99 && cvv2<1000);
    }

    /**
     * @return true if email matches pattern
     */
    default boolean isEmailValid(String email){
        return (!isEmpty(email) && isMatches(email, EMAIL_PATTERN));
    }


    /**
     * @return true if full name matches letters
     */
    default boolean isFullNameValid(String fullName){
        return (!isEmpty(fullName) && isMatches(fullName, FULL_NAME_PATTERN));
    }

}
