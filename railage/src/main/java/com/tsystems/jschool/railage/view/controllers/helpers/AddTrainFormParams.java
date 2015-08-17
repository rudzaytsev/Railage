package com.tsystems.jschool.railage.view.controllers.helpers;

/**
 * Created by rudolph on 28.07.15.
 */
public class AddTrainFormParams {

    private String trainNumber;
    private String seatsNumber;

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(String seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    @Override
    public String toString() {
        return "AddTrainFormParams{" +
                "trainNumber='" + trainNumber + '\'' +
                ", seatsNumber='" + seatsNumber + '\'' +
                '}';
    }
}
