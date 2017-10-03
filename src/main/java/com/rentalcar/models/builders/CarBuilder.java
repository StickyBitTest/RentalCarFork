package com.rentalcar.models.builders;

import com.rentalcar.controllers.utils.ErrorMessage;
import com.rentalcar.dao.DAOFactory;
import com.rentalcar.dao.abstracted.CarDAO;
import com.rentalcar.models.Car;

import javax.servlet.http.HttpServletRequest;

public class CarBuilder extends AbstractBuilder {

    CarBuilder(HttpServletRequest request){
        super(request);
    }

    public Car getCar() {
        int carId = Integer.valueOf(request.getParameter("car_id"));
        Car car = getCarFromDB(carId);
        if(car == null)
            errorMessage = ErrorMessage.ERROR_INVALID_CAR_DATA;
        return car;
    }

    private Car getCarFromDB(int carId) {
        CarDAO carDAO = DAOFactory.getCarDAO();
        return carDAO.get(carId);
    }
}

