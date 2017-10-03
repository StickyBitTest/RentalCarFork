package com.rentalcar.dao;

import com.rentalcar.dao.jdbc.DBConnection;

import java.sql.Connection;
import java.util.ResourceBundle;

public interface DAOContext {

    ResourceBundle resourceBundle = ResourceBundle.getBundle("db/sqlqueries");
    Connection connection = DBConnection.getConnection();
}
