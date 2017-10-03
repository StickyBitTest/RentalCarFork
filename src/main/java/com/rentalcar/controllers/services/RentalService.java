package com.rentalcar.controllers.services;

import com.rentalcar.dao.DAOFactory;
import com.rentalcar.dao.abstracted.CarDAO;
import com.rentalcar.models.Car;
import com.rentalcar.models.TermDate;

import java.util.List;

public class RentalService {

    private RentalService(){}

    private static class SingletonHolder{
        private static RentalService instance = new RentalService();
    }

    public static RentalService getInstance() {
        return RentalService.SingletonHolder.instance;
    }

    public List<Car> findCars(TermDate term) {
        CarDAO carDAO = DAOFactory.getCarDAO();
        return carDAO.get(term);
    }
}
