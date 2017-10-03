package com.rentalcar.models;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TermDate {

    private static final double DAY_MILLIS = 1000.0 * 24.0 * 60.0 * 60.0;
    private Date pickUp;
    private Date dropOff;
    private long numberOfDays;

    public TermDate(Date pickUp, Date dropOff){
        this.dropOff = dropOff;
        this.pickUp = pickUp;
        System.out.println(dropOff);
        System.out.println(pickUp);
        numberOfDays = (int)( (dropOff.getTime() - pickUp.getTime()) / DAY_MILLIS);
        System.out.println(numberOfDays);
        if(numberOfDays <=0)
            numberOfDays = 1;
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
}
