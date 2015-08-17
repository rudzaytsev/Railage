package com.tsystems.jschool.railage.view.controllers.helpers;

/**
 * Created by rudolph on 02.08.15.
 */
public class BuyTicketFormParams {

    private String passengerName;

    private String passengerLastName;

    private String passengerBirthDate;

    private String boardingStationId;

    private String rideIdForTicket;

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerLastName() {
        return passengerLastName;
    }

    public void setPassengerLastName(String passengerLastName) {
        this.passengerLastName = passengerLastName;
    }

    public String getPassengerBirthDate() {
        return passengerBirthDate;
    }

    public void setPassengerBirthDate(String passengerBirthDate) {
        this.passengerBirthDate = passengerBirthDate;
    }

    public String getBoardingStationId() {
        return boardingStationId;
    }

    public void setBoardingStationId(String boardingStationId) {
        this.boardingStationId = boardingStationId;
    }

    public String getRideIdForTicket() {
        return rideIdForTicket;
    }

    public void setRideIdForTicket(String rideIdForTicket) {
        this.rideIdForTicket = rideIdForTicket;
    }

    @Override
    public String toString() {
        return "BuyTicketFormParams{" +
                "passengerName='" + passengerName + '\'' +
                ", passengerLastName='" + passengerLastName + '\'' +
                ", passengerBirthDate='" + passengerBirthDate + '\'' +
                ", boardingStationId='" + boardingStationId + '\'' +
                ", rideIdForTicket='" + rideIdForTicket + '\'' +
                '}';
    }
}
