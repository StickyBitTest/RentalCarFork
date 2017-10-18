package com.rentalcar.models.builders;

import com.rentalcar.models.builders.validators.TermDateValidator;
import com.rentalcar.models.order.TermDate;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.rentalcar.constants.MessageWrapper.Error;

public class TermDateBuilder extends EntityBuilder implements TermDateValidator{

    private static final Logger log = Logger.getLogger(TermDateBuilder.class);

    private static final String DATE_FORMAT = "MM/dd/yyyy";

    private Date pickUp;
    private Date dropOff;

    private SimpleDateFormat dateFormat;

    public TermDateBuilder(){
        dateFormat = new SimpleDateFormat(DATE_FORMAT);
    }

    public TermDate getTermDate() throws EntityBuilderException {
        validate(isDatesValid(pickUp, dropOff), Error.INVALID_DATE);
        validate();
        return new TermDate(pickUp, dropOff);
    }


    public TermDateBuilder setPickUp(String pickUp){
        this.pickUp = getDate(pickUp);
        return this;
    }

    public TermDateBuilder setDropOff(String dropOff){
        this.dropOff = getDate(dropOff);
        return this;
    }

    private Date getDate(String dateStr){
        validate(!isNull(dateStr), Error.INVALID_DATE);
        try {
            if(!isNull(dateStr))
                return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            validate(false, Error.INVALID_DATE);
            log.error(e.getMessage());
        }
        return null;
    }
}
