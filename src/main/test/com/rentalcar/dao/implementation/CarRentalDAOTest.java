package com.rentalcar.dao.implementation;

import com.rentalcar.dao.DAOFactory;
import com.rentalcar.dao.abstracted.CarDAO;
import com.rentalcar.models.Car;
import com.rentalcar.models.TermDate;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class CarRentalDAOTest {

    Car createMiniCooper(){
        Car car = new Car();
        car.setDailyPrice(new BigDecimal(30));
        car.setModel("Mini Cooper");
        car.setImgFile("18557721bf63a5598a4494af8e1c9e98");
        return car;
    }

    Car createMercedes(){
        Car car = new Car();
        car.setDailyPrice(new BigDecimal(40));
        car.setModel("Mercedez Benz GLA");
        car.setImgFile("d9eaabe53adedb62bc74b7eb0a9477d4");
        return car;
    }

    @Test
    public void testAdd(){
        CarDAO dao = DAOFactory.getCarDAO();
        int result = dao.add(createMercedes());
        assertTrue(result > 0);
    }

    @Test
    public void testFind(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
    }

}