package com.rentalcar.dao.mysql;

import com.rentalcar.dao.DAOContext;
import com.rentalcar.dao.abstracted.OrderDAO;
import com.rentalcar.dao.jdbc.DBConnection;
import com.rentalcar.models.car.Car;
import com.rentalcar.models.car.TransmissionType;
import com.rentalcar.models.car.VehicleType;
import com.rentalcar.models.order.Order;
import com.rentalcar.models.order.OrderStatus;
import com.rentalcar.models.order.TermDate;
import com.rentalcar.models.user.Client;
import com.rentalcar.models.user.CreditCard;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLOrderDAO implements OrderDAO, DAOContext{

    private static final Logger log = Logger.getLogger(MySQLOrderDAO.class);

    private static final String  ADD_CLIENT = resourceBundle.getString("CLIENT.INSERT");
    private static final String ADD_CARD  = resourceBundle.getString("CARD.INSERT");
    private static final String ADD_TERM = resourceBundle.getString("TERM.INSERT");
    private static final String ADD_ORDER = resourceBundle.getString("ORDER.INSERT");

    private static final String GET_ORDERS = resourceBundle.getString("ORDERS.SELECT");

    @Override
    public int add(Order order) {
        int result = -1;
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            Client client = order.getClient();
            TermDate termDate = order.getTerm();
            Car car = order.getCar();

            PreparedStatement clientInsert = connection.prepareStatement(ADD_CLIENT);
            clientInsert.setString(1, client.getFullName());
            clientInsert.setString(2, client.getEmail());
            clientInsert.setString(3, client.getPassport());
            clientInsert.executeUpdate();

            ResultSet resultSet = clientInsert.getGeneratedKeys();
            int clientId;
            if(!resultSet.next()){
                connection.rollback();
                return result;
            }
            clientId = resultSet.getInt(1);

            CreditCard card = client.getCreditCard();
            PreparedStatement cardInsert = connection.prepareStatement(ADD_CARD);
            cardInsert.setString(1, card.getNumber());
            cardInsert.setInt(2, clientId);
            cardInsert.setDate(3, new Date(card.getExpires().getTime()));
            cardInsert.executeUpdate();

            PreparedStatement termInsert = connection.prepareCall(ADD_TERM);
            termInsert.setDate(1, new Date(termDate.getPickUp().getTime()));
            termInsert.setDate(2, new Date(termDate.getDropOff().getTime()));
            termInsert.setInt(3, (int) car.getId());
            termInsert.executeUpdate();

            resultSet = termInsert.getGeneratedKeys();
            int termId;
            if(!resultSet.next()){
                connection.rollback();
                return result;
            }
            termId = resultSet.getInt(1);

            PreparedStatement orderInsert = connection.prepareStatement(ADD_ORDER);
            orderInsert.setInt(1, (int) car.getId());
            orderInsert.setInt(2, clientId);
            orderInsert.setInt(3, termId);
            orderInsert.setBigDecimal(4, order.getTotalPrice());
            orderInsert.setString(5, order.getStatus().name());
            result =  orderInsert.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                log.error(e1.getMessage());
            }
            log.error(e.getMessage());
        }
        return result;
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(GET_ORDERS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Order order = parseOrder(resultSet);
                if (order!= null)
                    orders.add(order);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }

        return orders;
    }

    private Order parseOrder(ResultSet resultSet) {
        Order order = new Order();
        try {
            order.setStatus(OrderStatus.valueOf(resultSet.getString("status")));
            order.setId(resultSet.getInt("id"));
            order.setTotalPrice(resultSet.getBigDecimal("total_price"));
            order.setCar(parseCar(resultSet));
            order.setTerm(parseTerm(resultSet));
            order.setClient(parseClient(resultSet));
        } catch (SQLException e) {
            log.error(e.getMessage());
            return null;
        }
        return order;
    }

    private Client parseClient(ResultSet resultSet) throws SQLException {
        Client client = new Client();
        client.setFullName(resultSet.getString("full_name"));
        client.setPassport(resultSet.getString("passport"));
        client.setEmail(resultSet.getString("email"));
        return client;
    }

    private TermDate parseTerm(ResultSet resultSet) throws SQLException {
        return new TermDate(resultSet.getDate("pick_up"),
                resultSet.getDate("drop_off"));
    }

    private Car parseCar(ResultSet resultSet) throws SQLException {
        Car car = new Car();
        car.setId(resultSet.getInt("car.id"));
        car.setModel(resultSet.getString("model"));
        car.setImgFile(resultSet.getString("img_name"));
        car.setDailyPrice(resultSet.getBigDecimal("daily_price"));
        car.setVehicle(VehicleType.valueOf(resultSet.getString("vehicle")));
        car.setTransmission(TransmissionType.valueOf(resultSet.getString("transmission")));
        return car;
    }
}
