package com.rentalcar.models.builders.validators;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public interface TermDateValidator extends Validator {

    Logger log = Logger.getLogger(TermDateValidator.class);

    /**
     * @return true if dates are valid period of time
     */
    default boolean isDatesValid(Date pickUp, Date dropOff){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            Date today = dateFormat.parse(dateFormat.format(new Date()));
            if(today.after(dropOff))
                return false;
        } catch (ParseException e) {
            log.error(e.getMessage());
        }
        if (pickUp.after(dropOff) || pickUp.compareTo(dropOff) == 0 )
            return false;
        return true;
    }
}
