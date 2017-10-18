package com.rentalcar.dao.mysql;

import com.rentalcar.dao.DAOContext;
import com.rentalcar.dao.abstracted.AccountDAO;
import com.rentalcar.dao.jdbc.DBConnection;
import com.rentalcar.models.user.Account;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLAccountDAO implements AccountDAO, DAOContext {

    private final static String INSERT = resourceBundle.getString("ACCOUNT.CREATE");
    private final static String GET_BY_NAME_AND_PASSWORD =
            resourceBundle.getString("ACCOUNT.GET_BY_NAME_AND_PASSWORD");

    private final Logger log = Logger.getLogger(MySQLAccountDAO.class);

    @Override
    public int add(Account account) {
        Connection connection = DBConnection.getInstance().getConnection();
        int rowCount = -1;
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT);
            statement.setString(1, account.getFullName());
            statement.setString(2, account.getEmail());
            statement.setString(3, account.getLogin());
            statement.setString(4, account.getPassword());
            statement.setBoolean(5, account.isAdmin());
            rowCount = statement.executeUpdate();
        }catch (SQLException e){
            log.error(e.getMessage());
        }
       return rowCount;
    }

    @Override
    public Account get(Account account) {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(GET_BY_NAME_AND_PASSWORD);
            statement.setString(1, account.getLogin());
            statement.setString(2, account.getPassword());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return getAccount(resultSet);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return null;
        /*
        Account result = null;
        try{
            PreparedStatement statement = connection.prepareStatement(GET_BY_NAME_AND_PASSWORD);
            statement.setString(1, account.getLogin());
            statement.setString(2, account.getPassword());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return getAccount(resultSet);
            }
        }catch (SQLException e){
            log.error(e.getMessage());
        }
        return result;
    */
    }

    private Account getAccount(ResultSet resultSet) throws SQLException {
        Account account = new Account();
        account.setFullName(resultSet.getString("full_name"));
        account.setLogin(resultSet.getString("login"));
        account.setEmail(resultSet.getString("email"));
        account.setPassword(resultSet.getString("password"));
        account.setAdmin(resultSet.getBoolean("is_admin"));
        return account;
    }
}
