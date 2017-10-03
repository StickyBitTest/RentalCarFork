package com.rentalcar.models;

import java.math.BigDecimal;

public class Order {

    private Client client;
    private TermDate term;
    private Car car;
    private BigDecimal totalPrice;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public TermDate getTerm() {
        return term;
    }

    public void setTerm(TermDate term) {
        this.term = term;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
