package com.rentalcar.models.builders;

import com.rentalcar.models.builders.validators.CarValidator;
import com.rentalcar.models.car.Car;
import com.rentalcar.models.car.TransmissionType;
import com.rentalcar.models.car.VehicleType;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;

import static com.rentalcar.constants.MessageWrapper.Error;

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
        validate(isModelValid(model), Error.CAR_MODEL);
        this.model = model;
        return this;
    }

    public CarBuilder setImgName(String fileName){
        this.imgFileName = isEmpty(fileName) ? DEFAULT_IMG : fileName;
        return this;
    }

    public CarBuilder setDailyPrice(String priceStr){
        DecimalFormat format = new DecimalFormat();
        format.setParseBigDecimal(true);
        BigDecimal price = null;
        try {
            price = (BigDecimal) format.parse(priceStr);
        } catch (ParseException e) {
            validate(false, Error.CAR_PRICE);
        }
        validate(isPriceValid(price), Error.CAR_PRICE);
        this.dailyPrice = price;
        return this;
    }


    public CarBuilder setTransmission(TransmissionType type){
        validate(!isNull(type), Error.CAR_TRANSMISSION);
        this.transmissionType = type;
        return this;
    }

    public CarBuilder setVehicle(VehicleType type){
        validate(!isNull(type), Error.CAR_VEHICLE);
        this.vehicleType = type;
        return this;
    }

    public boolean hasError(){
        return isError;
    }

    public String getModel() {
        return model;
    }
}

