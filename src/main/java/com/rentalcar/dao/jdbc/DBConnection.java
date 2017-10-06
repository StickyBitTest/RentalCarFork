package com.rentalcar.dao.jdbc;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import org.apache.commons.dbcp.BasicDataSource;

/**
 * Implementation of connection pool from resource bundle db/db.properties.
 *
 * @author yullia.tatarinova@gmail.com
 */
public class DBConnection {

    private static final Logger log = Logger.getLogger(DBConnection.class);

    private static final String BUNDLE = "db/db";
    private static final String DRIVER = "DB_DRIVER_CLASS";
    private static final String URL = "DB_URL";
    private static final String USERNAME = "DB_USERNAME";
    private static final String PASSWORD = "DB_PASSWORD";
    private static final String POOL_SIZE = "DB_POOL_SIZE";
    private static final String MIN_IDLE = "DB_MIN_IDLE";
    private static final String MAX_IDLE = "DB_MAX_IDLE";

    private static class DBConnectionHolder{
        private static DBConnection instance = new DBConnection();
    }

    public static DBConnection getInstance(){
        return DBConnectionHolder.instance;
    }

    private BasicDataSource pool;


    public Connection getConnection() {
        try {
            return pool.getConnection();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return null;
    }


    private DBConnection(){
        setUpPool();
    }

    private void setUpPool(){
        ResourceBundle resources = ResourceBundle.getBundle(BUNDLE);
        String driver = resources.getString(DRIVER);
        try {
            Class.forName(driver);
            pool = new BasicDataSource();
            pool.setDriverClassName(driver);
            pool.setUsername(resources.getString(USERNAME));
            pool.setPassword(resources.getString(PASSWORD));
            pool.setUrl(resources.getString(URL));

            pool.setInitialSize(Integer.valueOf(resources.getString(POOL_SIZE)));
            pool.setMinIdle(Integer.valueOf(resources.getString(MIN_IDLE)));
            pool.setMaxIdle(Integer.valueOf(resources.getString(MAX_IDLE)));

        } catch (ClassNotFoundException e) {
            log.error(e.getMessage());
        }
    }

}
