package com.rentalcar.dao;

import com.rentalcar.dao.abstracted.AccountDAO;
import com.rentalcar.dao.abstracted.CarDAO;
import com.rentalcar.dao.abstracted.OrderDAO;
import com.rentalcar.dao.mysql.MySQLAccountDAO;
import com.rentalcar.dao.mysql.MySQLCarDAO;
import com.rentalcar.dao.mysql.MySQLOrderDAO;

public class DAOFactory {

    public static AccountDAO getAccountDAO() {
        return new MySQLAccountDAO();
    }

    public static CarDAO getCarDAO() {return new MySQLCarDAO();}

    public static OrderDAO getOrderDAO(){
        return new MySQLOrderDAO();
    }
}
