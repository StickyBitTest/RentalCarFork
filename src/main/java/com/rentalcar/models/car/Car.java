package com.rentalcar.models.car;

import com.rentalcar.models.Entity;

import java.math.BigDecimal;

public class Car extends Entity {

    private String model;
    private String imgFile;
    private BigDecimal dailyPrice;
    private VehicleType vehicleType;
    private TransmissionType transmissionType;

    public String getImgFile() {
        return imgFile;
    }

    public void setImgFile(String imgFile) {
        this.imgFile = imgFile;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(BigDecimal dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public VehicleType getVehicle() {
        return vehicleType;
    }

    public void setVehicle(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public TransmissionType getTransmission() {
        return transmissionType;
    }

    public void setTransmission(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }
}
