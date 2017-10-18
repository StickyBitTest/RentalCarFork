package com.rentalcar.dao.mysql;

import com.rentalcar.dao.DAOContext;
import com.rentalcar.dao.abstracted.CarDAO;
import com.rentalcar.dao.jdbc.DBConnection;
import com.rentalcar.models.car.Car;
import com.rentalcar.models.car.TransmissionType;
import com.rentalcar.models.car.VehicleType;
import com.rentalcar.models.order.TermDate;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLCarDAO implements CarDAO, DAOContext {

    private static final Logger log = Logger.getLogger(MySQLCarDAO.class);

    private final static String INSERT = resourceBundle.getString("CAR.CREATE");
    private final static String GET_FREE_BY_TERM =
            resourceBundle.getString("CAR.GET_FREE_BY_TERM");
    private final static String GET_BY_ID = resourceBundle.getString("CAR.GET_BY_ID");


    @Override
    public List<Car> get(TermDate term) {
        List<Car> cars = new ArrayList<>();
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(GET_FREE_BY_TERM);
            statement.setDate(1, new Date(term.getPickUp().getTime()));
            statement.setDate(2,new Date(term.getDropOff().getTime()));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
               Car car = parseCar(resultSet);
               cars.add(car);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return cars;
    }

    @Override
    public int add(Car car) {
        int rowCount = -1;
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT);
            statement.setString(1, car.getModel());
            statement.setString(2, car.getImgFile());
            statement.setBigDecimal(3, car.getDailyPrice());
            statement.setString(4, car.getVehicle().name());
            statement.setString(5, car.getTransmission().name());
            rowCount = statement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return rowCount;
    }


    @Override
    public Car get(int carId) {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(GET_BY_ID);
            statement.setInt(1, carId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return parseCar(resultSet);
        } catch (SQLException e) {
            log.error(e.getMessage());
        }

        return null;
    }

    private Car parseCar(ResultSet set) throws SQLException {
        Car car = new Car();
        car.setDailyPrice(set.getBigDecimal("daily_price"));
        car.setImgFile(set.getString("img_name"));
        car.setModel(set.getString("model"));
        car.setId(set.getLong("id"));
        car.setTransmission(TransmissionType.valueOf(set.getString("transmission")));
        car.setVehicle(VehicleType.valueOf(set.getString("vehicle")));
        return car;
    }
}
