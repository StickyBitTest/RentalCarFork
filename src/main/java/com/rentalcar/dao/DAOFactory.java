package com.rentalcar.dao;

import com.rentalcar.dao.abstracted.AccountDAO;
import com.rentalcar.dao.abstracted.CarDAO;
import com.rentalcar.dao.implementation.AccountRentalDAO;
import com.rentalcar.dao.implementation.CarRentalDAO;

public class DAOFactory {
    public static AccountDAO getAccountDAO() {
        return new AccountRentalDAO();
    }

    public static CarDAO getCarDAO() {return new CarRentalDAO();}
}
