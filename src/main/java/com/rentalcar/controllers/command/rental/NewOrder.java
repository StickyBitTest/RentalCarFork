package com.rentalcar.controllers.command.rental;

import com.rentalcar.constants.MessageWrapper;
import com.rentalcar.constants.RequestConstants;
import com.rentalcar.controllers.command.Command;
import com.rentalcar.controllers.services.RentalService;
import com.rentalcar.models.builders.EntityBuilderException;
import com.rentalcar.models.builders.OrderBuilder;
import com.rentalcar.models.builders.UserBuilder;
import com.rentalcar.models.car.Car;
import com.rentalcar.models.order.Order;
import com.rentalcar.models.order.OrderStatus;
import com.rentalcar.models.order.TermDate;
import com.rentalcar.models.user.Client;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.rentalcar.constants.RequestConstants.UserAttributes.*;

public class NewOrder implements Command {

    private static final String EXPIRES_FORMAT = "MM/yy";

    private static final Logger log = Logger.getLogger(NewOrder.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Order order = getOrder(request);
        if(order != null && RentalService.getInstance().addOrder(order)){
            setMessage(request, MessageWrapper.Info.CREATE_ORDER);
        }else {
            setMessage(request, MessageWrapper.Error.CREATE_ORDER);
        }
        return getPreviousView(request);
    }

    private Order getOrder(HttpServletRequest request) {
        Car car = getCar(request);
        TermDate date = (TermDate) request.getSession().getAttribute(SessionAttributes.TERM);
        OrderBuilder builder = new OrderBuilder()
                .setTerm(date)
                .setCar(car)
                .setPrice(computePrice(date, car))
                .setStatus(OrderStatus.NEW)
                .setClient(getClient(request));
        try {
            return builder.getOrder();
        } catch (EntityBuilderException e) {
            log.error(e.getMessage());
            setMessage(request, builder.getErrorMessage());
        }
        return null;
    }

    private Client getClient(HttpServletRequest request) {
        UserBuilder builder = new UserBuilder()
                .setEmail(request.getParameter(EMAIL))
                .setFullName(request.getParameter(FULL_NAME))
                .setPassport(request.getParameter(CLIENT_PASSPORT))
                .setCardNumber(request.getParameter(CLIENT_CARD_NUMBER))
                .setCVV2(Integer.valueOf(request.getParameter(CLIENT_CARD_CVV)))
                .setCardExpiresDate(getExpires(request));
        try {
            return builder.getClient();
        } catch (EntityBuilderException e) {
            log.error(e.getMessage());
            setMessage(request, builder.getErrorMessage());
        }
        return null;
    }

    private Car getCar(HttpServletRequest request){
        return RentalService.getInstance().getCarByID(Integer.valueOf(
                request.getParameter(RequestConstants.CarAttributes.CAR_ID)));
    }

    private BigDecimal computePrice(TermDate date, Car car){
        if(date != null || car != null){
            long diff = (date != null ? date.getDropOff().getTime() : 0) -
                    (date != null ? date.getPickUp().getTime() : 0);
            long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            if(days > 0)
                return car.getDailyPrice().multiply(new BigDecimal(days));
        }
        return null;
    }

    private Date getExpires(HttpServletRequest request){
        String dateStr = request.getParameter(CLIENT_CARD_EXPIRES);
        if(dateStr != null)
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat(EXPIRES_FORMAT);
                return dateFormat.parse(dateStr);
            } catch (ParseException e) {
                log.error(e.getMessage());
            }
        return null;
    }
}
