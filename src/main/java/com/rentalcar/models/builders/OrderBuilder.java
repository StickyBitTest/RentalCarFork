package com.rentalcar.models.builders;

import com.rentalcar.controllers.utils.ErrorMessage;
import com.rentalcar.models.Car;
import com.rentalcar.models.Client;
import com.rentalcar.models.Order;
import com.rentalcar.models.TermDate;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

public class OrderBuilder extends  AbstractBuilder{

    public OrderBuilder(HttpServletRequest request){
        super(request);
    }

    public Order getOrder() {
        Order order = null;
        Car car = getCar();
        Client client = getClient();
        TermDate term = getTermDate();
        if(client != null && car !=null && term !=null ){
            BigDecimal price = computePrice(term, car);
            if(price != null) {
                order = new Order();
                order.setCar(car);
                order.setClient(client);
                order.setTerm(term);
                order.setTotalPrice(price);
            }
        }
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

    private Car getCar(){
        CarBuilder carBuilder = new CarBuilder(request);
        Car car = carBuilder.getCar();
        validateObject(car, carBuilder);
        return car;
    }

    public Client getClient() {
        ClientBuilder clientBuilder = new ClientBuilder(request);
        Client client = clientBuilder.getClient();
        validateObject(client, clientBuilder);
        return client;
    }

    public TermDate getTermDate() {
        TermDateBuilder termDateBuilder = new TermDateBuilder(request);
        TermDate date = termDateBuilder.getTermDate();
        validateObject(date, termDateBuilder);
        return date;
    }

    private void  validateObject(Object obj, AbstractBuilder builder){
        if(obj == null)
            errorMessage = builder.errorMessage;
    }
}
