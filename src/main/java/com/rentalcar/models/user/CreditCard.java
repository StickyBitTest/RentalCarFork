package com.rentalcar.models.user;

import com.rentalcar.models.Entity;

import java.util.Date;

public class CreditCard extends Entity {

    private String number;
    private Date expires;
    private int cvv2;       //only for payment

    public CreditCard(String number, Date expires, int cvv2){
        this.expires = expires;
        this.number = number;
        this.cvv2 = cvv2;
    }

    public String getNumber() {
        return number;
    }

    public Date getExpires() {
        return expires;
    }

    public int getCvv2() {
        return cvv2;
    }
}
