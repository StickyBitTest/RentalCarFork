package com.rentalcar.dao.abstracted;

import com.rentalcar.models.Car;
import com.rentalcar.models.TermDate;

import java.util.List;

public interface CarDAO {

    List<Car> get(TermDate term);

    int add(Car car);

    Car get(int carId);
}
