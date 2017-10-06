package com.rentalcar.models.order;

import com.rentalcar.models.Entity;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TermDate extends Entity{

    private static final double DAY_MILLIS = 1000.0 * 24.0 * 60.0 * 60.0;
    private Date pickUp;
    private Date dropOff;
    private long numberOfDays;

    public TermDate(Date pickUp, Date dropOff){
        this.dropOff = dropOff;
        this.pickUp = pickUp;
        setupDays();
    }

    public Date getPickUp() {
        return pickUp;
    }

    public Date getDropOff() {
        return dropOff;
    }

    public long getDaysCount() {
        return numberOfDays;
    }

    private void setupDays(){
        numberOfDays = (int)( (dropOff.getTime() - pickUp.getTime()) / DAY_MILLIS);
        if(numberOfDays <=0)
            numberOfDays = 1;
    }
}
