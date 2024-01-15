package com.RestApiExample.RestApiExampleProg.RestApiBean;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Car {

    private String carId;
    private String carName;
    private String carColor;
    private String carPrice;

    @JsonCreator
    public Car(@JsonProperty("carId") String carId,
               @JsonProperty("carName") String carName,
               @JsonProperty("carColor") String carColor,
               @JsonProperty("carPrice") String carPrice) {
        this.carId = carId;
        this.carName = carName;
        this.carColor = carColor;
        this.carPrice = carPrice;
    }

    // Default constructor
    public Car() {
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(String carPrice) {
        this.carPrice = carPrice;
    }

    public static Car displayCar(String carId, String carName, String carColor, String carPrice) {
        return new Car(carId, carName, carColor, carPrice);
    }
}
