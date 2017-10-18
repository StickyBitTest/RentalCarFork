package com.rentalcar.controllers.services;

import com.rentalcar.dao.DAOFactory;
import com.rentalcar.dao.abstracted.CarDAO;
import com.rentalcar.dao.abstracted.OrderDAO;
import com.rentalcar.models.car.Car;
import com.rentalcar.models.order.Order;
import com.rentalcar.models.order.TermDate;

import java.util.List;

public class RentalService {

    private RentalService(){}

    public boolean addOrder(Order order) {
        OrderDAO orderDAO = DAOFactory.getOrderDAO();
        return orderDAO.add(order) > 0;
    }

    private static class SingletonHolder{
        private static RentalService instance = new RentalService();
    }

    public static RentalService getInstance() {
        return RentalService.SingletonHolder.instance;
    }

    public List<Car> findCars(TermDate term) {
        return DAOFactory.getCarDAO().get(term);
    }

    public Car getCarByID(int id){
        return DAOFactory.getCarDAO().get(id);
    }
}
