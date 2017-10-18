package com.rentalcar.models.builders;

import com.rentalcar.models.builders.validators.CarValidator;
import com.rentalcar.models.car.Car;
import com.rentalcar.models.order.OrderStatus;
import com.rentalcar.models.user.Client;
import com.rentalcar.models.order.Order;
import com.rentalcar.models.order.TermDate;

import java.math.BigDecimal;

import static com.rentalcar.constants.MessageWrapper.Error;

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

    public OrderBuilder setCar(Car car){
        validate(!isNull(car), Error.ORDER_CAR);
        this.car = car;
        return this;
    }

    public OrderBuilder setTerm(TermDate date){
        validate(!isNull(date), Error.ORDER_DATE);
        this.term = date;
        return this;
    }

    public OrderBuilder setClient(Client client){
        validate(!isNull(client), Error.ORDER_CLIENT);
        this.client = client;
        return this;
    }

    public OrderBuilder setPrice(BigDecimal price){
        validate(isPriceValid(price), Error.ORDER_PRICE);
        this.totalPrice = price;
        return this;
    }

    public OrderBuilder setStatus(OrderStatus status){
        validate(!isNull(status), Error.ORDER_STATUS);
        this.status = status;
        return this;
    }

}
