package com.rentalcar.models.builders;

import com.rentalcar.controllers.utils.ErrorMessage;
import com.rentalcar.models.builders.validators.CarValidator;
import com.rentalcar.models.car.Car;
import com.rentalcar.models.order.OrderStatus;
import com.rentalcar.models.user.Client;
import com.rentalcar.models.order.Order;
import com.rentalcar.models.order.TermDate;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

public class OrderBuilder extends EntityBuilder implements CarValidator{

    private Client client;
    private TermDate term;
    private Car car;
    private BigDecimal totalPrice;
    private OrderStatus status;

    public Order getOrder() throws EntityBuilderException {
        validate();
        Order order = new Order();
        order.setCar(car);
        order.setClient(client);
        order.setTerm(term);
        order.setTotalPrice(totalPrice);
        order.setStatus(status);
        return order;
    }


    private BigDecimal computePrice(TermDate date, Car car){
        long diff = date.getDropOff().getTime() - date.getPickUp().getTime();
        long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        if(days > 0){
            return car.getDailyPrice().multiply(new BigDecimal(days));
        }
        errorMessage = ErrorMessage.ERROR_PRICE;
        return null;
    }


    public OrderBuilder setCar(Car car){
        validate(!isNull(car), ErrorMessage.ORDER_CAR);
        this.car = car;
        return this;
    }

    public OrderBuilder setTerm(TermDate date){
        validate(!isNull(date), ErrorMessage.ORDER_DATE);
        this.term = date;
        return this;
    }

    public OrderBuilder setClient(Client client){
        validate(!isNull(client), ErrorMessage.ORDER_CLIENT);
        this.client = client;
        return this;
    }

    public OrderBuilder setPrice(BigDecimal price){
        validate(isPriceValid(price), ErrorMessage.ORDER_PRICE);
        this.totalPrice = price;
        return this;
    }

    public OrderBuilder setStatus(OrderStatus status){
        validate(!isNull(status), ErrorMessage.ORDER_STATUS);
        this.status = status;
        return this;
    }

}
