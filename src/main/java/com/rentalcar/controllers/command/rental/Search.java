package com.rentalcar.controllers.command.rental;

import com.rentalcar.constants.CommandConstants;
import com.rentalcar.constants.MessageWrapper;
import com.rentalcar.controllers.command.Command;
import com.rentalcar.controllers.services.RentalService;
import com.rentalcar.models.builders.EntityBuilderException;
import com.rentalcar.models.car.Car;
import com.rentalcar.models.order.TermDate;
import com.rentalcar.models.builders.TermDateBuilder;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.rentalcar.constants.RequestConstants.*;

/**
 *  Find cars by selected days. Returns view with car list
 */
public class Search implements Command {

    private static final Logger log = Logger.getLogger(Search.class);

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        TermDate date = getDate(request);
        if(date == null) return getPreviousView(request);

        List<Car> cars = RentalService.getInstance().findCars(date);
        if(cars == null || cars.size() <= 0){
            setMessage(request, MessageWrapper.Error.EMPTY_CARS);
            return getPreviousView(request);
        }

        request.setAttribute(CarAttributes.CAR_LIST, cars);
        request.setAttribute(TermAttributes.RENT_DAYS, date.getDaysCount());
        request.setAttribute(TermAttributes.TERM_PERIOD, date);
        request.getSession().setAttribute(SessionAttributes.TERM, date);

        return CommandConstants.Pages.CARS;
    }

    private TermDate getDate(HttpServletRequest request){
        TermDateBuilder builder = new TermDateBuilder()
                .setPickUp(request.getParameter(TermAttributes.PICK_UP))
                .setDropOff(request.getParameter(TermAttributes.DROP_OFF));
        TermDate date = null;
        try {
            date = builder.getTermDate();
        } catch (EntityBuilderException e) {
            log.error(e.getMessage());
            setMessage(request, builder.getErrorMessage());
        }
        return date;
    }
}
