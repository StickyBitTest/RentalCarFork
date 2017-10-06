package com.rentalcar.dao.implementation;

import com.rentalcar.dao.DAOContext;
import com.rentalcar.dao.abstracted.CarDAO;
import com.rentalcar.models.car.Car;
import com.rentalcar.models.order.TermDate;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarRentalDAO implements CarDAO, DAOContext {

    private static final Logger log = Logger.getLogger(CarRentalDAO.class);

    private final static String INSERT = resourceBundle.getString("CAR.INSERT");
    private final static String GET_FREE_BY_TERM =
            resourceBundle.getString("CAR.GET_FREE_BY_TERM");


    @Override
    public List<Car> get(TermDate term) {
        List<Car> cars = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(GET_FREE_BY_TERM);
            statement.setDate(1, new Date(term.getPickUp().getTime()));
            statement.setDate(2, new Date(term.getDropOff().getTime()));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Car car = new Car();
                car.setImgFile(resultSet.getString("img"));
                car.setModel(resultSet.getString("model"));
                car.setDailyPrice(resultSet.getBigDecimal("price"));
                cars.add(car);
            }
        } catch (SQLException e) {
            log.error("SQL exception : " + e.getMessage());
        }
        return cars;
    }

    @Override
    public int add(Car car) {
        int rowCount = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT);
            statement.setString(1, car.getModel());
            statement.setString(2, car.getImgFile());
            statement.setBigDecimal(3, car.getDailyPrice());
            rowCount = statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Unable to add car");
        }
        return rowCount;
    }

    @Override
    public Car get(int carId) {
        return null;
    }
}
