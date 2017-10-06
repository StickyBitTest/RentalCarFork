package com.rentalcar.models.builders;

import com.rentalcar.controllers.utils.ErrorMessage;
import com.rentalcar.models.builders.validators.CarValidator;
import com.rentalcar.models.car.Car;
import com.rentalcar.models.car.TransmissionType;
import com.rentalcar.models.car.VehicleType;

import java.math.BigDecimal;

public class CarBuilder extends EntityBuilder implements CarValidator {

    private static final String DEFAULT_IMG = "default";

    private String model;
    private String imgFileName;
    private BigDecimal dailyPrice;
    private TransmissionType transmissionType;
    private VehicleType vehicleType;

    public Car getCar() throws EntityBuilderException {
        validate();
        Car car = new Car();
        car.setModel(model);
        car.setImgFile(imgFileName);
        car.setDailyPrice(dailyPrice);
        car.setTransmission(transmissionType);
        car.setVehicle(vehicleType);
        return car;
    }

    public CarBuilder setModel(String model){
        validate(isModelValid(model), ErrorMessage.CAR_MODEL);
        this.model = model;
        return this;
    }

    public CarBuilder setImgURL(String fileName){
        if(isEmpty(fileName))
            this.imgFileName = DEFAULT_IMG;
        return this;
    }

    public CarBuilder setDailyPrice(BigDecimal price){
        validate(isPriceValid(price), ErrorMessage.CAR_PRICE);
        this.dailyPrice = price;
        return this;
    }


    public CarBuilder setTransmission(TransmissionType type){
        validate(!isNull(type), ErrorMessage.CAR_TRANSMISSION);
        this.transmissionType = type;
        return this;
    }

    public CarBuilder setVehicle(VehicleType type){
        validate(!isNull(type), ErrorMessage.CAR_VEHICLE);
        this.vehicleType = type;
        return this;
    }

}

