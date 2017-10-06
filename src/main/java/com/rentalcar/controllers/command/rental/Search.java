package com.rentalcar.controllers.command.rental;

import com.rentalcar.controllers.command.Command;
import com.rentalcar.controllers.command.Index;
import com.rentalcar.controllers.services.RentalService;
import com.rentalcar.controllers.utils.ErrorMessage;
import com.rentalcar.controllers.utils.SessionContext;
import com.rentalcar.models.car.Car;
import com.rentalcar.models.order.TermDate;
import com.rentalcar.models.builders.TermDateBuilder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Search implements Command, SessionContext {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TermDateBuilder dateBuilder = new TermDateBuilder(request);
        TermDate term = dateBuilder.getTermDate();
        if(term != null){
            RentalService service = RentalService.getInstance();
            List<Car> cars = service.findCars(term);
            if(cars != null && cars.size() > 0){
                request.setAttribute("cars", cars);
                request.setAttribute("days", term.getDaysCount());
                System.out.println(term.getDaysCount());
                String view = "/jsp/rental/cars.jsp";
                RequestDispatcher dispatcher = request.getRequestDispatcher(view);
                dispatcher.forward(request, response);
            }
            setError(request, ErrorMessage.ERROR_NO_CARS);
            //request.getSession().setAttribute("error", "No cars available. Try another dates");
        }else {
            setError(request, dateBuilder.getErrorMessage());
           // request.getSession().setAttribute("error", "Please, input valid dates!");
        }
        new Index().execute(request, response);
    }
}
