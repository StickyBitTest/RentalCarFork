package com.rentalcar.dao.abstracted;

import com.rentalcar.models.car.Car;
import com.rentalcar.models.order.TermDate;

import java.util.List;

public interface CarDAO {

    List<Car> get(TermDate term);

    int add(Car car);

    Car get(int carId);
}
