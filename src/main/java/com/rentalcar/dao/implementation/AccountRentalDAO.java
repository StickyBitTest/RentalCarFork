package com.rentalcar.dao.implementation;

import com.rentalcar.dao.DAOContext;
import com.rentalcar.dao.abstracted.AccountDAO;
import com.rentalcar.models.user.Account;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRentalDAO implements AccountDAO, DAOContext {

    private final static String INSERT = resourceBundle.getString("ACCOUNT.INSERT");
    private final static String GET_BY_NAME_AND_PASSWORD =
            resourceBundle.getString("ACCOUNT.GET_BY_NAME_AND_PASSWORD");

    private final Logger log = Logger.getLogger(AccountRentalDAO.class);

    @Override
    public int add(Account account) {
        int rowCount = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT);
            statement.setString(1, account.getLogin());
            statement.setString(2, account.getMail());
            statement.setBoolean(3, account.isAdmin());
            statement.setString(4, account.getPassword());
            rowCount = statement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return rowCount;
    }

    @Override
    public Account get(Account account) {
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
    }

    private Account getAccount(ResultSet resultSet) throws SQLException {
        Account account = new Account();
        account.setLogin(resultSet.getString("username"));
        account.setMail(resultSet.getString("email"));
        account.setPassword(resultSet.getString("password"));
        account.setAdmin(resultSet.getBoolean("is_admin"));
        return account;
    }
}
