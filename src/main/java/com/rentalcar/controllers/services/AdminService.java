package com.rentalcar.controllers.services;

import com.rentalcar.dao.DAOFactory;
import com.rentalcar.dao.abstracted.CarDAO;
import com.rentalcar.models.car.Car;
import com.rentalcar.models.order.Order;

import java.util.List;

public class AdminService {

    private AdminService(){}

    private static class SingletonHolder{
        private static AdminService instance = new AdminService();
    }

    public static AdminService getInstance() {
        return SingletonHolder.instance;
    }

    public boolean addCar(Car car){
        CarDAO carDAO = DAOFactory.getCarDAO();
        return carDAO.add(car) > 0;
    }

    public List<Order> getOrders() {
        List<Order> orders = DAOFactory.getOrderDAO().getAll();
        return orders;
    }

}
