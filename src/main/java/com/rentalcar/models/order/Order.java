package com.rentalcar.models.order;

import com.rentalcar.models.Entity;
import com.rentalcar.models.car.Car;
import com.rentalcar.models.user.Client;

import java.math.BigDecimal;

public class Order extends Entity{

    private Client client;
    private TermDate term;
    private Car car;
    private BigDecimal totalPrice;
    private OrderStatus status;

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

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
