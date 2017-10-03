package com.rentalcar.dao.jdbc;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBConnection {

    private static final Logger log = Logger.getLogger(DBConnection.class);

    private static Connection dbConnection = null;

    public static Connection getConnection(){
        if(dbConnection == null)
            try {
                ResourceBundle resourceBundle = ResourceBundle.getBundle("db/db");
                String driver = resourceBundle.getString("DB_DRIVER_CLASS");
                String url = resourceBundle.getString("DB_URL");
                String username = resourceBundle.getString("DB_USERNAME");
                String password = resourceBundle.getString("DB_PASSWORD");
                Class.forName(driver);
                dbConnection = DriverManager.getConnection(url, username, password);
                //Class.forName(driver).newInstance();
            } catch ( SQLException | ClassNotFoundException e) {
                log.error("Get db connection - " + e.getMessage());
            }
        return dbConnection;
    }
}
