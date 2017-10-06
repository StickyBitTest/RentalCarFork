package com.rentalcar.models.builders;

import com.rentalcar.controllers.utils.ErrorMessage;
import com.rentalcar.models.builders.validators.TermDateValidator;
import com.rentalcar.models.order.TermDate;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TermDateBuilder extends EntityBuilder implements TermDateValidator{

    private static final Logger log = Logger.getLogger(TermDateBuilder.class);

    private Date pickUp;
    private Date dropOff;

    private SimpleDateFormat dateFormat;

    public TermDateBuilder(){
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    }

    public TermDate getTermDate() throws EntityBuilderException {
        validate(isDatesValid(pickUp, dropOff), ErrorMessage.ERROR_INVALID_DATE);
        validate();
        TermDate termDate = new TermDate(pickUp, dropOff);
        return termDate;
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
        validate(!isNull(dateStr), ErrorMessage.ERROR_INVALID_DATE);
        try {
            Date date = dateFormat.parse(dateStr);
            return date;
        } catch (ParseException e) {
            validate(false, ErrorMessage.ERROR_INVALID_DATE);
            log.error(e.getMessage());
        }
        return null;
    }
}
