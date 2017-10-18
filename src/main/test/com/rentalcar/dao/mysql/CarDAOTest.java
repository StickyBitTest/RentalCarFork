package com.rentalcar.dao.mysql;

import com.rentalcar.dao.DAOFactory;
import com.rentalcar.dao.abstracted.CarDAO;
import com.rentalcar.models.builders.CarBuilder;
import com.rentalcar.models.builders.EntityBuilderException;
import com.rentalcar.models.car.Car;
import com.rentalcar.models.car.TransmissionType;
import com.rentalcar.models.car.VehicleType;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertTrue;

public class CarDAOTest {


    @Test
    public void addVolvo(){
        Car car = createVolvo();
        CarDAO carDAO = DAOFactory.getCarDAO();
        assertTrue(carDAO.add(car)>0);
    }

    public Car createVolvo() {
        Car testCar  = null;
        CarBuilder builder = new CarBuilder()
                .setModel("Volvo V60")
                .setVehicle(VehicleType.WAGON)
                .setDailyPrice("40")
                .setTransmission(TransmissionType.AUTOMATIC)
                .setImgName("1");
        try {
            testCar = builder.getCar();
        } catch (EntityBuilderException e) {
            e.printStackTrace();
        }
        return testCar;
    }

   /* CarAttributes createMiniCooper(){
        CarAttributes car = new CarAttributes();
        car.setDailyPrice(new BigDecimal(30));
        car.setModel("Mini Cooper");
        car.setImgFile("18557721bf63a5598a4494af8e1c9e98");
        return car;
    }

    CarAttributes createMercedes(){
        CarAttributes car = new CarAttributes();
        car.setDailyPrice(new BigDecimal(40));
        car.setModel("Mercedez Benz GLA");
        car.setImgFile("d9eaabe53adedb62bc74b7eb0a9477d4");
        return car;
    }

    @Test
    public void testAddAdmin(){
        CarDAO dao = DAOFactory.getCarDAO();
        int result = dao.add(createMercedes());
        assertTrue(result > 0);
    }

    @Test
    public void testFind(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
    }
*/
}